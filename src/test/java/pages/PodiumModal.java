package pages;

import autoFramework.AutoBase;
import org.openqa.selenium.By;
import org.testng.Assert;
import webTestFramework.SeleniumControl;

public class PodiumModal extends AutoBase {

    SeleniumControl firstLocation = new SeleniumControl(By.xpath("//*[@class = 'LocationContainer StaggerFadeIn3 LocationContainer--desktop']"));

    SeleniumControl nameTextInput = new SeleniumControl(By.xpath("//*[@id= 'Name']"));

    SeleniumControl nameCheckMark = new SeleniumControl(By.xpath("//*[@class='TextInput']//*[@class='checkmark']"));

    SeleniumControl mobileNumberTextInput = new SeleniumControl(By.xpath("//*[@id= 'Mobile Phone']"));

    SeleniumControl mobileNumberCheckmark = new SeleniumControl(By.xpath("//*[@class='TextInput TextInput--tel']//*[@class='checkmark']"));

    SeleniumControl messageTextInput = new SeleniumControl(By.xpath("//*[@id= 'Message']"));

    SeleniumControl sendButtonValid = new SeleniumControl(By.xpath("//*[@class= 'SendButton SendButton--valid']"));

    SeleniumControl subjectTermsLink = new SeleniumControl(By.className("terms"));

    SeleniumControl termsLink = new SeleniumControl(By.xpath("//*[text()='Terms of Service']"));

    SeleniumControl returnArrowBtn = new SeleniumControl((By.xpath("//*[@class='SendSmsPage__ArrowIcon']")));

    SeleniumControl locationText = new SeleniumControl(By.xpath("//*[@class='SendSmsPage__CurrentLocationName']"));

    SeleniumControl addressText = new SeleniumControl(By.xpath("//*[@class='SendSmsPage__CurrentLocationAddress']"));

    SeleniumControl locationSearchBar = new SeleniumControl(By.xpath("//*[@name='Search Locations']"));

    SeleniumControl messageCharCount = new SeleniumControl(By.xpath("//*[contains(@stroke, '#3074dc')]"));

    SeleniumControl clearLocationSearchBar = new SeleniumControl(By.xpath("//*[@class='SearchInput__Reset']"));

    public void SelectFirstLocation() throws Exception
    {
        firstLocation.Click(5);
    }

    public String GetFirstLocationText() throws Exception
    {
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
        Assert.assertFalse(emptyMessageLocator.equals(messageAttribute));
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

    public void VerifyCorrectLocationOpened(String data) throws Exception
    {
        Assert.assertEquals(GetLocationInMessageModal(), data);
    }

    public void ClickClearSearchBar() throws Exception
    {
        clearLocationSearchBar.Click(5);
    }

}
