package webTestSuites;

import autoFramework.AutoTestBase;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import webTestFramework.SeleniumControl;

public class RSTestSuite extends AutoTestBase {

    @BeforeMethod
    public void TestSetUp()
    {
        pages.InitWebDriver();
    }

    @AfterMethod
    public void TestTearDown()
    {
        pages.Quit();
        ResetSteps();
    }

    /** Scenario 1: Create New Card in To Do
     Given I have a Trello Board with 3 Lists (To Do, Working, and Done)
     When I need to add a new card to the To Do list with the title of "new card"
     And a description of "this is a new card"
     Then I am able to see it in the To Do column */
    @Test
    public void TestCreateNewCardInTodo() throws Exception {

        String cardTitle = "new card";
        String cardDescription = "this is a new card";

        Step("Go to Trello Page");
            pages.GoToURL("https://trello.com/b/0QEdvItb/rt-sdet");

        Step("Open up a new card in the To Do List, exposing the textarea");
            // TODO: need a way to click 'Add a card', either directly or the three-dot dropdown menu
            SeleniumControl addTodoCard = new SeleniumControl(By.xpath("//*[contains(@class, 'list js-list-content')]"));

        Step("Add the title to the new card: new card");
            // xpath for textarea to input title of the card
            SeleniumControl inputCardTitle = new SeleniumControl(By.xpath("//*[contains(@class, 'list-card-composer-textarea js-card-title')]"));
            inputCardTitle.SetText(cardTitle, 5, false);

        Step("Add the card to the To Do List");
            // TODO: make sure using @value is ok
            SeleniumControl addCardBtn = new SeleniumControl(By.xpath("//*[contains(@value, 'Add card')]"));
            addTodoCard.Click(5);

        Step("Find the newly created card and open it to edit the card");
            // xpath for new card that was just created
            // TODO: Need a way dynamically create a new card with String text, then use it to find the new card //*[contains(text(), 'new card')]
            // IDEA: Method that takes in string and uses it in xpath. Throws exception if it cannot find it
            SeleniumControl newCard = new SeleniumControl(By.xpath("//*[contains(@class, 'list-card js-member-droppable ui-droppable ui-sortable-handle')]//*[contains(text(), 'new card')]"));
            newCard.Click(5);

        Step("In the card modal, add the description: this is a new card");
            SeleniumControl cardModalDescription = new SeleniumControl(By.xpath("//*[contains(@class, 'description-edit edit')]"));
            cardModalDescription.SetText(cardDescription, 5, false);

        Step("Save the description");
            SeleniumControl saveDescription = new SeleniumControl(By.xpath("//*[contains(@class, 'nch-button nch-button--primary confirm mod-submit-edit js-save-edit')]"));
            saveDescription.Click(5);

        Step("Close the modal");
            SeleniumControl closeCardModal = new SeleniumControl(By.xpath("//*[contains(@class, 'icon-md icon-close dialog-close-button js-close-window')]"));
            closeCardModal.Click(5);

        Step("Open the card again and confirm the description is correct");
            newCard.Click(5);
            Assert.assertEquals(cardModalDescription.getText(), cardDescription);

    }


    /** Scenario 2: Move Card to Working
    Given I have a card titled "new card" in the "To Do" column
    When I want to work on it
    Then I am able to move it into the "working" column
    And see that it stays there */

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
