
package logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class FrameworkLogger {

    private FrameworkLogger() {
        // Prevent object creation
    }

    public static Logger getLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }
}