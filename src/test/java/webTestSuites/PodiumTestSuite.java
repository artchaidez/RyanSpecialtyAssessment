package webTestSuites;

import jdk.jfr.Description;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.Pages;
import webTestFramework.SeleniumControl;

public class PodiumTestSuite extends Pages {

    @BeforeMethod
    public void TestSetUp()
    {
        InitWebDriver();
    }

    @AfterMethod
    public void TestTearDown()
    {
        Quit();
        ResetSteps();
    }

    @Test (groups = {"smokeTest"})
    @Description("Test to switch to correct iframes and click on Podium icon.")
    public void TestClickPodiumButton() throws Exception
    {
        Step("Go to Podium Demo Website");
        GoToURL("https://demo.podium.tools/qa-webchat-lorw/");

        Step("Verify on site by finding podium bubble");
        podiumBubble.VerifyPodiumBubbleExists();
        Info("Found Podium bubble");

        Step("Switch to Podium bubble iframe");
        Sleep(1);
        podiumBubble.GoToPodiumBubbleFrame();

        Step("Click on Podium Bubble");
        podiumBubble.ClickOnPodiumButton();
        Info("Currently within Podium bubble");

        Step("Switch to Podium modal iframe");
        podiumBubble.GoToPodiumModalFrame();

        Step("Verify on Podium Modal");
        podiumBubble.VerifyOnModal();
        Info("Podium modal is visible");
    }

    @Test (groups = {"regressionTest"})
    @Description("Test to click on first location found in Location modal.")
    public void TestSelectFirstLocation() throws Exception
    {
        Step("Go to Podium Website");
        GoToURL("https://demo.podium.tools/qa-webchat-lorw/");

        Step("Go immediately to Podium modal");
        Sleep(1);
        podiumBubble.JumpToPodiumModal();

        Step("Verify on Podium modal");
        podiumModal.VerifyLocationSearchBar();
        Info("Podium modal open");

        Step("Click on first location in location list");
        podiumModal.SelectFirstLocation();

        Step("Verify on message modal");
        podiumModal.VerifyNameTextInputExists();
        Info("Within message modal as name text input was found.");
    }

    @Test (groups = {"regressionTest"})
    @Description("Test to confirm correct location is being clicked in the modal")
    public void TestScoreboardOremLocationExists() throws Exception
    {
        String location = "Scoreboard Sports - Orem";

        Step("Go to Podium Website");
        GoToURL("https://demo.podium.tools/qa-webchat-lorw/");

        Step("Go immediately to Podium modal");
        Sleep(1);
        podiumBubble.JumpToPodiumModal();

        Step(String.format("Verify %s is in location list", location));
        SeleniumControl locationBtn = new SeleniumControl(By.xpath(String.format("//*[text()= \"%s\"]", location)));
        locationBtn.IsVisible(5);
        Info(String.format("%s is in the modal", location));

        Step(String.format("Click on %s", location));
        locationBtn.Click(5);

        Step(String.format("Verify %s opened up", location));
        podiumModal.VerifyCorrectLocationOpened(location);
        Info(String.format("%s opened up", location));
    }

    @Test (groups = {"smokeTest"})
    @Description("Test to input data in all 3 fields of message modal")
    public void TestInputMessageData() throws Exception
    {
        String name = "Art";
        String telephone = "7777777777";
        String message = "Hello QA Tester";

        Step("Go to Podium Website");
        GoToURL("https://demo.podium.tools/qa-webchat-lorw/");

        Step("Go immediately to Podium modal");
        Sleep(1);
        podiumBubble.JumpToPodiumModal();

        Step("Verify on Podium modal");
        podiumModal.VerifyLocationSearchBar();
        Info("Podium modal open");

        Step("Click on first location in location list");
        podiumModal.SelectFirstLocation();

        Step("Input name in Name text input");
        podiumModal.SetTextInNameInput(name, 5, null);
        Sleep(1);

        Step("Verify text was put into name input");
        podiumModal.VerifyNameCheckmarkExists();
        Info("Message has text input");

        Step("Input phone number into mobile phone text input");
        podiumModal.SetMobileNumberInput(telephone, 5, null);
        Sleep(1);

        Step("Verify phone number was put into input");
        podiumModal.VerifyMobileNumberCheckmarkExists();

        Step("Input message in message text input");
        podiumModal.SetMessageInput(message, 5, null);
        Sleep(1);

        Step("Verify message has input");
        podiumModal.VerifyMessageHasInput();
        Info("Message has text input");

        Step("Verify all inputs have data");
        podiumModal.VerifyAllInputsComplete();
        Info("All inputs filled out and send button is valid");
    }

    @Test (groups = {"regressionTest"})
    @Description("Test to confirm subject and terms are opened in a new tab.")
    public void TestClickSubjectTerms() throws Exception
    {
        Step("Go to Podium Website");
        GoToURL("https://demo.podium.tools/qa-webchat-lorw/");

        Step("Go immediately to Podium modal");
        Sleep(1);
        podiumBubble.JumpToPodiumModal();

        Step("Verify on Podium modal");
        podiumModal.VerifyLocationSearchBar();
        Info("Podium modal open");

        Step("Click on 'use is subject to terms'");
        podiumModal.ClickOnTermsButton();

        Step("Switch to Terms and Service page");
        switchToNewlyOpenTab();

        Step("Verify on Terms and Service page by clicking on 'Terms of Service'");
        podiumModal.VerifyOnSubjectTermsPage();
        Info("Clicked on 'Terms of Service'");
    }

    @Test (groups = {"regressionTest"})
    @Description("Test to prove there is a bug with the return arrow in the message modal.")
    public void TestReturnButtonDoesNotWork() throws Exception
    {
        String message = "There is a bug with the return arrow on the message widget!";

        Step("Go to Podium Website");
        GoToURL("https://demo.podium.tools/qa-webchat-lorw/");

        Step("Go immediately to Podium modal");
        Sleep(1);
        podiumBubble.JumpToPodiumModal();

        Step("Verify on Podium modal");
        podiumModal.VerifyLocationSearchBar();
        Info("Podium modal open");

        Step("Click on first location in location list");
        podiumModal.SelectFirstLocation();

        Step("Verify on message modal");
        podiumModal.VerifyNameTextInputExists();
        Info("Within message modal as name text input was found.");

        Step("Click on return arrow");
        podiumModal.ClickOnReturnArrowBtn();

        Step("Verify message modal is still open by inputting text into message input");
        podiumModal.SetMessageInput(message, 5, null);
        Sleep(1);

        Step("Verify text was inputted into message input");
        podiumModal.VerifyMessageHasInput();
        Info("Message has text input");
    }

    @Test (groups = {"smokeTest"})
    @Description("Test to prove location searchbar works.")
    public void TestLocationSearchBar() throws Exception
    {
        String bountifulZIP = "84043";
        String bountifulStreet = "E Main St";
        String bountifulCity = "Lehi";
        String bountifulBuildingNum = "1402";
        String bountifulName = "Bountiful";

        Step("Go to Podium Website");
        GoToURL("https://demo.podium.tools/qa-webchat-lorw/");

        Step("Go immediately to Podium modal");
        Sleep(1);
        podiumBubble.JumpToPodiumModal();

        Step("Verify on Podium modal");
        podiumModal.VerifyLocationSearchBar();
        Info("Podium modal open");

        Step("Input Bountiful ZIP code into searchbar");
        podiumModal.SetLocationSearchBarText(bountifulZIP);
        Sleep(1);

        Step("Verify first location in list is Bountiful");
        podiumModal.VerifySearchBarInputCorrect(bountifulZIP);
        Info("Correctly searched for Bountiful ZIP");

        Step("Input Bountiful name into searchbar");
        podiumModal.SetLocationSearchBarText(bountifulName);
        Sleep(1);

        Step("Verify first location in list is Bountiful");
        podiumModal.VerifySearchBarInputCorrect(bountifulName);
        Info("Correctly searched for Bountiful name");

        Step("Input Bountiful building number into searchbar");
        podiumModal.SetLocationSearchBarText(bountifulBuildingNum);
        Sleep(1);

        Step("Verify first location in list is Bountiful");
        podiumModal.VerifySearchBarInputCorrect(bountifulBuildingNum);
        Info("Correctly searched for Bountiful building name");

        Step("Input Bountiful street name into searchbar");
        podiumModal.SetLocationSearchBarText(bountifulStreet);
        Sleep(1);

        Step("Verify first location in list is Bountiful");
        podiumModal.VerifySearchBarInputCorrect(bountifulStreet);
        Info("Correctly searched for Bountiful street name");

        Step("Input Bountiful city into searchbar");
        podiumModal.SetLocationSearchBarText(bountifulCity);
        Sleep(1);

        Step("Verify first location in list in Bountiful");
        podiumModal.VerifySearchBarInputCorrect(bountifulCity);
        Info("Correctly searched for Bountiful city");
    }
}
