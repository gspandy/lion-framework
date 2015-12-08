package com.newtouch.lion.redis.pool;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import redis.clients.jedis.BinaryJedis;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.InvalidURIException;
import redis.clients.util.JedisURIHelper;

import java.net.URI;
import java.util.concurrent.atomic.AtomicReference;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 *
 * @author wanglijun
 * @version 1.0
 *          2015-12-07 14:41.
 */
public class JedisFactory implements PooledObjectFactory<Jedis> {

    private final AtomicReference<HostAndPort> hostAndPort = new AtomicReference<HostAndPort>();
    /**连接超时时间*/
    private final int timeout;
    /**密码*/
    private final String password;
    /**数据库*/
    private final int database;
    /**连接的名字*/
    private final String clientName;

    /***
     *
     * @param host
     * @param port
     * @param timeout
     * @param password
     * @param database
     */
    public JedisFactory(final String host, final int port, final int timeout,
                        final String password, final int database) {
        this(host, port, timeout, password, database, null);
    }

    /***
     *
     * @param host
     * @param port
     * @param timeout
     * @param password
     * @param database
     * @param clientName
     */
    public JedisFactory(final String host, final int port, final int timeout,
                        final String password, final int database, final String clientName) {
        this.hostAndPort.set(new HostAndPort(host, port));
        this.timeout = timeout;
        this.password = password;
        this.database = database;
        this.clientName = clientName;
    }

    public JedisFactory(final URI uri, final int timeout, final String clientName) {
        if (!JedisURIHelper.isValid(uri)) {
            throw new InvalidURIException(String.format("Cannot open Redis connection due invalid URI. %s", uri.toString()));
        }

        this.hostAndPort.set(new HostAndPort(uri.getHost(), uri.getPort()));
        this.timeout = timeout;
        this.password = JedisURIHelper.getPassword(uri);
        this.database = JedisURIHelper.getDBIndex(uri);
        this.clientName = clientName;
    }

    public void setHostAndPort(final HostAndPort hostAndPort) {
        this.hostAndPort.set(hostAndPort);
    }

    @Override
    public void activateObject(PooledObject<Jedis> pooledJedis)
            throws Exception {
        final BinaryJedis jedis = pooledJedis.getObject();
        if (jedis.getDB() != database) {
            jedis.select(database);
        }

    }

    @Override
    public void destroyObject(PooledObject<Jedis> pooledJedis) throws Exception {
        final BinaryJedis jedis = pooledJedis.getObject();
        if (jedis.isConnected()) {
            try {
                try {
                    jedis.quit();
                } catch (Exception e) {
                }
                jedis.disconnect();
            } catch (Exception e) {

            }
        }

    }

    @Override
    public PooledObject<Jedis> makeObject() throws Exception {
        final HostAndPort hostAndPort = this.hostAndPort.get();
        final Jedis jedis = new Jedis(hostAndPort.getHost(),
                hostAndPort.getPort(), this.timeout);

        jedis.connect();
        if (StringUtils.isNotEmpty(this.password)) {
            jedis.auth(this.password);
        }
        if (database != 0) {
            jedis.select(database);
        }
        if (StringUtils.isNotEmpty(this.clientName)) {
            jedis.clientSetname(clientName);
        }

        return new DefaultPooledObject<Jedis>(jedis);
    }

    @Override
    public void passivateObject(PooledObject<Jedis> pooledJedis)
            throws Exception {

    }

    @Override
    public boolean validateObject(PooledObject<Jedis> pooledJedis) {
        final BinaryJedis jedis = pooledJedis.getObject();
        try {
            HostAndPort hostAndPort = this.hostAndPort.get();

            String connectionHost = jedis.getClient().getHost();
            int connectionPort = jedis.getClient().getPort();

            return hostAndPort.getHost().equals(connectionHost)
                    && hostAndPort.getPort() == connectionPort
                    && jedis.isConnected() && jedis.ping().equals("PONG");
        } catch (final Exception e) {
            return false;
        }
    }
}
