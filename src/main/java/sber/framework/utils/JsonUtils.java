package sber.framework.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonUtils {
    public static <T> T deserializeFile(String filepath, Class<T> tClass) {
        try {
            return new ObjectMapper().readValue(new File(filepath), tClass);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
