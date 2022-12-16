package pages;

import autoFramework.UIBase;

public class Pages extends UIBase {

    public PodiumBubble podiumBubble;
    public PodiumModal podiumModal;

    public TrelloPage trelloPage;

    public Pages()
    {
        podiumBubble = new PodiumBubble();
        podiumModal = new PodiumModal();
        trelloPage = new TrelloPage();
    }
}
