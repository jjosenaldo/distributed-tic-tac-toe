package gui;

import client.IClientController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUISwing extends JFrame implements GUI {

    private static final long serialVersionUID = -6411296905838649087L;

    private final IClientController client;
    private final JPanel homeScreen;
    private final JPanel loadingScreen;
    private final JPanel gameScreen;
    private JLabel turnUsername;
    private final JButton[][] board;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 600;

    /**
     * This method is called when the user enter its username in GUI.
     *
     * @param username The chosen username.
     */
    private void enterUsername(String username) {
        updateScreen(loadingScreen);
        boolean registrationOk = client.register(username);

        if (!registrationOk) {
            // TODO: Show a warning screen to the player
            updateScreen(homeScreen);
        }
    }

    /**
     * This method is called when the user click on a board cell.
     *
     * @param row Cell's row.
     * @param col Cell's column.
     */
    private void play(int row, int col) {
        if (board[row][col].getText().isEmpty()) {
            // TODO: client.getLabel, set label on cell
            drawPlay(row, col, client.getYourLabel());
            client.play(row, col);
            System.out.println("Click " + row + " " + col);
        }
    }

    public void drawPlay(int row, int col, String label) {
        board[row][col].setText(label);
    }

    /**
     * Set the username in gameScreen.
     *
     * @param username The username to be set.
     */
    @Override
    public void setTurnUsername(String username) {
        // TODO: BUG: the Label is not updated when this method
        // is called for the second time, i.e., when the player's
        // username is supplied
        this.turnUsername = new JLabel(username + "'s turn");
    }

    /**
     * Update the screen to the passed parameter.
     *
     * @param screen The new screen.
     */
    private void updateScreen(JPanel screen) {
        this.getContentPane().removeAll();
        this.getContentPane().add(screen);
        this.revalidate();
        this.repaint();
    }

    /**
     * Create a homeScreen panel.
     *
     * @return The created homeScreen panel.
     */
    private JPanel createHomeScreen() {

        JPanel panel = new JPanel(new GridBagLayout());
        Insets insets = new Insets(5, 5, 5, 5);

        JLabel label = new JLabel("Enter a username");
        GridBagConstraints lc = new GridBagConstraints();
        lc.gridx = 1;
        lc.gridy = 1;
        lc.ipadx = 10;
        lc.ipady = 10;
        lc.insets = insets;
        panel.add(label, lc);

        JTextField textField = new JTextField();
        textField.addActionListener(event -> {
            String username = event.getActionCommand();
            textField.setText("");
            enterUsername(username);
        });
        GridBagConstraints tfc = new GridBagConstraints();
        tfc.gridx = 1;
        tfc.gridy = 2;
        tfc.insets = insets;
        tfc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(textField, tfc);

        JButton button = new JButton("OK");
        button.addActionListener(event -> {
            String username = textField.getText();
            textField.setText("");
            enterUsername(username);
        });
        GridBagConstraints bc = new GridBagConstraints();
        bc.gridx = 1;
        bc.gridy = 3;
        bc.insets = insets;
        bc.anchor = GridBagConstraints.LAST_LINE_END;
        panel.add(button, bc);

        return panel;
    }

    /**
     * Create a loadingScreen panel.
     *
     * @return The created loadingScreen panel.
     */
    private JPanel createLoadingScreen() {
        JPanel panel = new JPanel(new GridBagLayout());

        JLabel label = new JLabel("Loading ...");

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 1;

        panel.add(label, c);
        return panel;
    }

    /**
     * Create a gameScreen panel.
     *
     * @return The created gameScreen panel.
     */
    private JPanel createGameScreen() {
        JPanel panel = new JPanel(new BorderLayout());

        // Top
        setTurnUsername("<USERNAME>");
        JPanel top = new JPanel(new GridBagLayout());
        GridBagConstraints top_c = new GridBagConstraints();
        top_c.insets = new Insets(10, 5, 5, 5);
        top.add(this.turnUsername, top_c);

        // Center
        JPanel center = new JPanel(new GridBagLayout());
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = new JButton(""); // TODO: Ver depois

                int side = WIDTH < HEIGHT ? WIDTH : HEIGHT;
                side = (side - 20) / 3;
                board[i][j].setPreferredSize(new Dimension(side, side));
                int row = i;
                int col = j;
                board[i][j].addActionListener(event -> {
                    play(row, col);
                });

                GridBagConstraints c = new GridBagConstraints();
                c.gridx = i;
                c.gridy = j;
                center.add(board[i][j], c);
            }
        }

        panel.add(top, BorderLayout.PAGE_START);
        panel.add(center, BorderLayout.CENTER);
        return panel;
    }

    public GUISwing(IClientController client) {
        super("TicTacToe");

        this.client = client;
        this.board = new JButton[3][3];
        this.homeScreen = createHomeScreen();
        this.loadingScreen = createLoadingScreen();
        this.gameScreen = createGameScreen();

        this.updateScreen(homeScreen);
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setSize(WIDTH, HEIGHT);
        super.setResizable(false);
        super.setVisible(true);
    }

    @Override
    public void showGameScreen(boolean blocked) {
        // TODO: if "blocked" is true, the user cannot be
        // able to interact with the game
        updateScreen(gameScreen);
    }

    @Override
    public void showInvalidPlayScreen() {
        // TODO Auto-generated method stub

    }

    @Override
    public void showNotYourTurnScreen() {
        // TODO Auto-generated method stub

    }

    @Override
    public void drawOpponentPlay(int row, int col) {
        // TODO Auto-generated method stub

    }

    @Override
    public void finishGameWithWin(int[][] winCoordinates) {
        // TODO Auto-generated method stub

    }

    @Override
    public void finishGameWithDraw() {
        // TODO Auto-generated method stub

    }

    @Override
    public void finishGameWithLoss(int[][] winCoordinates) {
        // TODO Auto-generated method stub

    }

    @Override
    public void goToThisPlayerTurn() {
        // TODO Auto-generated method stub

    }

    @Override
    public void waitOtherPlayerTurn() {
        // TODO Auto-generated method stub

    }

}
