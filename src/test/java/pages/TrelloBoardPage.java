package pages;

import autoFramework.UIBase;
import org.openqa.selenium.By;
import org.testng.Assert;
import webTestFramework.SeleniumControl;

public class TrelloBoardPage extends UIBase {

    private final SeleniumControl inputCardTitleText = new SeleniumControl(By.xpath("//*[@class= 'list-card-composer-textarea js-card-title']"));

    private final SeleniumControl addCardBtn = new SeleniumControl(By.xpath("//*[contains(@value, 'Add card')]"));

    private final SeleniumControl showMenuBtn = new SeleniumControl(By.xpath("//*[@class='show-sidebar-button-react-root']"));

    private final SeleniumControl moreInMenuBtn = new SeleniumControl(By.xpath("//*[@class='board-menu-navigation-item-link js-open-more']"));

    private final SeleniumControl archiveItemsBtn = new SeleniumControl(By.xpath("//*[@class='board-menu-navigation-item-link js-open-archive']"));

    private final SeleniumControl noCardsCheck = new SeleniumControl(By.xpath("//*[@class='empty-list js-empty-message']"));

    private final SeleniumControl sdetBoard = new SeleniumControl(By.xpath("//*[@class='js-board-editing-target board-header-btn-text']"));

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

        inputCardTitleText.SetText(cardTitle, 5, false);

        addCardBtn.Click(5);
    }

    public void OpenCard(String cardTitle, String listName) throws Exception
    {
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
    public void VerifyNoCardsInColumn(String columnName)
    {

        // //*[@class='list-header-num-cards hide js-num-cards']
        // //*[text()='Working']/parent::div//*[text()='0 cards']
        // //*[text()='%s']/parent::div//*[@class='list-header-num-cards hide js-num-cards']
        String xpath = String.format("//*[text()='Working']/parent::div//*[text()='0 cards']", columnName);
        SeleniumControl card = new SeleniumControl(By.xpath(xpath));
        String word = card.getAttribute("class");
        //Assert.assertEquals(card.getText(), "0 cards");
    }

    public void VerifyCardWasDeleted() throws Exception
    {
        Info("   Verify card is not archived");
            /* BUG: Sometimes Menu is open. Does not create issues interacting with List, but does when trying to
            * check archive. Try/catch is to take proper steps to correctly check archive regardless of this bug.*/
            try
            {
                moreInMenuBtn.IsVisible(5);

            } catch(Exception e) {
                Info("Open Menu");
                showMenuBtn.Click(5);
            }

        Info("   Click on 'More' option in menu");
            moreInMenuBtn.Click(5);

        Info("    Click on 'Archived items'");
            archiveItemsBtn.Click(5);

        Info("   Verifying archive is empty");
            noCardsCheck.IsVisible(5);
    }

    public void VerifyOnSDETBoard()
    {
        sdetBoard.IsVisible(5);
        Info("   On SDET Board");
    }

}
