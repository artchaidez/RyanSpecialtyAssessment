package webTestSuites;

import autoFramework.AutoTestBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
    public void TestCreateNewCardInTodo() throws Exception
    {
        String cardTitle = "new card";
        String listNameTodo = "To Do";
        String cardDescription = "this is a new card";

        Step("Go to Trello Page");
            pages.GoToURL("https://trello.com/b/0QEdvItb/rt-sdet");

        Step("Log into Trello");
            pages.trelloSignInPage.LogIntoAccountOnMainPage(email, password);

        Step("Verify RT SDET board is open");
            pages.trelloSignInPage.trelloBoardPage.VerifyOnSDETBoard();

        Step(String.format("Add a new card titled '%s' to list '%s'", cardTitle, listNameTodo));
            pages.trelloSignInPage.trelloBoardPage.AddCardToColumn(listNameTodo, cardTitle);

        Step("Verify card was made by opening it");
            pages.trelloSignInPage.trelloBoardPage.OpenCard(cardTitle, listNameTodo);

        Step(String.format("In the card modal, add the description: %s", cardDescription));
            pages.trelloSignInPage.trelloBoardPage.trelloCardPage.SetDescriptionText(cardDescription);

        Step("Verify the description is correct");
            pages.trelloSignInPage.trelloBoardPage.trelloCardPage.VerifyDescriptionOnCard(cardDescription);

            //TODO: this should be in tearDown
        Step("Delete card in modal");
            pages.trelloSignInPage.trelloBoardPage.trelloCardPage.DeleteCard();

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

        Step("Log into Trello");
            pages.trelloSignInPage.LogIntoAccountOnMainPage(email, password);

        Step("Verify RT SDET board is open");
            pages.trelloSignInPage.trelloBoardPage.VerifyOnSDETBoard();

        Step(String.format("Add '%s' card to list '%s'", cardTitle, listNameTodo));
            pages.trelloSignInPage.trelloBoardPage.AddCardToColumn(listNameTodo, cardTitle);

        Step(String.format("Verify '%s' was created by opening the card", cardTitle));
            pages.trelloSignInPage.trelloBoardPage.OpenCard(cardTitle, listNameTodo);

        Step(String.format("Under Actions, click on Move and move the card to list '%s'", listNameWorking));
            pages.trelloSignInPage.trelloBoardPage.trelloCardPage.MoveCardToColumnUnderAction(listNameWorking);

        Step(String.format("Verify card was moved to list '%s'", listNameWorking));
            pages.trelloSignInPage.trelloBoardPage.VerifyCardInColumn(cardTitle, listNameWorking);

            // TODO: this should be done in tearDown
        Step("Delete card in modal");
            pages.trelloSignInPage.trelloBoardPage.trelloCardPage.DeleteCard();

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

        Step("Log into Trello");
            pages.trelloSignInPage.LogIntoAccountOnMainPage(email, password);

        Step("Verify RT SDET board is open");
            pages.trelloSignInPage.trelloBoardPage.VerifyOnSDETBoard();

        Step(String.format("Add '%s' card to list '%s'", cardTitle, listName));
            pages.Sleep(1);
            pages.trelloSignInPage.trelloBoardPage.AddCardToColumn(listName, cardTitle);

        Step("Verify the card was created by opening it");
            pages.Sleep(1);
            pages.trelloSignInPage.trelloBoardPage.OpenCard(cardTitle, listName);

        Step(String.format("Create a checklist named: '%s' and its items", checklistTitle));
            pages.trelloSignInPage.trelloBoardPage.trelloCardPage.CreateNewCheckList(checklistTitle, checkListItems);

        Step("Verify checklist is correct");
            pages.trelloSignInPage.trelloBoardPage.trelloCardPage.VerifyCheckList(checkListItems);

        Step("Delete card in modal");
            pages.trelloSignInPage.trelloBoardPage.trelloCardPage.DeleteCard();
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

        Step("Log into Trello");
            pages.trelloSignInPage.LogIntoAccountOnMainPage(email, password);

        Step("Verify RT SDET board is open");
            pages.trelloSignInPage.trelloBoardPage.VerifyOnSDETBoard();

        Step(String.format("Add '%s' card to list '%s'", cardTitle, listName));
            pages.Sleep(1);
            pages.trelloSignInPage.trelloBoardPage.AddCardToColumn(listName, cardTitle);

        Step("Verify the card was created by opening it");
            pages.Sleep(1);
            pages.trelloSignInPage.trelloBoardPage.OpenCard(cardTitle, listName);

        Step(String.format("Create a checklist named: '%s' and its items", checklistTitle));
            pages.trelloSignInPage.trelloBoardPage.trelloCardPage.CreateNewCheckList(checklistTitle, checkListItems);

        Step("Complete the checklist by clicking on the checkboxes");
            pages.trelloSignInPage.trelloBoardPage.trelloCardPage.CompleteCheckList(checkListItems);

        Step("Verify checklist is complete");
            pages.trelloSignInPage.trelloBoardPage.trelloCardPage.VerifyChecklistComplete();

        // TODO: this should be done in tearDown
        Step("Delete card in modal");
            pages.trelloSignInPage.trelloBoardPage.trelloCardPage.DeleteCard();
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

        Step("Log into Trello");
            pages.trelloSignInPage.LogIntoAccountOnMainPage(email, password);

        Step("Verify RT SDET board is open");
            pages.trelloSignInPage.trelloBoardPage.VerifyOnSDETBoard();

        Step(String.format("Add '%s' card to list '%s'", cardTitle, listNameWorking));
            pages.Sleep(1);
            pages.trelloSignInPage.trelloBoardPage.AddCardToColumn(listNameWorking, cardTitle);

        Step("Verify the card was created by opening it");
            pages.Sleep(1);
            pages.trelloSignInPage.trelloBoardPage.OpenCard(cardTitle, listNameWorking);

        Step(String.format("Click on 'in list Working' at the header and move to list '%s'", listNameDone));
            pages.trelloSignInPage.trelloBoardPage.trelloCardPage.MoveCardToColumnUsingInList(listNameDone);

        Step(String.format("Verify card is in list '%s'", listNameDone));
            pages.trelloSignInPage.trelloBoardPage.VerifyCardInColumn(cardTitle, listNameDone);

        // TODO: this should be done in tearDown
        Step("Delete card in modal");
            pages.trelloSignInPage.trelloBoardPage.trelloCardPage.DeleteCard();
    }
/*

    @Test
    public void TestDeleteCardWorks() throws Exception
    {
        String cardTitle = "my card";
        String listNameTodo = "To Do";

        Step("Go to Trello Page");
            pages.GoToURL("https://trello.com/b/0QEdvItb/rt-sdet");

        Step("Log into Trello");
            pages.trelloSignInPage.LogIntoAccountOnMainPage(email, password);

        Step("Verify RT SDET board is open");
            pages.trelloSignInPage.trelloBoardPage.VerifyOnSDETBoard();

        Step(String.format("Add a new card titled '%s' to list '%s'", cardTitle, listNameTodo));
            pages.trelloSignInPage.trelloBoardPage.AddCardToColumn(listNameTodo, cardTitle);

        Step("Verify card was made by opening it");
            pages.trelloSignInPage.trelloBoardPage.OpenCard(cardTitle, listNameTodo);

        Step("Delete the card");
            pages.trelloSignInPage.trelloBoardPage.trelloCardPage.DeleteCard();

        Step("Verify card is no longer in list");
        // TODO: ***Extra Credit***  confirm after delete that card is not in list in Board
        pages.trelloSignInPage.trelloBoardPage.VerifyNoCardsInColumn(listNameTodo);

        // this works
        pages.trelloSignInPage.trelloBoardPage.VerifyCardWasDeletedNotArchived();
    }
*/

}
