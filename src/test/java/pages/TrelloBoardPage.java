package pages;

import autoFramework.UIBase;
import org.openqa.selenium.By;
import org.testng.Assert;
import webTestFramework.SeleniumControl;

import java.util.ArrayList;

public class TrelloBoardPage extends UIBase {

    // TODO: Create and organize three pages: Sign in Page, main board, card modal

    // this class needs card model object
    public TrelloCardPage trelloCardPage;

    public TrelloBoardPage()
    {
        trelloCardPage = new TrelloCardPage();
    }


    public void AddCardToColumn(String listName, String cardTitle) throws Exception
    {
        String xpath = String.format("//*[text()='%s']/parent::div/following-sibling::div[2]//*[text()='Add a card']", listName);
        SeleniumControl list = new SeleniumControl(By.xpath(xpath));
        list.Click(5);

        SeleniumControl inputCardTitle = new SeleniumControl(By.xpath("//*[@class= 'list-card-composer-textarea js-card-title']"));
        inputCardTitle.SetText(cardTitle, 5, false);

        SeleniumControl addCardBtn = new SeleniumControl(By.xpath("//*[contains(@value, 'Add card')]"));
        addCardBtn.Click(5);
    }

    // TODO: Use same xpath to delete a card. Do this at the end of every test making a new card
    public void OpenCard(String cardTitle, String listName) throws Exception
    {
        // TODO: This is used in ReturnCardInColumn; refactor
        String xpath = String.format("//*[text()='%s']/parent::div/following-sibling::div[1]//*[text()='%s']", listName, cardTitle);
        SeleniumControl newCard = new SeleniumControl(By.xpath(xpath));
        newCard.Click(5);
    }

    public void DeleteCard(String cardTitle, String listName) throws Exception
    {
        String xpath = String.format("//*[text()='%s']/parent::div/following-sibling::div[1]//*[text()='%s']", listName, cardTitle);
        SeleniumControl newCard = new SeleniumControl(By.xpath(xpath));
        newCard.Click(5);

        trelloCardPage.DeleteCard();
    }



    // TODO: VerifyCardNotInColumn not working
    private boolean ReturnCardInColumn(String cardTitle, String listName)
    {
        String xpath = String.format("//*[text()='%s']/parent::div/following-sibling::div[1]//*[text()='%s']", listName, cardTitle);
        SeleniumControl card = new SeleniumControl(By.xpath(xpath));
        return card.IsVisible(5);
    }

    public void VerifyCardInColumn(String cardTitle, String listName) throws Exception
    {
        Assert.assertTrue(ReturnCardInColumn(cardTitle, listName));
    }

    // TODO: Fix
    public void VerifyCardNotInColumn(String cardTitle, String listName)
    {
        String xpath = String.format("//*[text()='%s']/parent::div/following-sibling::div[1]//*[text()='%s']", listName, cardTitle);
        SeleniumControl card = new SeleniumControl(By.xpath(xpath));
        Assert.assertTrue(card.IsNotVisible(5));
    }

}
