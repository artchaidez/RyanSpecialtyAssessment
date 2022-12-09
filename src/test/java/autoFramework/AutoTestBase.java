package autoFramework;

import apis.OffersApi;

/** Class to hold all test objects, then be extended by test suites. */
public class AutoTestBase extends AutoLogger{

    //protected WeakAssert weakAssert;
    protected OffersApi offersApi;

    public AutoTestBase()
    {
        //weakAssert = new WeakAssert();
        offersApi = new OffersApi();
    }

}

