package pages;

import org.openqa.selenium.By;
import webTestFramework.SeleniumControl;

public class TrelloSignInPage {
    private final SeleniumControl logInMainPageBtn = new SeleniumControl(By.xpath("//*[@class='seg6b92uUYbLu1']"));

    private final SeleniumControl emailInput = new SeleniumControl(By.xpath("//*[@id='user']"));

    private final SeleniumControl continueBtn = new SeleniumControl(By.xpath("//*[@id='login']"));

    private final SeleniumControl passwordInput = new SeleniumControl(By.xpath("//*[@id = 'password']"));

    private final SeleniumControl logInBtn = new SeleniumControl(By.xpath("//*[@id = 'login-submit']"));


    public TrelloBoardPage trelloBoardPage;

    public TrelloSignInPage()
    {
        trelloBoardPage = new TrelloBoardPage();
    }

    public void ClickOnMainPageLogIn() throws Exception
    {
        logInMainPageBtn.Click(5);
    }

    public void SetEmailText(String email) throws Exception
    {
        emailInput.SetText(email, 5, false);
    }

    public void ClickOnMainPageContinue() throws Exception
    {
        continueBtn.Click(5);
    }

    public void SetPasswordText(String password) throws Exception
    {
        passwordInput.SetText(password, 5, false);
    }

    public void ClickOnLogInButton() throws Exception
    {
        logInBtn.Click(5);
    }

    // TODO: Should these methods being called be private? If public, test them?
    public void LogIntoAccountOnMainPage(String email, String password) throws Exception
    {
        ClickOnMainPageLogIn();
        SetEmailText(email);
        ClickOnMainPageContinue();
        SetPasswordText(password);
        ClickOnLogInButton();
    }
}
