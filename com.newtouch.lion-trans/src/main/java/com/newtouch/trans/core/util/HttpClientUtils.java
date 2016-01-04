/* 文档密级：秘密 */
/*
 *  Copyright 2012, NEWTOUCH Co., Ltd.  All right reserved.
 *
 *  THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF NEWTOUCH CO., LTD.
 *  THE CONTENTS OF THIS FILE MAY NOT BE DISCLOSED
 *  TO THIRD PARTIES, COPIED OR DUPLICATED IN ANY FORM, IN WHOLE OR IN PART,
 *  WITHOUT THE PRIOR WRITTEN PERMISSION OF NEWTOUCH CO., LTD.
 */
package com.newtouch.trans.core.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 使用httpclient提交请求
 * 
 * @author kui.yang
 * 
 */
public class HttpClientUtils {
	private static Logger logger = LoggerFactory
			.getLogger(HttpClientUtils.class);
	private static HttpParams params = new BasicHttpParams();
	private static HttpContext httpContext = new BasicHttpContext();
	private static ClientConnectionManager connectionManager = null;

	static {
		// 设置http请求参数
		ConnManagerParams.setMaxConnectionsPerRoute(params,
				new ConnPerRouteBean(400));
		ConnManagerParams.setMaxTotalConnections(params, 800);
		HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
		SchemeRegistry schemeRegistry = new SchemeRegistry();
		schemeRegistry.register(new Scheme("http", PlainSocketFactory
				.getSocketFactory(), 80));
		connectionManager = new ThreadSafeClientConnManager(params,
				schemeRegistry);
	}

	/**
	 * String方式提交数据
	 * 
	 * @param url
	 *            URL
	 * @param xmlString
	 *            报文
	 * @param soTimeout
	 *            读取超时
	 * @param connectionTimeout
	 *            连接超时
	 * @param charset
	 *            请求字符集
	 * @return 响应字符串
	 */
	public static String postContent(String url, String xmlString,
			Integer soTimeout, Integer connectionTimeout, String charset) {
		HttpClient httpClient = new DefaultHttpClient(connectionManager, params);
		HttpPost post = new HttpPost(url);
		Long timeA = System.currentTimeMillis();
		try {
			HttpParams httpConnectionParams = httpClient.getParams();
			HttpConnectionParams.setSoTimeout(httpConnectionParams, soTimeout);
			HttpConnectionParams.setConnectionTimeout(httpConnectionParams,
					connectionTimeout);

			StringEntity requestEntity = new StringEntity(xmlString, HTTP.UTF_8);
			requestEntity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
					"application/xml"));
			post.setEntity(requestEntity);
			logger.info("post [{}]----- Start.", url);
			HttpResponse response = httpClient.execute(post, httpContext);
			HttpEntity entity = response.getEntity();
			logger.info("post  [{}]----- End.", url);
			// Read the response body.
			String responseBody = EntityUtils.toString(entity, charset);
			return responseBody;
		} catch (Exception e) {
			logger.error("http request error :url={},content={}：",
					new String[] { url, xmlString }, e);
			throw new RuntimeException("HTTP请求错误", e);
		} finally {
			if (httpClient != null) {
				httpClient.getConnectionManager().shutdown();// 最后关掉链接。
				httpClient = null;
			}
			Long timeB = System.currentTimeMillis();
			logger.info("post [" + url + "] 耗时:" + (timeB - timeA));
		}
	}

	/**
	 * post方式提交键值对
	 * 
	 * @param url
	 *            URL
	 * @param paramNames
	 *            参数key值 数组
	 * @param paramValues
	 *            参数value 数组
	 * @return 响应字符串
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static String postParams(String url, String[] paramNames,
			String[] paramValues) throws ClientProtocolException, IOException {
		HttpClient httpClient = new DefaultHttpClient(connectionManager, params);
		HttpPost httpPost = new HttpPost(url);
		Long timeA = System.currentTimeMillis();
		String xmlString = null;
		try {
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			for (int i = 0; i < paramNames.length; i++) {
				nvps.add(new BasicNameValuePair(paramNames[i], paramValues[i]));
			}
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
			logger.info("post [{}]----- Start.", url);
			HttpResponse response = httpClient.execute(httpPost, httpContext);
			HttpEntity entity = response.getEntity();
			logger.info("post  [{}]----- End.", url);
			xmlString = EntityUtils.toString(entity, HTTP.UTF_8);
			return xmlString;
		}  finally {
			if (httpClient != null) {
				httpClient.getConnectionManager().shutdown();// 最后关掉链接。
				httpClient = null;
			}
			Long timeB = System.currentTimeMillis();
			logger.info("post [" + url + "] 耗时:" + (timeB - timeA));
		}
	}

	/**
	 * 使用json格式提交报文
	 * 
	 * @param url
	 * @param jsonString
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public static String postJSON(String url, String jsonString)
			throws ClientProtocolException, IOException {
		HttpClient httpClient = new DefaultHttpClient(connectionManager, params);
		HttpPost httpPost = new HttpPost(url);
		StringEntity params = null;
		params = new StringEntity(jsonString, "UTF-8");
		httpPost.addHeader("content-type", "application/json");
		httpPost.setEntity(params);
		HttpResponse response = httpClient.execute(httpPost, httpContext);
		HttpEntity entity = response.getEntity();
		InputStream instream = entity.getContent();
		BufferedReader in = new BufferedReader(new InputStreamReader(instream,
				"UTF-8"));
		StringBuffer sb = new StringBuffer();
		String data = null;
		while ((data = in.readLine()) != null) {
			sb.append(data);
			sb.append("\n");
		}
		if (in != null)
			in.close();
		return sb.toString();
	}
	
	public static void main(String[] args) {
		
		String[] keys = {"transCode","appId","appSecret","accessToken","version","requestBodyJson"};
		String[] values = {"T2002","CPIC_CYB","4db10d28f12516baca5f117d00443d","123123","1.0","{\"loginName\":\"admin\"}"};
		
		try {
			String retval = HttpClientUtils.postParams("http://10.192.113.63/cmps/access/innerService.do", keys, values);
			System.out.println(retval);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
