package pages;

import autoFramework.AutoBase;
import org.openqa.selenium.By;
import webTestFramework.SeleniumControl;

public class PodiumBubble extends AutoBase {

    SeleniumControl podiumBubble = new SeleniumControl(By.id("podium-bubble"));

    SeleniumControl podiumBtn = new SeleniumControl(By.className("ContactBubble__Bubble"));

    SeleniumControl modal = new SeleniumControl(By.xpath("//*[contains(@class, 'SearchInput')]"));

    /** Switch to podium-bubble iframe.*/
    public void GoToPodiumBubbleFrame()
    {
        switchToMainFrame();
        switchToiFrame("podium-bubble");
    }

    /** Verify in main iframe by checking if podium-bubble exists. Podium button should not visible.*/
    public void VerifyPodiumBubbleExists()
    {
        podiumBubble.IsVisible(5);
    }

    /** Must be within podium-bubble*/
    public void ClickOnPodiumButton() throws Exception
    {
        podiumBtn.Click(5);
    }

    /** Verify within podium-bubble iframe and Podium button exists.*/
    public void VerifyPodiumBtnExists() throws Exception
    {
        podiumBtn.IsVisible(5);
    }

    /** Switch to podium-modal iframe.*/
    public void GoToPodiumModalFrame()
    {
        switchToMainFrame();
        switchToiFrame("podium-modal");
    }

    /** Looks for searchbar to verify within iframe podium-modal.  */
    public void VerifyOnModal()
    {
        modal.IsVisible(5);
    }

    /** Jumps to podium-modal iframe regardless of where currently at.*/
    public void JumpToPodiumModal() throws Exception
    {
        switchToMainFrame();
        switchToiFrame("podium-bubble");
        podiumBtn.Click(5);
        switchToMainFrame();
        switchToiFrame("podium-modal");
    }
}
