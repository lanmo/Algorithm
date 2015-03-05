package com.yn.compoment;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.io.Charsets;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParamConfig;
import org.apache.http.params.HttpParams;

/**
 * Copyright (C), nanyang205380@sohu-inc.com.
 * @ClassName: RestTemplate   
 * @Description: 对httpclient进行封装
 * @author YangNan(杨楠)
 * @date 2015年2月26日 上午10:41:44 
 */
public class RestTemplate {
	private static final int DEFAULT_MAX_TOTAL_CONNECTIONS = 300;
    private static final int DEFAULT_READ_TIMEOUT_MILLISECONDS = 15 * 1000;
    private PoolingHttpClientConnectionManager pccm;
    private RequestConfig requestConfig;
    
    public RestTemplate(int maxConnectionsPerRoute) {
    	pccm = new PoolingHttpClientConnectionManager();
    	pccm.setMaxTotal(DEFAULT_MAX_TOTAL_CONNECTIONS);
    	pccm.setDefaultMaxPerRoute(maxConnectionsPerRoute);
    	
    	//创建http request的配置信息
        requestConfig = RequestConfig.custom().setConnectionRequestTimeout(DEFAULT_READ_TIMEOUT_MILLISECONDS)  
                                        .setSocketTimeout(DEFAULT_READ_TIMEOUT_MILLISECONDS).
                                        setConnectTimeout(DEFAULT_READ_TIMEOUT_MILLISECONDS).build(); 
	}
    
    public CloseableHttpClient getCloseableHttpClient() {
    	return HttpClients.custom().setConnectionManager(pccm).setDefaultRequestConfig(requestConfig).build();
    }
    
    public void post(String url, Map<String, Object> params) {
    	CloseableHttpClient client = getCloseableHttpClient();
    	HttpPost post = new HttpPost(url);
    	// 创建名/值组列表  
        List<NameValuePair> parameters = new ArrayList<NameValuePair>();
    	// 创建UrlEncodedFormEntity对象  
        UrlEncodedFormEntity formEntiry = null;
		Set<Entry<String, Object>> entries = params.entrySet();
		for(Entry<String, Object> entry : entries) {
			if(StringUtils.isBlank(entry.getKey()) || entry.getValue() == null)
				continue;
			parameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
		}
		formEntiry = new UrlEncodedFormEntity(parameters, Charsets.UTF_8);
		post.setEntity(formEntiry);
    }
    
}
