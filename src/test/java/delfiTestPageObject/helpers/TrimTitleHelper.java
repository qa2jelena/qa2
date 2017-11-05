package delfiTestPageObject.helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TrimTitleHelper {

    private static final Logger LOGGER = LogManager.getLogger(TrimTitleHelper.class);

    public String trimTitle(String title) {
        LOGGER.info("Trimming title if necessary");
        if (title.contains(": комментарии 1-")) {
            title = title.substring(0, title.indexOf(": комментарии 1-"));
        }
        return title;
    }
}
