package com.friuno.image;

import android.net.Uri;

import com.friuno.credentials.APICredentials;
import com.friuno.http.HTTPClient;

//import org.apache.http.HttpResponse;
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.mime.HttpMultipartMode;
//import org.apache.http.entity.mime.MultipartEntity;
//import org.apache.http.entity.mime.content.FileBody;
//import org.apache.http.entity.mime.content.StringBody;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStreamReader;

/**
 * Created by GodwinRoseSamuel on 16-11-2017.
 */
public class ImageSearch {

//    public static String getImage(Uri imagePath) {
//        StringBuffer result = null;
//        HttpClient httpclient = HTTPClient.getInstance();
//        HttpPost request = new HttpPost(APICredentials.FACE_DETECTION_ENDPOINT);
//        try {
//            MultipartEntity multipartEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
//            multipartEntity.addPart("file", new FileBody(new File(imagePath.getPath())));
//            multipartEntity.addPart("apikey", new StringBody(APICredentials.HP_IDOL_ACCESS_KEY));
//            request.setEntity(multipartEntity);
//
//            HttpResponse response = httpclient.execute(request);
//            System.out.println("Response Code : " + response.getStatusLine());
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
