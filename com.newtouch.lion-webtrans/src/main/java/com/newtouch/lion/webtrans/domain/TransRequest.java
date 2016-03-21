package com.newtouch.lion.webtrans.domain;

import java.io.Serializable;

/**
 * Created by wanglijun on 16/2/5.
 */
public class TransRequest implements Serializable {

    private static final long serialVersionUID = -5539534363467777475L;

    public static final String STATUS_INIT = "000";

    public static final String STATUS_COMPLETE = "100";

    public static final String TRANS_TYPE_SYNC = "0";

    public static final String TRANS_TYPE_ASYN = "1";


    private String clientIp;

    private String transCode;

    private Object requestBody;

    private String status;

    private String requestJson;


    public TransRequest() {
        super();
    }


    public String getTransCode() {
        return transCode;
    }

    public void setTransCode(String transCode) {
        this.transCode = transCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRequestJson() {
        return requestJson;
    }

    public void setRequestJson(String requestJson) {
        this.requestJson = requestJson;
    }

    public Object getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(Object requestBody) {
        this.requestBody = requestBody;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }
}
