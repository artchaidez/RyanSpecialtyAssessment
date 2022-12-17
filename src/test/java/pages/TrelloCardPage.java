package pages;

import autoFramework.UIBase;
import org.openqa.selenium.By;
import org.testng.Assert;
import webTestFramework.SeleniumControl;

import java.util.ArrayList;

public class TrelloCardPage extends UIBase {


    public void DeleteCard() throws Exception
    {
        SeleniumControl archiveBtn = new SeleniumControl(By.xpath("//*[@class='button-link js-archive-card']"));
        archiveBtn.Click(5);

        SeleniumControl deleteBtn = new SeleniumControl(By.xpath("//*[@class='button-link js-delete-card negate']"));
        deleteBtn.Click(5);

        SeleniumControl confirmDelete = new SeleniumControl(By.xpath("//*[@class='js-confirm full nch-button nch-button--danger']"));
        confirmDelete.Click(5);
    }

    public void CloseCard() throws Exception
    {
        SeleniumControl closeCardModal = new SeleniumControl(By.xpath("//*[@class= 'icon-md icon-close dialog-close-button js-close-window']"));
        closeCardModal.Click(5);
    }

    private void MoveCardToColumn(String listName) throws Exception
    {
        String xpath = String.format("//*[@class='button-link setting form-grid-child form-grid-child-threequarters']//option[contains(.,'%s')]", listName);
        SeleniumControl workingOnMoveCardList = new SeleniumControl(By.xpath(xpath));
        workingOnMoveCardList.Click(5);

        SeleniumControl clickMove = new SeleniumControl(By.xpath("//*[@class='nch-button nch-button--primary wide js-submit']"));
        clickMove.Click(5);
    }

    /** Same functionality as MoveCardToColumnUsingInList*/
    public void MoveCardToColumnUnderAction(String listName) throws Exception
    {
        SeleniumControl moveBtn = new SeleniumControl(By.xpath("//*[@class='button-link js-move-card']"));
        moveBtn.Click(5);
        MoveCardToColumn(listName);
    }

    /** Same functionality as MoveCardToColumnUnderAction*/
    public void MoveCardToColumnUsingInList(String listName) throws Exception
    {
        SeleniumControl inList = new SeleniumControl(By.xpath("//*[@class= 'js-open-move-from-header']"));
        inList.Click(5);
        MoveCardToColumn(listName);
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
            String xpath = String.format("//*[@class='checklist-items-list js-checklist-items-list js-no-higher-edits ui-sortable']//*[text()='%s']", checkListItems.get(i));
            SeleniumControl checkList = new SeleniumControl(By.xpath(xpath));
            Assert.assertTrue(checkList.IsVisible(5));
        }
    }

    public void CompleteCheckList(ArrayList<String> checkListItems) throws Exception
    {
        for (int i = 0; i < checkListItems.size(); i++) {
            String xpath = String.format("//*[text()='%s']/ancestor::div[@class= 'checklist-item no-assignee no-due']//*[@data-testid='checklist-item-checkbox']", checkListItems.get(i));
            SeleniumControl checkList = new SeleniumControl(By.xpath(xpath));
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
