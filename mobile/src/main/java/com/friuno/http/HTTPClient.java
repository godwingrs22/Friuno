package com.friuno.http;

//import android.os.StrictMode;
//
//import com.friuno.credentials.DBCredentials;
//
//import org.apache.http.HttpResponse;
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.DefaultHttpClient;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;

/**
 * Created by GodwinRoseSamuel on 25-07-2016.
 */
public class HTTPClient {
//
//    public static HttpClient httpclient;
//
//    public static HttpClient getInstance() {
//        if (httpclient == null) {
//            httpclient = new DefaultHttpClient();
//        }
//        return httpclient;
//    }
//
//    public String receiveDBPOSTResponse(final String url, final String data) {
//        StringBuffer result = null;
//        HttpClient httpclient = getInstance();
//        HttpPost request = new HttpPost(url);
//        try {
//            StringEntity input = new StringEntity(data);
//            request.setHeader("Authorization", "Basic " + DBCredentials.getEncodedAuthorization());
//            request.setEntity(input);
//            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//            StrictMode.setThreadPolicy(policy);
//            HttpResponse response = httpclient.execute(request);
//            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
//
//            result = new StringBuffer();
//            String line = "";
//            while ((line = rd.readLine()) != null) {
//                result.append(line);
//            }
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return result.toString();
//    }
}
