package SwingGames.BlackJack;

import javax.swing.*;

public class BlackJackFrame extends JFrame {

    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;

    public BlackJackFrame() {

        setSize(WIDTH, HEIGHT);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }




    public static void main(String[] args) {
        new BlackJackFrame();
    }
}
