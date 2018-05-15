package org.crunchytorch.coddy.application.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public class TestUtils {

    private static final String API_PREFIX = "/api/v1";

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private TestUtils() {

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
    public static <T> T getObjectFromJson(final String jsonFile, Class<T> object) throws IOException {
        InputStream stream = null;
        try {
            stream = TestUtils.class.getClassLoader().getResourceAsStream(jsonFile);
            return MAPPER.readValue(stream, object);
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
    }

    public static <T> T getObjectFromString(final String s, TypeReference valueTypeRef) throws IOException {
        return MAPPER.readValue(s, valueTypeRef);
    }

}
