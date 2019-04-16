package TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame  {

    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;

    JPanel gameBoard;

    private JButton[] placementLoc = new JButton[9];
    public static String[][] gameGrid = new String[3][3];
    public static int filledLoc = 0;
    private JButton reset;
    private JButton startButton;

    //Labels
    private JLabel turnLabel;
    private JLabel menuLabel;

    private boolean isCirclesTurn = true;
    private boolean winnerExists = false;
    private String symbolWinner;

    public GameFrame() {

        setSize(WIDTH, HEIGHT);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(new BorderLayout());
        centerComponents();
        eastComponents();
        westComponents();
    }

    //Where the game board resides
    public void centerComponents() {

        int rowSize = gameGrid.length;
        int colSize = gameGrid[0].length;

        for (int row = 0; row < rowSize; row++)
            for (int col = 0; col < colSize; col++)
                if (gameGrid[row][col] == null)
                    gameGrid[row][col] = "";


        gameBoard = new JPanel(new GridLayout(3,3, 10, 10));
        gameBoard.setBackground(Color.BLACK);

        //Need to fix
        for (int i = 0; i < placementLoc.length; i++) {
            placementLoc[i] = new JButton();
            placementLoc[i].setBackground(Color.WHITE);
            placementLoc[i].setFont(new Font("Courier New", Font.BOLD, 50));
            gameBoard.add(placementLoc[i]);
        }

        for (int bIndex = 0, row = 0, col = 0; bIndex < placementLoc.length; bIndex++, col++) {

            if (bIndex % 3 == 0)
                row++;
            if (col % 3 == 0)
                col = 0;

            placementLoc[bIndex].addActionListener(new PlacementListener(row - 1, col));
        }
        for (Component component : gameBoard.getComponents()) {
            component.setEnabled(false);
        }
        gameBoard.setSize(400, 400);
        //Add the panel
        add(gameBoard, BorderLayout.CENTER);
    }

    //Game information; Which player's turn
    public void eastComponents() {

        JPanel eastPanel = new JPanel();

        turnLabel = new JLabel("Welcome To TicTacToe!");
        eastPanel.add(turnLabel);
        add(eastPanel, BorderLayout.EAST);

    }

    public void westComponents() {

        JPanel menuPanel = new JPanel();
        startButton = new JButton("Begin");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton thisButton = (JButton)e.getSource();
                thisButton.setEnabled(false);
                for (Component component : gameBoard.getComponents())
                    component.setEnabled(true);
                turnLabel.setText("Circle Player's Turn");
            }
        });

        JButton exitButton = new JButton("EXIT");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        menuPanel.add(startButton);
        menuPanel.add(exitButton);
        add(menuPanel, BorderLayout.WEST);
    }
    //Event Listeners

    //When a button is clocked, set button to symbol corresponding to player's turn.
    //Check if each player placement wins the game.
    private class PlacementListener implements ActionListener {

        private int row;
        private int col;

        public PlacementListener(int row, int col) {

            this.row = row;
            this.col = col;

        }

        @Override
        public void actionPerformed(ActionEvent e) {

            JButton buttonSource = (JButton) e.getSource();
            if (isCirclesTurn) {

                placeAtLocation(e, "O");
                buttonSource.setBackground(Color.BLUE);
                turnLabel.setText("Cross Player's Turn");
                isCirclesTurn = false;
            } else {

                placeAtLocation(e, "X");
                buttonSource.setBackground(Color.RED);
                turnLabel.setText("Circle Player's Turn");
                isCirclesTurn = true;

            }

            filledLoc++;

            if (winnerExists) {

                turnLabel.setText(symbolWinner + " is the Winner!!");

            } else if (filledLoc == placementLoc.length) {
                turnLabel.setText("No more possible locations.Game is a tie.");

            }

        }

        public void placeAtLocation(ActionEvent event, String symbol) {
            System.out.println("Placed an " + symbol + " at location (" + row + ", " + col + ")");
            if (event.getSource() instanceof JButton) {
                ((JButton) event.getSource()).setText(symbol);
                ((JButton) event.getSource()).setEnabled(false);
                gameGrid[row][col] = symbol;
            }

            if (isWinner(symbol)) {
                System.out.println(symbol + " is the winner!!");

                for (JButton button : placementLoc)
                    button.setEnabled(false);

                winnerExists = true;
                symbolWinner = symbol;
            }
        }


    }

    private class ResetListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

        }
    }

    //static only temporary for testing. When completed, remove the static
    private static boolean isWinner(String symbol) {

        int rowsize = gameGrid.length;
        int colsize = gameGrid[0].length;

        for (int row = 0; row < rowsize; row++) {
            for (int col = 0; col < colsize; col++)
                if (gameGrid[row][col] == null)
                    gameGrid[row][col] = "";
        }

        //Check all rows for win condition
        for (int row = 0; row < rowsize; row++) {
            int trueCnt = 0;
            for (int col = 0; col < colsize; col++) {
                if (!gameGrid[row][col].equals(symbol))
                    break;
                if (col == colsize - 1)
                    return true;
            }
        }

        //Check all columns
        for (int col = 0; col < colsize; col++) {
            int trueCnt = 0;
            for (int row = 0; row < rowsize; row++) {
                if (!gameGrid[row][col].equals(symbol))
                    break;
                if (row == rowsize - 1)
                    return true;
            }
        }

        //Check all diagonals
        for (int dia = 0; dia < gameGrid.length; dia++) {
            if (!gameGrid[dia][dia].equals(symbol))
                break;
            if (dia == gameGrid.length-1)
                return true;
        }

        for (int row = 2, col = 0; row > -1; row--, col++) {
            if(!gameGrid[row][col].equals(symbol))
                break;
            if (row == 0)
                return true;
        }


        return false;
    }

    public static void main(String[] args) {

        new GameFrame();
    }
}
