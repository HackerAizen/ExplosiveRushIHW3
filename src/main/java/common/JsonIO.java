package common;

import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import errors.AgentException;
import errors.JsonValidationException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class JsonIO {

    public static void writeError(AgentException e) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File("errors.txt"), e);
        } catch (IOException ex) {
            System.err.println("unable to write error message");
            ex.printStackTrace();
        }
    }

    public static void fatalError(AgentException e) {
        writeError(e);
        System.err.println("Fatal error, check error.txt file");
        System.exit(1);
    }

    public static <T> T readFromFile(String fileName, Class<T> clazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            T data = objectMapper.readValue(ClassLoader.getSystemResourceAsStream(fileName), clazz);
            System.out.println("Loaded " + data);
            return data;
        } catch (DatabindException e) {
            System.err.printf("invalid file %s format\n", fileName);
            e.printStackTrace();
            try {
                objectMapper.writeValue(new File("errors.txt"), new JsonValidationException(fileName, e.getLocation().offsetDescription()));
            } catch (IOException ex) {
                System.err.println("unable to write error message");
                ex.printStackTrace();
            }
            System.exit(1);
        } catch (IOException e) {
            System.err.printf("open file %s error\n", fileName);
            e.printStackTrace();
            try {
                objectMapper.writeValue(new File("errors.txt"), new JsonValidationException(fileName, "NA"));
            } catch (IOException ex) {
                System.err.println("unable to write error message");
                ex.printStackTrace();
            }
            System.exit(1);
        }
        return null;
    }
}
