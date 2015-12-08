package com.newtouch.lion.rpc.io;

import com.newtouch.lion.rpc.exception.RpcException;

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
 *          2015-12-05 11:45.
 */
public interface Parser {
    /**
     * @param param 请求参数
     * @return
     * @throws RpcException
     */
    Request reqParse(String param) throws RpcException;

    /**
     * @param result
     * @param <T>
     * @return
     */
    public <T> T rsbParse(String result);

}
