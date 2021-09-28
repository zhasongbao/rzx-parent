package com.rzx.common.utils.http;

import cn.hutool.http.ssl.TrustAnyHostnameVerifier;
import com.alibaba.fastjson.JSON;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;


public class HttpClientUtil {

	private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

	private static final String APPLICATION_JSON = "application/json";
    
	private static final String CONTENT_TYPE_TEXT_JSON = "text/json";

	private static class TrustAnyTrustManager implements X509TrustManager {

		public void checkClientTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
		}

		public void checkServerTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
		}

		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[] {};
		}
	}

	public static void httpPostWithJSON(String url, String json) throws Exception {
		 
	        // 将JSON进行UTF-8编码,以便传输中文
	        String encoderJson = URLEncoder.encode(json, HTTP.UTF_8);
	        DefaultHttpClient httpClient = new DefaultHttpClient();
	        HttpPost httpPost = new HttpPost(url);
	        httpPost.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);
	        StringEntity se = new StringEntity(encoderJson);
	        se.setContentType(CONTENT_TYPE_TEXT_JSON);
	        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON));
	        httpPost.setEntity(se);
	        httpClient.execute(httpPost);
	    }

	public static String doPost(String add_url,String json) {
		 StringBuffer sb = new StringBuffer("");
	    try {
	        //创建连接
	        URL url = new URL(add_url);
	        HttpURLConnection connection = (HttpURLConnection) url
	                .openConnection();
	        connection.setDoOutput(true);
	        connection.setDoInput(true);
	        connection.setRequestMethod("POST");
	        connection.setUseCaches(false);
	        connection.setInstanceFollowRedirects(true);
	        connection.setRequestProperty("Content-Type",
	                "application/x-www-form-urlencoded");
	        connection.setRequestProperty("Connection", "close"); // 短连接方式连接
	       // connection.setRequestProperty("Content-Type", "text/html");
	        connection.setRequestProperty("Charset", "utf-8"); // 以utf-8的方式发送
	        connection.connect();

	        //POST请求
	        DataOutputStream out = new DataOutputStream(
	                connection.getOutputStream());

	        //String encoderJson = URLEncoder.encode(json, HTTP.UTF_8);
	        //out.writeBytes(encoderJson);
	        out.write(json.getBytes());
	        out.flush();
	        out.close();

	        //读取响应
	        BufferedReader reader = new BufferedReader(new InputStreamReader(
	                connection.getInputStream()));
	        String lines;

	        while ((lines = reader.readLine()) != null) {
	            lines = new String(lines.getBytes(), "utf-8");
	            sb.append(lines);
	        }
	        reader.close();
	        // 断开连接
	        connection.disconnect();

	    } catch (IOException e) {
	        logger.error(add_url+json,e);
	    }
	    return sb.toString();

	}

	/**
	 * 发送云中鹤请求
	 * 向指定URL发送POST方法的请求 Content-Type=application/x-www-form-urlencoded
	 * @param targetUrl 发送请求的URL
	 * @param params    请求参数，请求参数应该是name1=value1&name2=value2的形式。
	 * @return JSONObject 返回的JSON数据
	 */
	public static com.alibaba.fastjson.JSONObject postFormUrlEncoded(String targetUrl, String params) {
		System.out.println("请求地址：" +targetUrl +" 入参："+ params);
		HttpURLConnection urlConnection = null;
		try {
			URL url = new URL(targetUrl.trim());
			urlConnection = (HttpURLConnection) url.openConnection();
			// 设置请求方式
			urlConnection.setRequestMethod("POST");
			// 设置数据类型
			urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			// 设置允许输入输出
			urlConnection.setDoOutput(true);
			urlConnection.setDoInput(true);
			// 设置不用缓存
			urlConnection.setUseCaches(false);

			urlConnection.connect();
			PrintWriter out = new PrintWriter(new OutputStreamWriter(urlConnection.getOutputStream(), StandardCharsets.UTF_8));
			// 写入传递参数,格式为a=b&c=d
			out.print(params);
			out.flush();

			int resultCode = urlConnection.getResponseCode();
			if (HttpURLConnection.HTTP_OK == resultCode) {
				StringBuffer stringBuffer = new StringBuffer();
				String readLine;
				BufferedReader responseReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), StandardCharsets.UTF_8));
				while ((readLine = responseReader.readLine()) != null) {
					stringBuffer.append(readLine);
				}
				responseReader.close();
				System.out.println("请求返回："+ stringBuffer.toString());
				return JSON.parseObject(stringBuffer.toString());
			}
			out.close();
		} catch (Exception e) {
			logger.error(e.toString(), e);
		} finally {
			if (urlConnection != null) {
				urlConnection.disconnect();
			}
		}
		return null;
	}

	public static com.alibaba.fastjson.JSONObject doPostToBaiHui(String url, com.alibaba.fastjson.JSONObject json) throws Exception {
		System.out.println("请求报文:"+ json);
		CloseableHttpClient httpclient = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(url);
		com.alibaba.fastjson.JSONObject response = null;
		post.addHeader("Content-type", "application/json");
		post.addHeader("Accept", "application/json;charset=UTF-8");
		StringEntity s = new StringEntity(json.toString(), ContentType.APPLICATION_JSON);
		s.setContentEncoding("UTF-8");
		s.setContentType("application/json");// 发送json数据需要设置contentType
		post.setEntity(s);
		HttpResponse res = httpclient.execute(post);
		System.out.println("返回状态:"+res.getStatusLine().getStatusCode());
		if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			String result = EntityUtils.toString(res.getEntity(),"UTF-8");// 返回json格式：
//			result = URLDecoder.decode(result,"UTF-8");
			response = JSON.parseObject(result);
		}
		System.out.println("返回报文:"+response);
		return response;
	}

	/**
	 * post方式请求服务器(https协议)
	 *
	 * @param url
	 *            请求地址
	 * @param content
	 *            参数
	 * @param charset
	 *            编码
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 * @throws IOException
	 */
	public static String doPostToBaiHuiPro(String url, String content, String charset) throws NoSuchAlgorithmException, KeyManagementException,IOException {

		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, new TrustManager[] { new TrustAnyTrustManager() }, new java.security.SecureRandom());

		URL console = new URL(url);
		HttpsURLConnection conn = (HttpsURLConnection) console.openConnection();
		conn.setSSLSocketFactory(sc.getSocketFactory());
		conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setRequestMethod("POST");
		conn.setConnectTimeout(30000);
		conn.setReadTimeout(30000);
		conn.connect();
		DataOutputStream out = new DataOutputStream(conn.getOutputStream());
		out.write(content.getBytes(charset));
		// 刷新、关闭
		out.flush();
		out.close();
		InputStream is = conn.getInputStream();
		if (is != null) {
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = is.read(buffer)) != -1) {
				outStream.write(buffer, 0, len);
			}
			is.close();
			return new String(outStream.toByteArray(),"utf-8");
		}
		return null;
	}

	/**
	 * https请求前海方法
	 * @param url
	 * @param bs
	 * @return
	 * @throws Exception
	 */
	public static String sendPostHttpsQh(String url, String bs) throws Exception {
		logger.info("请求URL地址:[" + url + "]\r\n请求报文:" + bs + "");
		String response = "";
		javax.net.ssl.HttpsURLConnection conn = null;
		OutputStream outStream = null;
		InputStream inStream = null;
		ByteArrayOutputStream byteArray = null;
		try {
			conn = (javax.net.ssl.HttpsURLConnection) new URL(url).openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);// 是否输入参数
			conn.setRequestProperty("Content-Type","application/json; charset=utf-8");
			conn.setConnectTimeout(60 * 1000);
			outStream = conn.getOutputStream();
			outStream.write(bs.getBytes(StandardCharsets.UTF_8));// 输入参数
			inStream = conn.getInputStream();
			byteArray = new ByteArrayOutputStream();
			int byteData = 0;
			while ((byteData = inStream.read()) != -1) {
				byteArray.write(byteData);
			}
			inStream.close();
			byte[] bytes = byteArray.toByteArray();
			response = new String(bytes, "utf-8");
		} catch (Exception e) {
			logger.error("与前海交互数据异常：" + e);
			throw new Exception("与前海交互数据异常：" , e);
		} finally {
			if (outStream != null) {
				outStream.close();
			}
			if (byteArray != null) {
				byteArray.close();
			}
		}
		logger.info("返回报文:" + response);
		return response;
	}

}
