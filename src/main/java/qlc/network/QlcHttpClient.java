package qlc.network;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import com.alibaba.fastjson.JSONObject;

public class QlcHttpClient {
	
	private final URL url;
	
	public QlcHttpClient(String url) throws MalformedURLException {
		this.url = new URL(url);
	}
	
	public JSONObject send(JSONObject params) {
		
		PostMethod method = new PostMethod(url.toString());

		try {
			method.setRequestHeader("Content-Type", "application/json");

			RequestEntity requestEntity = new StringRequestEntity(params.toString(), "application/json", "UTF-8");
			method.setRequestEntity(requestEntity);

			getHttpClient().executeMethod(method);
			int statusCode = method.getStatusCode();

			if (statusCode != HttpStatus.SC_OK) {
				throw new QlcException(statusCode, "");
			}

			JSONObject response = JSONObject.parseObject(method.getResponseBodyAsString());
			if (response == null) 
				throw new QlcException(900, "Invalid response type");
			else if (response.containsKey("result") || response.containsKey("error"))
				return response;
			else
				throw new IOException();
		} catch (Exception e) {
			throw new QlcException(901, e.getMessage());
		} finally {
			method.releaseConnection();
		}
	}

	private HttpClient getHttpClient() {
		
		HttpClient client = new HttpClient();
		client.getState().setCredentials(AuthScope.ANY, null);

		return client;
	}

}
