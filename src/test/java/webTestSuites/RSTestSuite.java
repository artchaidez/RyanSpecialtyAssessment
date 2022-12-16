package webTestSuites;

import autoFramework.AutoTestBase;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import webTestFramework.SeleniumControl;

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
        String columnNameTodo = "To Do";
        String cardDescription = "this is a new card";

        Step("Go to Trello Page");
            pages.GoToURL("https://trello.com/b/0QEdvItb/rt-sdet");

        // TODO: Verify all these steps
        Step("Log into Trello");
            pages.trelloPage.LogIntoAccountOnMainPage(email, password);

        Step("Add a new card titled 'new card' to column 'To Do'");
            pages.trelloPage.AddCardToColumn(columnNameTodo, cardTitle);

        Step("Find the newly created card and open it to edit the card");
            pages.trelloPage.OpenCard(cardTitle);

        // TODO: Fails here. Need correct Xpath
        Step("In the card modal, add the description: this is a new card");
            SeleniumControl cardModalDescription = new SeleniumControl(By.xpath("//*[@class='editable']//*[@class='description-content js-desc-content']//*[@class= 'field field-autosave js-description-draft description card-description']"));
            // //*[@class= 'field field-autosave js-description-draft description card-description'] does not work
            // //*[@class='description-content js-desc-content'] does not work

            //cardModalDescription.SetText(cardDescription, 5, false);

        Step("Save the description");
            SeleniumControl saveDescription = new SeleniumControl(By.xpath("//*[@class= 'nch-button nch-button--primary confirm mod-submit-edit js-save-edit']"));
            //saveDescription.Click(5);

        Step("Close the modal");
            pages.trelloPage.CloseCard();

        // TODO: Verify this was saved: open up card and check description; need method using parent xpath
        Step("Open the card again and confirm the description is correct");


    }


    /** Scenario 2: Move Card to Working
    Given I have a card titled "new card" in the "To Do" column
    When I want to work on it
    Then I am able to move it into the "working" column
    And see that it stays there */
    @Test
    public void TestMoveCardIntoWorkingColumn() throws Exception
    {
        String columnNameTodo = "To Do";
        String cardTitle = "new card";
        String columnNameWorking = "Working";

        Step("Go to Trello Page");
            pages.GoToURL("https://trello.com/b/0QEdvItb/rt-sdet");

        // TODO: Verify all these steps
        Step("Log into Trello");
            pages.trelloPage.LogIntoAccountOnMainPage(email, password);

        Step(String.format("Add '%s' card to column '%s'", cardTitle, columnNameTodo));
            pages.trelloPage.AddCardToColumn(columnNameTodo, cardTitle);

        Step(String.format("Open '%s'", cardTitle));
            pages.trelloPage.OpenCard(cardTitle);

        Step("In the modal, under Actions, click on Move to open the 'Move Card' modal");
            pages.trelloPage.ClickOnMoveCardInModal();

        Step(String.format("Move card into '%s' column", columnNameWorking));
            pages.trelloPage.MoveCardToColumn(columnNameWorking);

        // TODO: Verify card was moved to working; need method using parent xpath

    }

    /** Scenario 3:  Add Checklist with items
    Given I have a card titled "new card" in the "Working" column
    When I would like to add a check list to the card
    Then I am able to add a checklist called "check list"
    And it has an item called "Item 1"
    And it has an item called "Item 2" */

    /** Scenario 4: Complete to do Items
    Given I have a card titled "new card" in the "Working" column
    And a check list in that card called "Check list"
    And has an item called "Item 1"
    And has an item called "Item 2"
    Then I am able click on the check box of each item
    They are marked as complete. */

    /** Scenario 5: Move to done
    Given I have a card titled "new card" in the "Working" column
    When I open the card
    And I click on the current list with the text “in list Working”
    Then I am able to move the card to the Done List
    And see that it is in the done list. */

}
