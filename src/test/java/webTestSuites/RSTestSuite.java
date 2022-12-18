package webTestSuites;

import autoFramework.AutoTestBase;
import org.openqa.selenium.By;
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

    @Test(description = "Scenario 1: Create a card in the To Do list")
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

        Step("Delete card in modal");
            pages.trelloSignInPage.trelloBoardPage.trelloCardPage.DeleteCard();

    }

    @Test(description = "Scenario 2: Create a card in the To Do list and move it into the Working list")
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

        Step("Delete card in modal");
            pages.trelloSignInPage.trelloBoardPage.trelloCardPage.DeleteCard();

    }

    @Test(description = "Scenario 3: Create a card and add a checklist with two items in it")
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
            pages.trelloSignInPage.trelloBoardPage.AddCardToColumn(listName, cardTitle);

        Step("Verify the card was created by opening it");
            pages.trelloSignInPage.trelloBoardPage.OpenCard(cardTitle, listName);

        Step(String.format("Create a checklist named: '%s' and its items", checklistTitle));
            pages.trelloSignInPage.trelloBoardPage.trelloCardPage.CreateNewCheckList(checklistTitle, checkListItems);

        Step("Verify checklist is correct");
            pages.trelloSignInPage.trelloBoardPage.trelloCardPage.VerifyCheckList(checkListItems);

        Step("Delete card in modal");
            pages.trelloSignInPage.trelloBoardPage.trelloCardPage.DeleteCard();
    }

    @Test(description = "Scenario 4: Create a card with a checklist, complete the checklist, " +
            "and verify the checklist was completed")
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
            pages.trelloSignInPage.trelloBoardPage.AddCardToColumn(listName, cardTitle);

        Step("Verify the card was created by opening it");
            pages.trelloSignInPage.trelloBoardPage.OpenCard(cardTitle, listName);

        Step(String.format("Create a checklist named: '%s' and its items", checklistTitle));
            pages.trelloSignInPage.trelloBoardPage.trelloCardPage.CreateNewCheckList(checklistTitle, checkListItems);

        Step("Complete the checklist by clicking on the checkboxes");
            pages.trelloSignInPage.trelloBoardPage.trelloCardPage.CompleteCheckList(checkListItems);

        Step("Verify checklist is complete");
            pages.trelloSignInPage.trelloBoardPage.trelloCardPage.VerifyChecklistComplete();

        Step("Delete card in modal");
            pages.trelloSignInPage.trelloBoardPage.trelloCardPage.DeleteCard();
    }

    @Test(description = "Scenario 5: Create a card in Working list and move it into the Done list")
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
            pages.trelloSignInPage.trelloBoardPage.AddCardToColumn(listNameWorking, cardTitle);

        Step("Verify the card was created by opening it");
            pages.trelloSignInPage.trelloBoardPage.OpenCard(cardTitle, listNameWorking);

        Step(String.format("Click on 'in list Working' at the header and move to list '%s'", listNameDone));
            pages.trelloSignInPage.trelloBoardPage.trelloCardPage.MoveCardToColumnUsingInList(listNameDone);

        Step(String.format("Verify card is in list '%s'", listNameDone));
            pages.trelloSignInPage.trelloBoardPage.VerifyCardInColumn(cardTitle, listNameDone);

        Step("Delete card in modal");
            pages.trelloSignInPage.trelloBoardPage.trelloCardPage.DeleteCard();
    }

    @Test(description = "Testing card can be deleted within card")
    public void TestDeleteCardInCard() throws Exception
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

        Step("Verify card was made by deleting it");
            pages.trelloSignInPage.trelloBoardPage.DeleteCard(cardTitle, listNameTodo);

        Step("Verify card is no longer in list");
            pages.trelloSignInPage.trelloBoardPage.VerifyNoCardsInColumn(listNameTodo);

        Step("Verify card was not archived only and properly deleted");
            pages.trelloSignInPage.trelloBoardPage.VerifyCardWasDeletedNotArchived();
    }

    @Test(description = "Verify card can be deleted on board")
    public void TestDeleteCardOnBoard() throws Exception
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

        Step("Verify card was made by deleting it");
            pages.trelloSignInPage.trelloBoardPage.OpenCard(cardTitle,listNameTodo);
            pages.trelloSignInPage.trelloBoardPage.trelloCardPage.DeleteCard();

        Step("Verify card is no longer in list");
            pages.trelloSignInPage.trelloBoardPage.VerifyNoCardsInColumn(listNameTodo);

        Step("Verify card was not archived only and properly deleted");
            pages.trelloSignInPage.trelloBoardPage.VerifyCardWasDeletedNotArchived();
    }

    @Test(description = "Provide a second way to add a card to a list")
    public void TestDropDownAddCardOption() throws Exception
    {
        String cardTitle = "my card";
        String listNameTodo = "To Do";

        Step("Go to Trello Page");
            pages.GoToURL("https://trello.com/b/0QEdvItb/rt-sdet");

        Step("Log into Trello");
            pages.trelloSignInPage.LogIntoAccountOnMainPage(email, password);

        Step("Verify RT SDET board is open");
            pages.trelloSignInPage.trelloBoardPage.VerifyOnSDETBoard();

        Step("Add card using List actions dropdown");
            pages.trelloSignInPage.trelloBoardPage.DropDownActionsAddCard(cardTitle, listNameTodo);

        Step("Verify card was made");
            pages.trelloSignInPage.trelloBoardPage.VerifyCardInColumn(cardTitle, listNameTodo);

        Step("Verify card was made by deleting");
            pages.trelloSignInPage.trelloBoardPage.DeleteCard(cardTitle, listNameTodo);
    }

}
