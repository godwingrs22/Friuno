package com.friuno.credentials;

/**
 * Created by GodwinRoseSamuel on 15-01-2016.
 */
public class DBCredentials {
    private static final String HOSTNAME = "api-eu.clusterpoint.com";
    private static final String USERNAME = "godwingrs22@gmail.com";
    private static final String PASSWORD = "God2help";
    private static final String ACCOUNT_ID = "982";
    private static final String DB_NAME = "FRIUNO";
    private static final String PROTOCOL = "https";
    private static final String INSERT_API = ".json";
    private static final String SELECT_API = "_search.json";

    public static String getInsertEndpoint() {
        return PROTOCOL + "://" + HOSTNAME + "/" + ACCOUNT_ID + "/" + DB_NAME + INSERT_API;
    }

    public static String getSearchEndpoint() {
        return PROTOCOL + "://" + HOSTNAME + "/" + ACCOUNT_ID + "/" + DB_NAME + "/" + SELECT_API;
    }

    public static String getEncodedAuthorization() {
        String encode = USERNAME + ":" + PASSWORD;
//        return Base64.encodeToString(encode.getBytes(), Base64.DEFAULT);
        return "Z29kd2luZ3JzMjJAZ21haWwuY29tOkdvZDJoZWxw";
    }
}
