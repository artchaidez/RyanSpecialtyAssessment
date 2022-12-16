package pages;

import autoFramework.UIBase;
import org.openqa.selenium.By;
import webTestFramework.SeleniumControl;

import java.util.ArrayList;

public class TrelloPage extends UIBase {

    private final SeleniumControl logInMainPageBtn = new SeleniumControl(By.xpath("//*[@class='seg6b92uUYbLu1']"));

    private final SeleniumControl emailInput = new SeleniumControl(By.xpath("//*[@id='user']"));

    private final SeleniumControl continueBtn = new SeleniumControl(By.xpath("//*[@id='login']"));

    private final SeleniumControl passwordInput = new SeleniumControl(By.xpath("//*[@id = 'password']"));

    private final SeleniumControl logInBtn = new SeleniumControl(By.xpath("//*[@id = 'login-submit']"));


    public void ClickOnMainPageLogIn() throws Exception
    {
        logInMainPageBtn.Click(5);
    }

    public void SetEmailText(String email) throws Exception
    {
        emailInput.SetText(email, 5, false);
    }

    public void ClickOnMainPageContinue() throws Exception
    {
        continueBtn.Click(5);
    }

    public void SetPasswordText(String password) throws Exception
    {
        passwordInput.SetText(password, 5, false);
    }

    public void ClickOnLogInButton() throws Exception
    {
        logInBtn.Click(5);
    }

    // TODO: Should these methods being called be private? If public, test them?
    public void LogIntoAccountOnMainPage(String email, String password) throws Exception
    {
        ClickOnMainPageLogIn();
        SetEmailText(email);
        ClickOnMainPageContinue();
        SetPasswordText(password);
        ClickOnLogInButton();
    }

    public void AddCardToColumn(String columnName, String cardTitle) throws Exception
    {
        String xpath = String.format("//*[text()='%s']/parent::div/following-sibling::div[2]//*[text()='Add a card']", columnName);
        SeleniumControl column = new SeleniumControl(By.xpath(xpath));
        column.Click(5);

        SeleniumControl inputCardTitle = new SeleniumControl(By.xpath("//*[@class= 'list-card-composer-textarea js-card-title']"));
        inputCardTitle.SetText(cardTitle, 5, false);

        SeleniumControl addCardBtn = new SeleniumControl(By.xpath("//*[contains(@value, 'Add card')]"));
        addCardBtn.Click(5);
    }

    // TODO: This should be looking by column
    // TODO: Use same xpath to delete a card. Do this at the end of every test making a new card
    public void OpenCard(String cardTitle) throws Exception
    {
        String xpath = String.format("//*[@class= 'list-card js-member-droppable ui-droppable ui-sortable-handle']//*[text()='%s']", cardTitle);
        SeleniumControl newCard = new SeleniumControl(By.xpath(xpath));
        newCard.Click(5);
    }

    public void CloseCard() throws Exception
    {
        SeleniumControl closeCardModal = new SeleniumControl(By.xpath("//*[@class= 'icon-md icon-close dialog-close-button js-close-window']"));
        closeCardModal.Click(5);
    }

    public void ClickOnMoveCardInModal() throws Exception
    {
        SeleniumControl moveBtn = new SeleniumControl(By.xpath("//*[@class='button-link js-move-card']"));
        moveBtn.Click(5);
    }

    public void MoveCardToColumn(String columnName) throws Exception
    {
        String xpath = String.format("//*[@class='button-link setting form-grid-child form-grid-child-threequarters']//option[contains(.,'%s')]", columnName);
        SeleniumControl workingOnMoveCardList = new SeleniumControl(By.xpath(xpath));
        workingOnMoveCardList.Click(5);

        SeleniumControl clickMove = new SeleniumControl(By.xpath("//*[@class='nch-button nch-button--primary wide js-submit']"));
        clickMove.Click(5);
    }

    public void CreateNewCheckList(String checklistTitle, ArrayList<String> checkListItems) throws Exception
    {
        SeleniumControl checkListBtn = new SeleniumControl(By.xpath("//*[@class='button-link js-add-checklist-menu']"));
        checkListBtn.Click(5);

        SeleniumControl checkListInput = new SeleniumControl(By.xpath("//*[@id='id-checklist']"));
        checkListInput.SetText(checklistTitle, 5, false);

        // new stuff
        SeleniumControl checkListAdd = new SeleniumControl(By.xpath("//*[@class='nch-button nch-button--primary wide confirm js-add-checklist']"));
        checkListAdd.Click(5);

        SeleniumControl addItemText = new SeleniumControl(By.xpath("//*[@class='edit field checklist-new-item-text js-new-checklist-item-input']"));
        //addItemText.SetText(itemOne, 5, false);

        SeleniumControl addItem = new SeleniumControl(By.xpath("//*[@class='nch-button nch-button--primary confirm mod-submit-edit js-add-checklist-item']"));
        /*addItem.Click(5);
        addItemText.SetText(itemTwo, 5, false);
        addItem.Click(5);*/

        for (int i = 0; i < checkListItems.size(); i++)
        {
            addItemText.SetText(checkListItems.get(i), 5, false);
            addItem.Click(5);
        }

    }


}
