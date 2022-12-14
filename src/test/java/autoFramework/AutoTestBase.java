package autoFramework;

import apis.Apis;
import apis.OffersApi;
import pages.Pages;
import pages.PodiumBubble;
import pages.PodiumModal;

public class AutoTestBase extends AutoLogger {

    protected Apis apis;
    protected Pages pages;

    public AutoTestBase()
    {
        apis = new Apis();
        pages = new Pages();
    }
}
