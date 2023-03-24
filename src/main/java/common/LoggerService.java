package common;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerService {
    public static Logger createLogger(String className) {
        Logger logger = Logger.getLogger(className);
        FileHandler logHandler = null;
        try {
            logHandler = new FileHandler(className + "-log.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert logHandler != null;
        logHandler.setFormatter(new SimpleFormatter());
        logHandler.setLevel(Level.ALL);
        logger.setLevel(Level.ALL);
        logger.addHandler(logHandler);
        return logger;
    }
}
