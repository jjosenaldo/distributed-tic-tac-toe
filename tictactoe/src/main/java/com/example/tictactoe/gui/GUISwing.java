package com.example.tictactoe.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.example.tictactoe.controller.IClientController;
import com.example.tictactoe.model.GameInfo;
import com.example.tictactoe.model.GameStatus;
import com.example.tictactoe.model.exception.ErrorException;

public class GUISwing extends JFrame {
	private static final long serialVersionUID = -1202528917858671445L;
	private static final int WIDTH = 400;
    private static final int HEIGHT = 600;
    
    private final IClientController controller;
    private final JButton[][] board;
    private final JPanel homeScreen;
    private final JPanel loadingScreen;
    private final JPanel gameScreen;
    
    private JLabel turnUsername;
    private String token;
    private GameInfo gameInfo;
    
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
            this.enterUsername(username);
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
            this.enterUsername(username);
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
     * Create a gameScreen panel.
     *
     * @return The created gameScreen panel.
     */
    private JPanel createGameScreen() {
        JPanel panel = new JPanel(new BorderLayout());

        // Top
        this.turnUsername = new JLabel("<USERNAME>'s turn");
        this.turnUsername.setFont(new Font("Arial", Font.PLAIN, 20));
        JPanel top = new JPanel(new GridBagLayout());
        GridBagConstraints top_c = new GridBagConstraints();
        top_c.insets = new Insets(20, 5, 5, 5);
        top.add(this.turnUsername, top_c);
        

        // Center
        JPanel center = new JPanel(new GridBagLayout());
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = new JButton("");
                board[i][j].setFont(new Font("Arial", Font.PLAIN, 100));

                int side = Math.min(WIDTH, HEIGHT);
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
    
    /**
     * Create a loadingScreen panel.
     *
     * @return The created loadingScreen panel.
     */
    private JPanel createSimpleScreenWithText(String text) {
        JPanel panel = new JPanel(new GridBagLayout());

        JLabel label = new JLabel(text);

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 1;

        panel.add(label, c);
        return panel;
    }
    
    public GUISwing(IClientController controller) {
        super("TicTacToe");

        this.controller = controller;
        this.board = new JButton[3][3];
        this.homeScreen = createHomeScreen();
        this.loadingScreen = createSimpleScreenWithText("Loading");
        this.gameScreen = createGameScreen();

        this.updateScreen(homeScreen);
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setSize(WIDTH, HEIGHT);
        super.setResizable(false);
        super.setVisible(true);
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
     * This method is called when the user enter its username in GUI.
     *
     * @param username The chosen username.
     */
    private void enterUsername(String username) {
        updateScreen(loadingScreen);
        
        try {
        	// Tenta registrar o username
			token = controller.register(username);
			
	        // Espera o jogo começar
			new Thread() {
				@Override
				public void run() {
					waitGameStart();
					waitYourTurn();
				}
			}.run();
		} catch (ErrorException e) {
			// Alerta o usuário do erro
			JOptionPane.showMessageDialog(this,
				    e.getMessage(),
				    "Erro de autenticação",
				    JOptionPane.ERROR_MESSAGE);

			// Volta para a tela principal
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
        	try {
				controller.play(row, col, token);
	            drawPlay(row, col, gameInfo.getYourLabel());
	            setTurnUsername(gameInfo.getOpponentName());
	            showGameScreen(true);
	            
	            // Espera sua vez
	            new Thread() {
	            	@Override
	            	public void run() {
	            		waitYourTurn();
	            	}
	            }.run();
			} catch (ErrorException e) {
				// Alerta o usuário do erro
				JOptionPane.showMessageDialog(this,
					    e.getMessage(),
					    "Jogada inválida",
					    JOptionPane.ERROR_MESSAGE);
			}
        }
    }
    
    private void drawPlay(int row, int col, String label) {
        board[row][col].setText(label);
    }
    
    /**
     * Set the username in gameScreen.
     *
     * @param username The username to be set.
     */
    private void setTurnUsername(String username) {
        this.turnUsername.setText(username + "'s turn");
    }

    private void showGameScreen(boolean blocked) {
    	for (int i = 0; i < 3; i++)
    		for (int j = 0; j < 3; j++)
    			board[i][j].setEnabled(!blocked);
    	updateScreen(gameScreen);
    }

    private void finishGameWithWin(List<List<Integer>> winCoordinates) {
        for(int i = 0; i < 3; i++) {
        	int row = winCoordinates.get(i).get(0);
        	int col = winCoordinates.get(i).get(1);
        	
        	board[row][col].setBackground(Color.GREEN);
        }
        
        this.turnUsername.setText("YOU WIN");
        this.turnUsername.setFont(new Font("Arial", Font.PLAIN, 40));
        this.turnUsername.setForeground(Color.GREEN);
        
        showGameScreen(true);
    }

    private void finishGameWithDraw() {
    	this.turnUsername.setText("DRAW");
        this.turnUsername.setFont(new Font("Arial", Font.PLAIN, 40));
        this.turnUsername.setForeground(Color.GRAY);

        showGameScreen(true);
    }

    private void finishGameWithLoss(List<List<Integer>> winCoordinates) {
    	for(int i = 0; i < 3; i++) {
        	int row = winCoordinates.get(i).get(0);
        	int col = winCoordinates.get(i).get(1);
        	
        	board[row][col].setBackground(Color.RED);
        }
        
        this.turnUsername.setText("YOU LOSE");
        this.turnUsername.setFont(new Font("Arial", Font.PLAIN, 40));
        this.turnUsername.setForeground(Color.RED);
        
        showGameScreen(true);
    }
    
    private void waitGameStart() {
    	boolean started = false;
    	do {
    		try {
				GameStatus status = controller.status(token);
				if(status.getStatus().equals(GameStatus.Status.RUNNING)) {
					startGame(status.getBoard());
					started = true;
				}
				Thread.sleep(1000);
			} catch (ErrorException e) {
				JOptionPane.showMessageDialog(this,
					    e.getMessage(),
					    "Erro ao recuperar o status do jogo!",
					    JOptionPane.ERROR_MESSAGE);
				System.exit(-1);
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.exit(-1);
			}
    	} while(started);
    }
    
    private void startGame(List<List<String>> initialBoard) {
    	try {
			gameInfo = controller.info(token);
			setBoard(initialBoard);
			setTurnUsername(gameInfo.getInitialPlayerName());
			showGameScreen(gameInfo.getInitialPlayerName().equals(gameInfo.getYourName()));
		} catch (ErrorException e) {
			e.printStackTrace();
			System.exit(-1);
		}
    }
    
    private void setBoard(List<List<String>> board) {
    	for(int i = 0; i < 3; i++)
    		for(int j = 0; j < 3; j++)
    			this.board[i][j].setText(board.get(i).get(j));
    }

    private void waitYourTurn() {
    	do {
    		try {
				GameStatus status = controller.status(token);
				if(status.getStatus().equals(GameStatus.Status.RUNNING)) {
					setTurnUsername(gameInfo.getYourName());
					setBoard(status.getBoard());
					showGameScreen(!status.getYourTurn());
					return;
				} else if(status.getStatus().equals(GameStatus.Status.WIN)) {
					finishGameWithWin(status.getWinCoordinates());
					return;
				} else if(status.getStatus().equals(GameStatus.Status.LOSE)) {
					finishGameWithLoss(status.getWinCoordinates());
					return;
				} else if(status.getStatus().equals(GameStatus.Status.DRAW)) {
					finishGameWithDraw();
					return;
				}
				
				Thread.sleep(1000);
			} catch (ErrorException e) {
				JOptionPane.showMessageDialog(this,
					    e.getMessage(),
					    "Erro ao recuperar o status do jogo!",
					    JOptionPane.ERROR_MESSAGE);
				System.exit(-1);
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.exit(-1);
			}
    	} while(true);
    }
}
