package qa.tools.testraill.core;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustomLogger {
    public static Logger log;

    static {
        log = LogManager.getLogger(CustomLogger.class);
    }
}
