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


    private boolean IsCardInColumn(String cardTitle, String listName)
    {
        String xpath = String.format("//*[text()='%s']/parent::div/following-sibling::div[1]//*[text()='%s']", listName, cardTitle);
        SeleniumControl card = new SeleniumControl(By.xpath(xpath));
        return card.IsVisible(5);
    }

    public void VerifyCardInColumn(String cardTitle, String listName)
    {
        Assert.assertTrue(IsCardInColumn(cardTitle, listName));
        Info(String.format("   Card '%s' is in list '%s'", cardTitle, listName));
    }

    public void VerifyNoCardsInColumn(String listName)
    {
        boolean noCards = false;
        String xpathChild = String.format("//*[text()='%s']/parent::div/parent::div//*[@class='list-cards u-fancy-scrollbar u-clearfix js-list-cards js-sortable ui-sortable']//child::*", listName);

        SeleniumControl card = new SeleniumControl(By.xpath(xpathChild));
        try {
            // Look for children in the list
            // If there are no children, it should be caught and log there are no cards
            card.IsVisible(5);

        }catch (Exception e)
        {
            noCards = true;
        }

        if (noCards)
        {
            Info(String.format("   '%s' has no cards", listName));
        } else {
            // If code hits here, the list has children and the card was not properly deleted
            Info(String.format("   FAILED: There is a card in '%s'", listName));
            Assert.assertFalse(true);
        }
    }

    public void VerifyCardWasDeletedNotArchived() throws Exception
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

        Info("   Click on 'Archived items'");
            archiveItemsBtn.Click(5);

        Info("   Verifying archive is empty");
            noCardsCheck.IsVisible(5);
    }

    public void VerifyOnSDETBoard()
    {
        sdetBoard.IsVisible(5);
        Info("   On SDET Board");
    }

    private void DropDownActions(String listName) throws Exception
    {
        String xpath = String.format("//*[text()='%s']/parent::div//*[@class='list-header-extras-menu dark-hover js-open-list-menu icon-sm icon-overflow-menu-horizontal']", listName);
        SeleniumControl dropDownListBtn = new SeleniumControl(By.xpath(xpath));
        dropDownListBtn.Click(5);
    }

    public void DropDownActionsAddCard(String cardTitle, String listName) throws Exception
    {
        String xpath = String.format("//*[text()='%s']/parent::div//*[@class='list-header-extras-menu dark-hover js-open-list-menu icon-sm icon-overflow-menu-horizontal']", listName);
        SeleniumControl dropDownListBtn = new SeleniumControl(By.xpath(xpath));
        dropDownListBtn.Click(5);

        SeleniumControl addCard = new SeleniumControl(By.xpath("//*[@class='js-add-card']"));
        addCard.Click(5);

        inputCardTitleText.SetText(cardTitle, 5, false);

        addCardBtn.Click(5);
    }
}
