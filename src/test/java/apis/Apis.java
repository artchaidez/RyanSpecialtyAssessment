package apis;

import autoFramework.AutoLogger;

/** Class to hold all APIs objects; then be extended by API test suites. */
public class Apis extends AutoLogger {

    public OffersApi offersApi;

    public Apis()
    {
        offersApi = new OffersApi();
    }

}

