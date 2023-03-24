import jade.Boot;

public class App {
    public static void main( String[] args )
    {
        Boot.main(new String[]{"-gui", "-agents", "manager:agents.ManagerAgent"});
    }
}
