package qlc.network;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;

public class QlcClient {
	
	private final URL url;
	
	public QlcClient(String url) throws MalformedURLException {
		this.url = new URL(url);
	}
	
	public JSONObject call(String method, JSONArray params) throws QlcException, IOException {
		
		JSONObject response = send(makeRequest(method, params));
		if (response.containsKey("result"))
			return response;
		else if (response.containsKey("error"))
			throw new QlcException((int)response.getJSONObject("error").getInteger("code"), response.getJSONObject("error").getString("message"));
		else
			throw new IOException();
	}
	
	private static JSONObject makeRequest(String method, JSONArray params) {
		
		JSONObject request = new JSONObject();
		request.put("jsonrpc", "2.0");
		request.put("id", UUID.randomUUID().toString().replace("-", ""));
		request.put("method", method);
		request.put("params", params);
		return request;
		
	}
	
	private JSONObject send(JSONObject message) throws QlcException {
		
		PostMethod method = new PostMethod(url.toString());

		try {
			method.setRequestHeader("Content-Type", "application/json");

			RequestEntity requestEntity = new StringRequestEntity(message.toString(), "application/json", "UTF-8");
			method.setRequestEntity(requestEntity);

			getHttpClient().executeMethod(method);
			int statusCode = method.getStatusCode();

			if (statusCode != HttpStatus.SC_OK) {
				throw new QlcException(statusCode, "");
			}

			JSONObject response = JSONObject.parseObject(method.getResponseBodyAsString());
			if (response == null) {
				throw new QlcException(11, "Invalid response type");
			}
			return response;
		} catch (Exception e) {
			throw new QlcException(1, e.getMessage());
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
