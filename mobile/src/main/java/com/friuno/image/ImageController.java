package com.friuno.image;


//import org.apache.commons.codec.binary.Base64;

import android.util.Log;

import com.friuno.credentials.DBCredentials;
import com.friuno.http.HTTPClient;
import com.friuno.util.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by GodwinRoseSamuel on 15-11-2017.
 */
public class ImageController {

    private static final String TAG = "ImageController";
    private final HTTPClient httpClient = new HTTPClient();

//    public void verifyImage(final String imageJSON) {
//        try {
//            JSONObject image = new JSONObject(imageJSON);
//            JSONArray faceArray = image.getJSONArray("face");
//            JSONObject coordinates = faceArray.getJSONObject(0);
//            String left = coordinates.getString("left");
//            String top = coordinates.getString("top");
//            String width = coordinates.getString("width");
//            String height = coordinates.getString("height");
//            checkImage(left, top, width, height);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void checkImage(final String left, final String top, final String width, final String height) {
////        insertImageParams(left, top, width, height);
//        String searchResponse = searchImage(left, top, width, height);
//        try {
//            JSONObject searchObject = new JSONObject(searchResponse);
//            if (searchObject.getString("documents") != null) {
//                final String username = searchObject.getJSONArray("documents").getJSONObject(0).getJSONObject("users").getJSONObject("user").getString("name");
//                final String deviceID = searchObject.getJSONArray("documents").getJSONObject(0).getString("id");
//                Log.d(TAG, "<--username: " + username + " ----deviceID:" + deviceID + "---->");
//                switchValidator(username, deviceID);
//            } else {
//                Log.e(TAG, "<---No user found---->");
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void switchValidator(final String username, final String deviceID) {
//        String switchesAllowedResponse = getSwitchesAllowed(deviceID);
//        Map<String, String> switchesAllowedMap = new HashMap<String, String>();
//        try {
//            JSONObject switchesAllowedObject = new JSONObject(switchesAllowedResponse);
//            if (switchesAllowedObject.getString("documents") != null) {
//                final JSONObject switchedAllowedObject = switchesAllowedObject.getJSONArray("documents").getJSONObject(0).getJSONObject("users").getJSONObject("user").getJSONObject("switches-allowed");
//                switchesAllowedMap.put(Constants.SWITCH_1, switchedAllowedObject.getString("switch1"));
//                switchesAllowedMap.put(Constants.SWITCH_2, switchedAllowedObject.getString("switch2"));
//                switchesAllowedMap.put(Constants.SWITCH_3, switchedAllowedObject.getString("switch3"));
//                switchesAllowedMap.put(Constants.SWITCH_4, switchedAllowedObject.getString("switch4"));
//                switchesAllowedMap.put(Constants.SWITCH_5, switchedAllowedObject.getString("switch5"));
//                switchesAllowedMap.put(Constants.SWITCH_6, switchedAllowedObject.getString("switch6"));
//                switchesAllowedMap.put(Constants.SWITCH_7, switchedAllowedObject.getString("switch7"));
//                switchesAllowedMap.put(Constants.SWITCH_8, switchedAllowedObject.getString("switch8"));
//                switchesAllowedMap.put(Constants.SWITCH_9, switchedAllowedObject.getString("switch9"));
//                switchesAllowedMap.put(Constants.SWITCH_10, switchedAllowedObject.getString("switch10"));
//
////                HomeFragment.switchedAllowedHandler.obtainMessage(HomeFragment.RECIEVE_SWITCHES_ALLOWED_ACTION, switchesAllowedMap).sendToTarget();
//            } else {
//                Log.e(TAG, "<---Unable to access switches---->");
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }

//    public void insertImageParams(final String left, final String top, final String width, final String height) {
//        String deviceId = "FRIUNO" + (int) Math.round(Math.random() * 999999);
//        String data = "{\"id\": \"" + deviceId + "\",\"users\": {\"user\": {\"name\": \"Godwin\",\"image-param\": {\"left\": \"" + left + "\",\"top\":\"" + top + "\",\"width\": \"" + width + "\",\"height\": \"" + height + "\"}}}}";
//        System.out.println("<----insertImage--->" + data);
//        final String response = httpClient.receiveDBPOSTResponse(DBCredentials.getInsertEndpoint(), data);
//        System.out.println("<----insertImage response--->" + response);
//    }

//    public String searchImage(final String left, final String top, final String width, final String height) {
//        String data = "{\"query\":\"<users><user><image-param><left>" + left + "</left><top>" + top + "</top><width>" + width + "</width><height>" + height + "</height></image-param></user></users>\", \"docs\":\"20\", \"offset\":\"0\", \"list\":{\"document\":\"yes\"}}";
//        System.out.println("<----searchImage--->" + data);
//        final String response = httpClient.receiveDBPOSTResponse(DBCredentials.getSearchEndpoint(), data);
//        System.out.println("<----searchImage response--->" + response);
//        return response;
//    }
//
//    public String getSwitchesAllowed(final String deviceID) {
//        String data = "{\"query\":\"<id>" + deviceID + "</id>\", \"docs\":\"20\", \"offset\":\"0\", \"list\":{\"document\":\"yes\"}}";
//        System.out.println("<----getSwitchesAllowed--->" + data);
//        final String response = httpClient.receiveDBPOSTResponse(DBCredentials.getSearchEndpoint(), data);
//        System.out.println("<----getSwitchesAllowed response--->" + response);
//        return response;
//    }
}
