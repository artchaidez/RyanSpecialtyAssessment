package autoFramework;


import pages.Pages;

// TODO: Need a class that has both API and Pages
public class AutoTestBase extends AutoLogger {

    protected Pages pages;

    public AutoTestBase()
    {
        pages = new Pages();
    }
}
