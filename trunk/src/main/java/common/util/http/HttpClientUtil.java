package common.util.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.CookieStore;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.activation.UnsupportedDataTypeException;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class HttpClientUtil {

	private static final Logger LOG = Logger.getLogger("EXTERNAL_SERVICE");

	private static PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();

	public static List<NameValuePair> buildEntity(Map<String, Object> urlParameter) throws UnsupportedDataTypeException {
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		if (null != urlParameter) {
			Set<String> keySet = urlParameter.keySet();
			for (String key : keySet) {
				Object object = urlParameter.get(key);
				if (object instanceof String) {
					nameValuePairs.add(new BasicNameValuePair(key, (String) object));
				} else if (object instanceof List<?>) {
					for (Object objectItem : (List<?>) object) {
						nameValuePairs.add(new BasicNameValuePair(key, (String) objectItem));
					}
				} else {
					throw new UnsupportedDataTypeException(key + " value is passed wrong data type " + object);
				}
			}
		}
		return nameValuePairs;
	}

	private static String buildURL(Map<String, String> urlParameter) {

		StringBuffer buildParameters = new StringBuffer();
		Set<String> keySet = urlParameter.keySet();
		for (String key : keySet) {
			try {
				buildParameters.append(key + "=" + java.net.URLEncoder.encode(urlParameter.get(key), "ISO-8859-1") + "&");
			} catch (UnsupportedEncodingException e) {
				LOG.error(e);
			}
		}
		return buildParameters.toString();
	}

	public static String getCall(String url) {

		CloseableHttpClient httpclient = HttpClients.custom().setConnectionManager(cm).build();

		String responseStr = null;

		try {

			HttpGet get = new HttpGet(url);
			HttpResponse response = httpclient.execute(get);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				responseStr = EntityUtils.toString(entity);
			}

		} catch (ClientProtocolException e) {
			LOG.error("", e);
		} catch (IOException e) {
			LOG.error("", e);
		} catch (ParseException e) {
			LOG.error("", e);
		} finally {
			cm.closeExpiredConnections();
		}
		return responseStr;
	}

	public static String getCall(String baseURI, String targetURI, Map<String, String> parameter, Set<Header> headers) {

		CloseableHttpClient httpclient = HttpClients.custom().setConnectionManager(cm).build();
		String response = null;
		String finalURL = targetURI;

		try {
			HttpHost target = new HttpHost(baseURI);
			if (parameter != null) {
				finalURL = targetURI + buildURL(parameter);
			}

			HttpGet req = new HttpGet(finalURL);

			if (headers != null) {
				for (Header header : headers) {
					req.addHeader(header);
				}
			}

			HttpResponse rsp = httpclient.execute(target, req);

			HttpEntity entity = rsp.getEntity();
			if (entity != null) {
				response = EntityUtils.toString(entity);
			}

			if (200 != rsp.getStatusLine().getStatusCode()) {
				LOG.error("Error while calling HttpClient: Code='" + rsp.getStatusLine().getStatusCode() + "', status text= '" + rsp.getStatusLine().getReasonPhrase() + "', response text= " + response);
				response = null;
			}

		} catch (ClientProtocolException e) {
			LOG.error("", e);
		} catch (IOException e) {
			LOG.error("", e);
		} catch (ParseException e) {
			LOG.error("", e);
		} finally {
			cm.closeExpiredConnections();
		}
		return response;
	}

	public static String postCall(String baseURI, String targetURI, Map<String, Object> parameter, Set<Header> headers, CookieStore cookieStore, Object reqEntity) {

		LOG.debug("Calling HttpClient with baseURI: " + baseURI + ", targetURI: " + targetURI);

		CloseableHttpClient httpclient = HttpClients.custom().setConnectionManager(cm).build();
		String response = null;
		try {
			HttpHost target = new HttpHost(baseURI);

			HttpPost req = new HttpPost(targetURI);
			req.setEntity(new UrlEncodedFormEntity(buildEntity(parameter)));

			if (reqEntity != null && reqEntity instanceof String) {
				req.setEntity(new StringEntity((String) reqEntity, ContentType.create("text/xml", "UTF-8")));
			}

			HttpContext localContext = new BasicHttpContext();
			if (null != cookieStore) {
				localContext.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);
			}

			if (headers != null) {
				for (Header header : headers) {
					req.addHeader(header);
				}
			}

			HttpResponse rsp = httpclient.execute(target, req, localContext);

			HttpEntity entity = rsp.getEntity();
			if (entity != null) {
				response = EntityUtils.toString(entity);
			}

			if (200 != rsp.getStatusLine().getStatusCode()) {
				LOG.error("Error while calling HttpClient: Code=" + rsp.getStatusLine().getStatusCode() + ", status text= " + rsp.getStatusLine().getReasonPhrase() + ", response text= " + response + "baseURI: " + baseURI + ", targetURI: " + targetURI);
				response = null;
			}

		} catch (UnsupportedDataTypeException e) {
			LOG.error("", e);
		} catch (ClientProtocolException e) {
			LOG.error("", e);
		} catch (IOException e) {
			LOG.error("", e);
		} catch (ParseException e) {
			LOG.error("", e);
		} finally {
			cm.closeExpiredConnections();
		}
		return response;
	}

	public static void main(String args[]) {
		LOG.info("HttpClient Util.");
		HttpClientUtil.getCall("www.google.com", "/", null, null);
	}
}
