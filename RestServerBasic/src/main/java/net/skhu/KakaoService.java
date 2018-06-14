package net.skhu;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class KakaoService {

	
	public static JsonNode getAccessToken(String autorize_code){ 

	    final String RequestUrl = "https://kauth.kakao.com/oauth/token";



	    final List<NameValuePair> postParams = new ArrayList<NameValuePair>();

	    postParams.add(new BasicNameValuePair("grant_type", "authorization_code"));

	    postParams.add(new BasicNameValuePair("client_id", RestApiKey));    // REST API KEY

	    postParams.add(new BasicNameValuePair("redirect_uri", Redirect_URI));    // 리다이렉트 URI

	    postParams.add(new BasicNameValuePair("code", autorize_code));    // 로그인 과정중 얻은 code 값



	    final HttpClient client = HttpClientBuilder.create().build();

	    final HttpPost post = new HttpPost(RequestUrl);

	    JsonNode returnNode = null;

	

	    try {

	      post.setEntity(new UrlEncodedFormEntity(postParams));

	      final HttpResponse response = client.execute(post);

	      final int responseCode = response.getStatusLine().getStatusCode();



	      System.out.println("\nSending 'POST' request to URL : " + RequestUrl);

	      System.out.println("Post parameters : " + postParams);

	      System.out.println("Response Code : " + responseCode);

	     

	      /JSON 형태 반환값 처리

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
