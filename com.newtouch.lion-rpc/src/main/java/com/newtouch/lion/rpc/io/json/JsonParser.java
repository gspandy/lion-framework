package com.newtouch.lion.rpc.io.json;

import com.newtouch.lion.rpc.exception.RpcException;
import com.newtouch.lion.rpc.io.Parser;
import com.newtouch.lion.rpc.io.Request;

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
 *          2015-12-05 12:03.
 */
public class JsonParser implements Parser{
    /**
     * @param param 请求参数
     * @return
     * @throws com.newtouch.lion.rpc.exception.RpcException
     */
    @Override
    public Request reqParse(String param) throws RpcException {
        return null;
    }

    /**
     * @param result
     * @return
     */
    @Override
    public <T> T rsbParse(String result) {
        return null;
    }
}
