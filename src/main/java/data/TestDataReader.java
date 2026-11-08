package data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestDataReader {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> List<T> readList(
            String fileName, Class<T> type) {

        String path = "testdata/" + fileName;

        try (InputStream input =
                     TestDataReader.class.getClassLoader()
                             .getResourceAsStream(path)) {

            if (input == null) {
                throw new RuntimeException(
                        "Test data file not found: " + path);
            }

            return mapper.readValue(
                    input,
                    mapper.getTypeFactory()
                            .constructCollectionType(List.class, type));

        } catch (IOException e) {
            throw new RuntimeException(
                    "Failed to read test data: " + path, e);
        }
    }
}