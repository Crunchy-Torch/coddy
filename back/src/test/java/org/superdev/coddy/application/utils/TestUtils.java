package org.superdev.coddy.application.utils;

public class TestUtils {

    private static final String SERVER_HOST = "localhost";

    private static final int SERVER_PORT = 8888;

    private static final String API_PREFIX = "/api/v1";

    private TestUtils() {

    }

    public static String getUrl(final String endpoint) {
        return "http://" + SERVER_HOST + ":" + SERVER_PORT + API_PREFIX + endpoint;
    }

}
