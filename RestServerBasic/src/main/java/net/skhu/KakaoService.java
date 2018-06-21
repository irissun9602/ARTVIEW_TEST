package net.skhu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
@Service
public class KakaoService {

	public String getToken(String authorize_code) {
		final String AUTH_HOST = "https://kauth.kakao.com";
		final String tokenRequestUrl = AUTH_HOST + "/oauth/token";

		String CLIENT_ID = "10a7f5555967e1628375402721efa3b3"; // 해당 앱의 REST API KEY 정보. 개발자 웹사이트의 대쉬보드에서 확인 가능
		String REDIRECT_URI = "http://localhost:8080/RestServerBasic/api/kakaologin"; // 해당 앱의 설정된 uri. 개발자 웹사이트의 대쉬보드에서 확인 및 설정 가능
		String code = authorize_code; // 로그인 과정중 얻은 authorization code 값

		HttpsURLConnection conn = null;
		OutputStreamWriter writer = null;
		BufferedReader reader = null;
		InputStreamReader isr = null;

		try {
			final String params = String.format("grant_type=authorization_code&client_id=%s&redirect_uri=%s&code=%s",
					CLIENT_ID, REDIRECT_URI, code);

			final URL url = new URL(tokenRequestUrl);

			conn = (HttpsURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);

			writer = new OutputStreamWriter(conn.getOutputStream());
			writer.write(params);
			writer.flush();

			final int responseCode = conn.getResponseCode();
			System.out.println("\nSending 'POST' request to URL : " + tokenRequestUrl);
			System.out.println("Post parameters : " + params);
			System.out.println("Response Code : " + responseCode);

			isr = new InputStreamReader(conn.getInputStream());
			reader = new BufferedReader(isr);
			final StringBuffer buffer = new StringBuffer();
			String line;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}

			System.out.println(buffer.toString());

			return buffer.toString();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// clear resources
			if (writer != null) {
				try {
					writer.close();
				} catch (Exception ignore) {
				}
			}
			if (reader != null) {
				try {
					reader.close();
				} catch (Exception ignore) {
				}
			}
			if (isr != null) {
				try {
					isr.close();
				} catch (Exception ignore) {
				}
			}
		}

		return "";
	}
	
	
	
	 public  JsonNode getKakaoUserInfo(String token) {

			

		 final String RequestUrl = "https://kapi.kakao.com/v1/user/me";

		    

		    String CLIENT_ID = "10a7f5555967e1628375402721efa3b3"; // REST API KEY

		    String REDIRECT_URI = "http://localhost:8080/RestServerBasic/api/kakaoInfo"; // 리다이렉트 URI

		    String access = token; // 로그인 과정중 얻은 토큰 값



		    final HttpClient client = HttpClientBuilder.create().build();

		    final HttpPost post = new HttpPost(REDIRECT_URI);

		    

		    // add header

		    post.addHeader("Authorization", "Bearer " + access);

		    

		    JsonNode returnNode = null;

		    

		    try {

		      final HttpResponse response = client.execute(post);

		      final int responseCode = response.getStatusLine().getStatusCode();



		      System.out.println("\nSending 'POST' request to URL : " + REDIRECT_URI);

		      System.out.println("Response Code : " + responseCode);



		      //JSON 형태 반환값 처리

		      ObjectMapper mapper = new ObjectMapper();

		      returnNode = mapper.readTree(response.getEntity().getContent());

		      

		    } catch (UnsupportedEncodingException e) {

		      e.printStackTrace();

		    } catch (ClientProtocolException e) {

		      e.printStackTrace();

		    } catch (IOException e) {

		      e.printStackTrace();

		    } finally {

		        // clear resources

		    }

		    return returnNode;

		}



}
