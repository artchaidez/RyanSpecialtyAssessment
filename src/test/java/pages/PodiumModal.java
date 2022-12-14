package pages;

import autoFramework.UIBase;
import org.openqa.selenium.By;
import org.testng.Assert;
import webTestFramework.SeleniumControl;

public class PodiumModal extends UIBase {

    // Objects are private final
    private final SeleniumControl firstLocation = new SeleniumControl(By.xpath("//*[@class = 'LocationContainer StaggerFadeIn3 LocationContainer--desktop']"));

    private final SeleniumControl nameTextInput = new SeleniumControl(By.xpath("//*[@id= 'Name']"));

    private final SeleniumControl nameCheckMark = new SeleniumControl(By.xpath("//*[@class='TextInput']//*[@class='checkmark']"));

    private final SeleniumControl mobileNumberTextInput = new SeleniumControl(By.xpath("//*[@id= 'Mobile Phone']"));

    private final SeleniumControl mobileNumberCheckmark = new SeleniumControl(By.xpath("//*[@class='TextInput TextInput--tel']//*[@class='checkmark']"));

    private final SeleniumControl messageTextInput = new SeleniumControl(By.xpath("//*[@id= 'Message']"));

    private final SeleniumControl sendButtonValid = new SeleniumControl(By.xpath("//*[@class= 'SendButton SendButton--valid']"));

    private final SeleniumControl subjectTermsLink = new SeleniumControl(By.className("terms"));

    private final SeleniumControl termsLink = new SeleniumControl(By.xpath("//*[text()='Terms of Service']"));

    private final SeleniumControl returnArrowBtn = new SeleniumControl((By.xpath("//*[@class='SendSmsPage__ArrowIcon']")));

    private final SeleniumControl locationText = new SeleniumControl(By.xpath("//*[@class='SendSmsPage__CurrentLocationName']"));

    private final SeleniumControl locationSearchBar = new SeleniumControl(By.xpath("//*[@name='Search Locations']"));

    private final SeleniumControl messageCharCount = new SeleniumControl(By.xpath("//*[contains(@stroke, '#3074dc')]"));

    private final SeleniumControl clearLocationSearchBar = new SeleniumControl(By.xpath("//*[@class='SearchInput__Reset']"));

    // Methods are public
    public void SelectFirstLocation() throws Exception
    {
        firstLocation.Click(5);
    }

    public String GetFirstLocationText() {
        return firstLocation.getText();
    }

    public void VerifySearchBarInputCorrect(String data) throws Exception
    {
        Assert.assertTrue(GetFirstLocationText().contains(data));
    }

    public void VerifyNameTextInputExists()
    {
        nameTextInput.IsVisible(5);
    }

    public void SetTextInNameInput(String data, int Max_Retries, Boolean escape) throws Exception
    {
        nameTextInput.SetText(data, Max_Retries, escape);
    }

    public void VerifyNameCheckmarkExists()
    {
        nameCheckMark.IsVisible(5);
    }

    public void SetMobileNumberInput(String data, int Max_Retries, Boolean escape) throws Exception
    {
        mobileNumberTextInput.SetText(data, Max_Retries, escape);
    }

    public void VerifyMobileNumberCheckmarkExists()
    {
        mobileNumberCheckmark.IsVisible(5);
    }

    public void SetMessageInput(String data, int Max_Retries, Boolean escape) throws Exception
    {
        messageTextInput.SetText(data, Max_Retries, escape);
    }

    public void VerifyMessageHasInput()
    {
        String emptyMessageLocator = "M 50 0 A 50 50 0 0 1 50 0";
        String messageAttribute = messageCharCount.getAttribute("d");
        Assert.assertNotEquals(messageAttribute, emptyMessageLocator);
    }

    public void VerifyAllInputsComplete()
    {
        sendButtonValid.IsVisible(5);
    }

    public void ClickOnTermsButton() throws Exception
    {
        subjectTermsLink.Click(5);
    }

    public void VerifyOnSubjectTermsPage()
    {
        termsLink.IsVisible(5);
    }

    /** Click on arrow on message modal to return to location list modal */
    public void ClickOnReturnArrowBtn() throws Exception
    {
        returnArrowBtn.Click(5);
    }

    public String GetLocationInMessageModal()
    {
        return locationText.getText();
    }

    public void VerifyLocationSearchBar()
    {
        locationSearchBar.IsVisible(5);
    }

    public void SetLocationSearchBarText(String zipAddress) throws Exception
    {
        if (clearLocationSearchBar.IsVisible(5))
            ClickClearSearchBar();
        locationSearchBar.SetText(zipAddress, 5, null);
    }

    public void VerifyCorrectLocationOpened(String data) {
        Assert.assertEquals(GetLocationInMessageModal(), data);
    }

    public void ClickClearSearchBar() throws Exception
    {
        clearLocationSearchBar.Click(5);
    }

}
