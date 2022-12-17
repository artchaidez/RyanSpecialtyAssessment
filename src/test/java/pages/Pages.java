package pages;

import autoFramework.UIBase;

public class Pages extends UIBase {

    public PodiumBubble podiumBubble;
    public PodiumModal podiumModal;

    public TrelloSignInPage trelloSignInPage;


    public Pages()
    {
        podiumBubble = new PodiumBubble();
        podiumModal = new PodiumModal();
        trelloSignInPage = new TrelloSignInPage();
    }
}
