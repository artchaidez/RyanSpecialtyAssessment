package pages;

import autoFramework.AutoBase;

public class Pages extends AutoBase {

    public PodiumBubble podiumBubble;
    public PodiumModal podiumModal;

    public Pages()
    {
        podiumBubble = new PodiumBubble();
        podiumModal = new PodiumModal();
    }
}
