package qa.tools.testraill;

import lombok.NonNull;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.codehaus.plexus.logging.console.ConsoleLogger;
import qa.tools.testraill.core.Credentials;
import qa.tools.testraill.core.CustomLogger;
import qa.tools.testraill.requests.*;

public class TestRail {
    private final String DEFAULT_PATH = "index.php?/api/v2/";
    private static String token = null;
    private static String url;
    private static String appName;

    public TestRail(@NonNull Credentials credentials) {
        new CustomLogger();
        if (!credentials.getBaseUrl().endsWith("/")) {
            credentials.setBaseUrl(credentials.getBaseUrl().concat("/"));
        }

        credentials.setBaseUrl(credentials.getBaseUrl().concat(DEFAULT_PATH));
        token = credentials.token();
        url = credentials.getBaseUrl();
        appName = credentials.getAppName();
    }

    public TestRail(@NonNull Credentials credentials, Level level) {
        mode(level);

        if (!credentials.getBaseUrl().endsWith("/")) {
            credentials.setBaseUrl(credentials.getBaseUrl().concat("/"));
        }

        credentials.setBaseUrl(credentials.getBaseUrl().concat(DEFAULT_PATH));
        token = credentials.token();
        url = credentials.getBaseUrl();
        appName = credentials.getAppName();
    }

    private void mode(Level level){
        new CustomLogger(level);
        CustomLogger.log.info("New level ".concat(level.name()));
    }

    public static String token() {
        if (token == null)
            throw new RuntimeException("User not authorize in the TR");
        else
            return token;
    }

    public static String url() {
        if (url == null)
            throw new RuntimeException("Default url is null");
        else
            return url;
    }

    public static String appName() {
        if (appName == null)
            throw new RuntimeException("Default appName is null");
        else
            return appName;
    }

    public Projects projects() {
        return new Projects();
    }

    public Suites suites() {
        return new Suites();
    }

    public Runs runs() {
        return new Runs();
    }

    public Cases cases() {
        return new Cases();
    }

    public Results results() {
        return new Results();
    }

    public Sections sections() {
        return new Sections();
    }

    public CaseFields caseFields() {
        return new CaseFields();
    }

    public ResultFields resultFields() {
        return new ResultFields();
    }
}
