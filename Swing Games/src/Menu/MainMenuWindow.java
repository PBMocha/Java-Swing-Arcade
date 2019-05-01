package Menu;

import TicTacToe.TicTacToeFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuWindow extends JFrame {

    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;

    public MainMenuWindow() {

        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(new BorderLayout());

        centerComponents();
        northComponents();
    }

    public void centerComponents() {

        JButton openGame = new JButton("Tic Tac Toe");
        openGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TicTacToeFrame(JFrame.DISPOSE_ON_CLOSE);

            }
        });

        add(openGame, BorderLayout.CENTER);

    }

    public void northComponents() {
        JLabel menuTitle = new JLabel("Welcome to the Basic Arcade");
        menuTitle.setFont(new Font("Serif", Font.BOLD,18));
        menuTitle.setHorizontalAlignment(JLabel.CENTER);

        add(menuTitle, BorderLayout.NORTH);
    }

    public static void main(String[] args) {
        new MainMenuWindow();
    }
}
