package delfiTestPageObject.helpers;

import com.google.common.base.CharMatcher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NumberFromTextHelper {

    private static final Logger LOGGER = LogManager.getLogger(NumberFromTextHelper.class);

    public Integer getIntegerFromString(String text) {
        LOGGER.info("Getting number from text");
        text = CharMatcher.digit().retainFrom(text);
        return Integer.valueOf(text);
    }

    public Long getLongFromString(String text) {
        LOGGER.info("Getting number from text");
        text = CharMatcher.digit().retainFrom(text);
        return Long.valueOf(text);
    }
}
