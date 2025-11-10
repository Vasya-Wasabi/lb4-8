package system;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Service class for centralized application logging.
 * Provides static methods for info, warning, and error logs.
 * Errors are additionally sent via ErrorMailer.
 */
public class LogService {

    /** Logger instance from Log4j. */
    private static final Logger log = LogManager.getLogger(LogService.class);

    /** Logs informational messages. */
    public static void info(String msg) { log.info(msg); }

    /** Logs warning messages. */
    public static void warn(String msg) { log.warn(msg); }

    /** Logs error messages and sends them by email. */
    public static void error(String msg,  Throwable e) {
        log.error(msg, e);
        ErrorMailer.sendError(msg, e);
    }
}
