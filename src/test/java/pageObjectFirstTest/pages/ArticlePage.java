package pageObjectFirstTest.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArticlePage {
    BaseFunctions baseFunctions;

    private static final Logger LOGGER = LogManager.getLogger(ArticlePage.class);

    public ArticlePage(BaseFunctions bs) {
        this.baseFunctions = bs;
    }
}
