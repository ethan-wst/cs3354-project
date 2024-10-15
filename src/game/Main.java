package game;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        Player p1 = new Player(true);
        Player p2 = new Player(false);
        game.initialize(p1, p2);
    }
}
