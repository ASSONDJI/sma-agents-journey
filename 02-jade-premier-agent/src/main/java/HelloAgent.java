import jade.core.Agent;

public class HelloAgent extends Agent {
    @Override
    protected void setup() {
        System.out.println("Bonjour, je suis l'agent " + getLocalName());
        doDelete();
    }
}
