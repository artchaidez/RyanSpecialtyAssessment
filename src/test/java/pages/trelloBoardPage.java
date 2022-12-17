package pages;

import autoFramework.UIBase;
import org.openqa.selenium.By;
import org.testng.Assert;
import webTestFramework.SeleniumControl;

import java.util.ArrayList;

public class trelloBoardPage extends UIBase {

    // TODO: Create and organize three pages: Sign in Page, main board, card modal

    // this class needs card model object


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

    // TODO: Use same xpath to delete a card. Do this at the end of every test making a new card
    public void OpenCard(String cardTitle, String columnName) throws Exception
    {
        // TODO: This is used in VerifyCardInColumn; refactor
        String xpath = String.format("//*[text()='%s']/parent::div/following-sibling::div[1]//*[text()='%s']", columnName, cardTitle);
        SeleniumControl newCard = new SeleniumControl(By.xpath(xpath));
        newCard.Click(5);
    }

    public void DeleteCard(String cardTitle, String columnName) throws Exception
    {
        String xpath = String.format("//*[text()='%s']/parent::div/following-sibling::div[1]//*[text()='%s']", columnName, cardTitle);
        SeleniumControl newCard = new SeleniumControl(By.xpath(xpath));
        newCard.Click(5);

        DeleteCardInModal();
    }

    public void DeleteCardInModal() throws Exception
    {
        SeleniumControl archiveBtn = new SeleniumControl(By.xpath("//*[@class='button-link js-archive-card']"));
        archiveBtn.Click(5);

        //*[@class='button-link js-delete-card negate']
        // after card archived, option appears to delete it
        SeleniumControl deleteBtn = new SeleniumControl(By.xpath("//*[@class='button-link js-delete-card negate']"));
        deleteBtn.Click(5);

        //*[@class='js-confirm full nch-button nch-button--danger']
        // confirm delete
        SeleniumControl confirmDelete = new SeleniumControl(By.xpath("//*[@class='js-confirm full nch-button nch-button--danger']"));
        confirmDelete.Click(5);
    }

    private boolean ReturnCardInColumn(String cardTitle, String columnName)
    {
        String xpath = String.format("//*[text()='%s']/parent::div/following-sibling::div[1]//*[text()='%s']", columnName, cardTitle);
        SeleniumControl card = new SeleniumControl(By.xpath(xpath));
        return card.IsVisible(5);
    }

    public void VerifyCardInColumn(String cardTitle, String columnName) throws Exception
    {
        Assert.assertTrue(ReturnCardInColumn(cardTitle, columnName));
    }

    public void VerifyCardNotInColumn(String cardTitle, String columnName)
    {
        String xpath = String.format("//*[text()='%s']/parent::div/following-sibling::div[1]//*[text()='%s']", columnName, cardTitle);
        SeleniumControl card = new SeleniumControl(By.xpath(xpath));
        Assert.assertTrue(card.IsNotVisible(5));
    }

    public void CloseCard() throws Exception
    {
        SeleniumControl closeCardModal = new SeleniumControl(By.xpath("//*[@class= 'icon-md icon-close dialog-close-button js-close-window']"));
        closeCardModal.Click(5);
    }

    private void MoveCardToColumn(String columnName) throws Exception
    {
        String xpath = String.format("//*[@class='button-link setting form-grid-child form-grid-child-threequarters']//option[contains(.,'%s')]", columnName);
        SeleniumControl workingOnMoveCardList = new SeleniumControl(By.xpath(xpath));
        workingOnMoveCardList.Click(5);

        SeleniumControl clickMove = new SeleniumControl(By.xpath("//*[@class='nch-button nch-button--primary wide js-submit']"));
        clickMove.Click(5);
    }

    /** Same functionality as MoveCardToColumnUsingInList*/
    public void MoveCardToColumnUnderAction(String columnName) throws Exception
    {
        SeleniumControl moveBtn = new SeleniumControl(By.xpath("//*[@class='button-link js-move-card']"));
        moveBtn.Click(5);
        MoveCardToColumn(columnName);
    }

    /** Same functionality as MoveCardToColumnUnderAction*/
    public void MoveCardToColumnUsingInList(String columnName) throws Exception
    {
        SeleniumControl inList = new SeleniumControl(By.xpath("//*[@class= 'js-open-move-from-header']"));
        inList.Click(5);
        MoveCardToColumn(columnName);
    }


    public void CreateNewCheckList(String checklistTitle, ArrayList<String> checkListItems) throws Exception
    {
        SeleniumControl checkListBtn = new SeleniumControl(By.xpath("//*[@class='button-link js-add-checklist-menu']"));
        checkListBtn.Click(5);

        SeleniumControl checkListInput = new SeleniumControl(By.xpath("//*[@id='id-checklist']"));
        checkListInput.SetText(checklistTitle, 5, false);

        SeleniumControl checkListAdd = new SeleniumControl(By.xpath("//*[@class='nch-button nch-button--primary wide confirm js-add-checklist']"));
        checkListAdd.Click(5);

        SeleniumControl addItemText = new SeleniumControl(By.xpath("//*[@class='edit field checklist-new-item-text js-new-checklist-item-input']"));

        SeleniumControl addItem = new SeleniumControl(By.xpath("//*[@class='nch-button nch-button--primary confirm mod-submit-edit js-add-checklist-item']"));

        for (int i = 0; i < checkListItems.size(); i++)
        {
            addItemText.SetText(checkListItems.get(i), 5, false);
            addItem.Click(5);
        }

    }

    public void VerifyCheckList(ArrayList<String> checkListItems) {
        for (int i = 0; i < checkListItems.size(); i++) {
            SeleniumControl checkList = new SeleniumControl(By.xpath(String.format("//*[@class='checklist-items-list js-checklist-items-list js-no-higher-edits ui-sortable']//*[text()='%s']", checkListItems.get(i))));
            Assert.assertTrue(checkList.IsVisible(5));
        }
    }

    public void CompleteCheckList(ArrayList<String> checkListItems) throws Exception
    {
        for (int i = 0; i < checkListItems.size(); i++) {
            SeleniumControl checkList = new SeleniumControl(By.xpath(String.format("//*[text()='%s']/ancestor::div[@class= 'checklist-item no-assignee no-due']//*[@data-testid='checklist-item-checkbox']", checkListItems.get(i))));
            checkList.Click(5);
            Sleep(1);
        }
    }

    public void VerifyChecklistComplete()
    {
        SeleniumControl progressBar = new SeleniumControl(By.xpath("//*[@class='checklist-progress-percentage js-checklist-progress-percent']"));
        Assert.assertEquals(progressBar.getText(), "100%");
    }




}
