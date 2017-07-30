package org.crunchytorch.coddy.application.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public class TestUtils {

    private static final String SERVER_HOST = "localhost";

    private static final int SERVER_PORT = 8888;

    private static final String API_PREFIX = "/api/v1";

    private TestUtils() {

    }

    /**
     * @param endpoint the endpoint you want to request
     * @return the built url with the given endpoint
     */
    public static String getUrl(final String endpoint) {
        return "http://" + SERVER_HOST + ":" + SERVER_PORT + API_PREFIX + endpoint;
    }

    /**
     * Method to deserialize JSON content from given json file into given Java type.
     *
     * @param jsonFile the json file to read
     * @param object   the object mapped to the json file
     * @param <T>      the type of the object
     * @return an object instance by the data contains inside the json file
     * @throws IOException if the file doesn't exist
     */
    public static <T> T getObjecFromJson(final String jsonFile, Class<T> object) throws IOException {
        InputStream stream = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            stream = TestUtils.class.getClassLoader().getResourceAsStream(jsonFile);
            return mapper.readValue(stream, object);
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
    }

}
