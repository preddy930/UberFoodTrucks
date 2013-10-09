package com.uber.util;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.uber.server.exception.UberHttpClientException;

public class UberHttpClient {
	
	private String postRequest(HttpPost theRequest) throws ClientProtocolException, IOException {
		
		HttpClient client = new DefaultHttpClient();
		
		try {
 
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			String response = client.execute(theRequest, responseHandler);
			
			return response;

		} finally {
			client.getConnectionManager().shutdown();
		}
	}
	
	public String postRequest(String domain, String path, Map<String,String>requestparams, boolean https) throws UberHttpClientException {
		
		String response = "";
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		URI uri = null;
		
		for(String key : requestparams.keySet()) {
			nameValuePairs.add(new BasicNameValuePair(key,requestparams.get(key)));
		}
		
		try {
			
			if (https) {
				uri = URIUtils.createURI("https", domain, -1, path, null, null);
			}
			
			else {
				uri = URIUtils.createURI("http", domain, -1, path, null, null);
			}
			
			HttpPost post = new HttpPost(uri);
			post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			
			response = postRequest(post);
		}
		
		catch (URISyntaxException e) {
			throw new UberHttpClientException(e,uri);
		} catch (IOException e) {
			throw new UberHttpClientException(e,uri);
		}
		
		return response;
	}
	
	private String getRequest(HttpGet theRequest) throws ClientProtocolException, IOException {
		
		HttpClient client = new DefaultHttpClient();
		
		try {
				ResponseHandler<String> responseHandler = new BasicResponseHandler();
				String response = client.execute(theRequest, responseHandler);

				return response;
				
		} finally {
			client.getConnectionManager().shutdown();
		}
	}
	
	public String getRequest(String domain, String path, Map<String,String>requestparams, boolean https) throws UberHttpClientException 
	{
		URI uri=null;
		
		String response ="";
		String params = "";
		
		if ( requestparams != null) {
			
			int count = requestparams.size();
			for (String s : requestparams.keySet() ) {
				params = params.concat(s+"="+requestparams.get(s));
				count--;
			
				if (count>0) {
					params = params.concat("&");
				}
			}
		}
		
		try {
			if (https) {
				uri = URIUtils.createURI("https", domain, -1, path, params, null);		
			}
	
			else {
				uri = URIUtils.createURI("http", domain, -1, path, params, null);
			}
		
			HttpGet httpget = new HttpGet(uri);	
			response = getRequest(httpget);	
		}
		
		catch (URISyntaxException e) {
			throw new UberHttpClientException(e,uri);
		}  catch (IOException e) {
			throw new UberHttpClientException(e,uri);
		}
		
		return response;
	}
}
