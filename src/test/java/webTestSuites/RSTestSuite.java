package webTestSuites;

import autoFramework.AutoTestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import webTestFramework.SeleniumControl;

import java.util.ArrayList;

public class RSTestSuite extends AutoTestBase {

    private String email = null;
    private String password = null;
    @BeforeMethod
    public void TestSetUp()
    {
        pages.InitWebDriver();
        email = System.getenv("trelloEmail");
        password = System.getenv("trelloPassword");

    }

    @AfterMethod
    public void TestTearDown()
    {
        pages.Quit();
        ResetSteps();
    }

    // TODO: ** Extra Credit ** Click on hover edit icon that appears on card; currently only clicking on card
    // TODO: ** Extra Credit ** click 'Add a card' by three-dot dropdown menu; currently only clicking on card

    /** Scenario 1: Create New Card in To Do
     Given I have a Trello Board with 3 Lists (To Do, Working, and Done)
     When I need to add a new card to the To Do list with the title of "new card"
     And a description of "this is a new card"
     Then I am able to see it in the To Do column */
    @Test
    public void TestCreateNewCardInTodo() throws Exception {

        String cardTitle = "new card";
        String listNameTodo = "To Do";
        String cardDescription = "this is a new card";
/*
        Step("Go to Trello Page");
            pages.GoToURL("https://trello.com/b/0QEdvItb/rt-sdet");

        // TODO: Verify all these steps
        Step("Log into Trello");
            pages.trelloPage.LogIntoAccountOnMainPage(email, password);

        Step("Add a new card titled 'new card' to list 'To Do'");
            pages.trelloPage.AddCardToColumn(listNameTodo, cardTitle);

        Step("Find the newly created card and open it to edit the card");
            pages.trelloPage.OpenCard(cardTitle, listNameTodo);

        // TODO: Fails here. Need correct Xpath
        // //*[@class='field field-autosave js-description-draft description card-description']
        // //*[@class='description-content js-desc-content']
        // //*[@class='description-content js-desc-content']//*[@class='field field-autosave js-description-draft description card-description']
        // //*[@class='u-gutter']
        // can click; cannot SetText()
        // //*[@class='description-fake-text-area js-description-fake-text-area hide-on-edit js-edit-desc js-hide-with-draft']
        Step("In the card modal, add the description: this is a new card");
            SeleniumControl cardModalDescription = new SeleniumControl(By.xpath("//*[@class='description-content js-desc-content']//*[@class='field field-autosave js-description-draft description card-description']"));
            cardModalDescription.SetText(cardDescription, 5, false);

        Step("Save the description");
            SeleniumControl saveDescription = new SeleniumControl(By.xpath("//*[@class= 'nch-button nch-button--primary confirm mod-submit-edit js-save-edit']"));
            saveDescription.Click(5);

        Step("Close the modal");
            pages.trelloPage.CloseCard();

        // TODO: Verify this was saved: open up card and check description; need method using parent xpath
        Step("Open the card again and confirm the description is correct");*/


    }

    /** Scenario 2: Move Card to Working
    Given I have a card titled "new card" in the "To Do" column
    When I want to work on it
    Then I am able to move it into the "working" column
    And see that it stays there */
    @Test
    public void TestMoveCardIntoWorkingColumn() throws Exception
    {
        String listNameTodo = "To Do";
        String cardTitle = "new card";
        String listNameWorking = "Working";

        Step("Go to Trello Page");
            pages.GoToURL("https://trello.com/b/0QEdvItb/rt-sdet");

        // TODO: Verify all these steps
        Step("Log into Trello");
            pages.trelloSignInPage.LogIntoAccountOnMainPage(email, password);

        Step(String.format("Add '%s' card to list '%s'", cardTitle, listNameTodo));
            pages.trelloSignInPage.trelloBoardPage.AddCardToColumn(listNameTodo, cardTitle);

        Step(String.format("Open '%s'", cardTitle));
            pages.trelloSignInPage.trelloBoardPage.OpenCard(cardTitle, listNameTodo);

        Step("In the modal, under Actions, click on Move to open the 'Move Card' modal and move to list Working");
            pages.trelloSignInPage.trelloBoardPage.trelloCardPage.MoveCardToColumnUnderAction(listNameWorking);

        Step("Verify card was moved to working");
            pages.trelloSignInPage.trelloBoardPage.VerifyCardInColumn(cardTitle, listNameWorking);

    }

    /** Scenario 3:  Add Checklist with items
    Given I have a card titled "new card" in the "Working" column
    When I would like to add a check list to the card
    Then I am able to add a checklist called "check list"
    And it has an item called "Item 1"
    And it has an item called "Item 2" */
    @Test
    public void TestAddCheckListWithItems() throws Exception
    {
        String cardTitle = "new card";
        String listName = "Working";
        String checklistTitle = "check list";
        ArrayList<String> checkListItems = new ArrayList<>();
        checkListItems.add("Item 1");
        checkListItems.add("Item 2");
        Step("Go to Trello Page");
        pages.GoToURL("https://trello.com/b/0QEdvItb/rt-sdet");

        // TODO: Verify all these steps
        Step("Log into Trello");
        pages.trelloSignInPage.LogIntoAccountOnMainPage(email, password);

        Step(String.format("Add '%s' card to list '%s'", cardTitle, listName));
        pages.Sleep(1);
        pages.trelloSignInPage.trelloBoardPage.AddCardToColumn(listName, cardTitle);

        Step("Open the newly created card");
        pages.Sleep(1);
        pages.trelloSignInPage.trelloBoardPage.OpenCard(cardTitle, listName);

        Step(String.format("Create a checklist named: '%s' and its items", checklistTitle));
        pages.trelloSignInPage.trelloBoardPage.trelloCardPage.CreateNewCheckList(checklistTitle, checkListItems);

        Step("Verify checklist is correct");
        pages.trelloSignInPage.trelloBoardPage.trelloCardPage.VerifyCheckList(checkListItems);

        Step("Delete card in modal");
        pages.trelloSignInPage.trelloBoardPage.trelloCardPage.DeleteCard();

        // TODO: complete up to here


        Step("Verify card is no longer in list");
        // TODO: confirm after delete that card is not in list in Board
        //pages.trelloPage.VerifyCardNotInColumn(cardTitle, listName);

        Step("Verify card was also not archived");
        // TODO: menu appearing after deletion
        // //*[@class='SyQNhGiXQPXGMA Ts+YceGnvTbKoG gGx-TWcD-qHGLA d3VddHWjHgldJq JIXQq8gDYY04N6']
        Info("Expand Menu");
        SeleniumControl threeBtnList = new SeleniumControl(By.xpath("//*[@class='show-sidebar-button-react-root']"));
            threeBtnList.Click(5);


        Info("Click on 'More' in Menu");
        SeleniumControl moreInMenu = new SeleniumControl(By.xpath("//*[@class='board-menu-navigation-item-link js-open-more']"));
        moreInMenu.Click(5);

        //*[@class='board-moreInMenu-navigation-item-link js-open-archive']
        // TODO: not clicking on archive
        Info("Click on 'Archived Items'");
        SeleniumControl archiveItems = new SeleniumControl(By.xpath("//*[@class='board-menu-navigation-item-link js-open-archive']"));
        archiveItems.Click(5);

        // shown in empty list but should not use it
        // //*[@class='empty-list js-empty-message']

        // should not be found if deleted right
        // //*[@class='empty-list js-empty-message'] - if this is true, card was correctly archived and deleted
        //*[@class='archived-list-card']//*[text()='new card'] - if you find this, card was only archived
        SeleniumControl noCardsCheck = new SeleniumControl(By.xpath("//*[@class='js-archive-items']"));
        noCardsCheck.FindElement(5);
        /*WebElement word = noCardsCheck.getWebElement();
        String stringword = noCardsCheck.getText();
        By word2 = noCardsCheck.getLocator();
        String word3 = noCardsCheck.getLocatorValue();*/

    }

    /** Scenario 4: Complete to do Items
    Given I have a card titled "new card" in the "Working" column
    And a check list in that card called "Check list"
    And has an item called "Item 1"
    And has an item called "Item 2"
    Then I am able click on the check box of each item
    They are marked as complete. */
    @Test
    public void TestCompleteToDoItemsChecklist() throws Exception
    {
        String cardTitle = "new card";
        String listName = "Working";
        String checklistTitle = "check list";
        ArrayList<String> checkListItems = new ArrayList<>();
        checkListItems.add("Item 1");
        checkListItems.add("Item 2");

        Step("Go to Trello Page");
        pages.GoToURL("https://trello.com/b/0QEdvItb/rt-sdet");
        // TODO: Verify all these steps
        Step("Log into Trello");
        pages.trelloSignInPage.LogIntoAccountOnMainPage(email, password);

        Step(String.format("Add '%s' card to list '%s'", cardTitle, listName));
        pages.Sleep(1);
        pages.trelloSignInPage.trelloBoardPage.AddCardToColumn(listName, cardTitle);

        Step("Open the newly created card");
        pages.Sleep(1);
        pages.trelloSignInPage.trelloBoardPage.OpenCard(cardTitle, listName);

        Step(String.format("Create a checklist named: '%s' and its items", checklistTitle));
        pages.trelloSignInPage.trelloBoardPage.trelloCardPage.CreateNewCheckList(checklistTitle, checkListItems);

        Step("Complete the checklist by clicking on the checkboxes");
        pages.trelloSignInPage.trelloBoardPage.trelloCardPage.CompleteCheckList(checkListItems);

        Step("Verify checklist is complete");
        pages.trelloSignInPage.trelloBoardPage.trelloCardPage.VerifyChecklistComplete();
    }

    /** Scenario 5: Move to done
    Given I have a card titled "new card" in the "Working" column
    When I open the card
    DELETED LINE CAUSE ERROR; click on top to get Move modal
    Then I am able to move the card to the Done List
    And see that it is in the done list. */
    @Test
    public void TestMoveCardIntoDoneColumn() throws Exception
    {
        String cardTitle = "new card";
        String listNameWorking = "Working";
        String listNameDone = "Done";
        Step("Go to Trello Page");
        pages.GoToURL("https://trello.com/b/0QEdvItb/rt-sdet");

        // TODO: Verify all these steps
        Step("Log into Trello");
        pages.trelloSignInPage.LogIntoAccountOnMainPage(email, password);

        Step(String.format("Add '%s' card to list '%s'", cardTitle, listNameWorking));
        pages.Sleep(1);
        pages.trelloSignInPage.trelloBoardPage.AddCardToColumn(listNameWorking, cardTitle);

        Step("Open the newly created card");
        pages.Sleep(1);
        pages.trelloSignInPage.trelloBoardPage.OpenCard(cardTitle, listNameWorking);

        Step("Click on 'in list Working' at the top of the modal and move to list 'Done'");
        pages.trelloSignInPage.trelloBoardPage.trelloCardPage.MoveCardToColumnUsingInList(listNameDone);

        Step("Verify card is in 'Done' list");
        pages.trelloSignInPage.trelloBoardPage.VerifyCardInColumn(cardTitle, listNameDone);

    }

}
