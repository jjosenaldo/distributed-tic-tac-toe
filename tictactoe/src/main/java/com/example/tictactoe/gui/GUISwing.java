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
import javax.swing.SwingWorker;

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
    
    // Initializer methods =================================================
    
    /**
     * Create a homeScreen panel.
     *
     * @return The created homeScreen panel.
     */
    private JPanel createHomeScreen() {

        JPanel panel = new JPanel(new GridBagLayout());
        Insets insets = new Insets(5, 5, 5, 5);

        JLabel label = new JLabel("Escolha um nome de usuário");
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

    // Auxiliary methods ===================================================
    
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

    private void showMessageError(String title, String message) {
    	JOptionPane.showMessageDialog(this,
			    message,
			    title,
			    JOptionPane.ERROR_MESSAGE);
    }

    private void setYourTurn(boolean yourTurn) {
    	// Set player name as turn label
    	if(yourTurn)
    		this.turnUsername.setText("Sua vez");
    	else
    		this.turnUsername.setText("Vez de " +  gameInfo.getOpponentName());
    	
    	// If its your turn, unlock game screen; otherwise lock game screen
    	for (int i = 0; i < 3; i++)
    		for (int j = 0; j < 3; j++)
    			board[i][j].setEnabled(yourTurn);
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
    
    private void updateGame(GameStatus status) {    	
    	// Update board
    	if(status.getBoard() != null)
	    	for(int i = 0; i < 3; i++)
	    		for(int j = 0; j < 3; j++)
	    			this.board[i][j].setText(status.getBoard().get(i).get(j).trim());
    	
    	// Check game status
    	if(status.getStatus().equals(GameStatus.Status.WIN))
    		this.finishGameWithWin(status.getWinCoordinates());
    	else if(status.getStatus().equals(GameStatus.Status.LOSE))
    		this.finishGameWithLoss(status.getWinCoordinates());
    	else if(status.getStatus().equals(GameStatus.Status.DRAW))
    		this.finishGameWithDraw();
    	else if(status.getStatus().equals(GameStatus.Status.RUNNING))
    		this.setYourTurn(status.getYourTurn());
    	
    	updateScreen(gameScreen);
    }
    
    private void startGame(GameStatus status) {
    	try {
    		// Save initial game information
			this.gameInfo = controller.info(token);
			
			// Update game
			updateGame(status);
			
			// Wait your turn
			// Wait your turn
            new SwingWorker<Void, Object>() {
				@Override
				protected Void doInBackground() throws Exception {
					waitYourTurn();
					return null;
				}
            }.execute();
		} catch (ErrorException e) {
			// Show a message error
			showMessageError("Erro ao tentar recuperar a informação inicial do jogo", e.getMessage());
			System.exit(-1);
		}
    }
    
    private void waitGameStart() {
    	do {
    		try {
				GameStatus status = controller.status(token);
				if(!status.getStatus().equals(GameStatus.Status.NOT_STARTED)) {
					startGame(status);
					return;
				}
				Thread.sleep(1000);
			} catch (ErrorException e) {
				this.showMessageError("Erro ao recuperar o status do jogo", e.getMessage());
				System.exit(-1);
			} catch (InterruptedException e) {
				this.showMessageError("A thread foi interrompida", e.getMessage());
				System.exit(-1);
			}
    	} while(true);
    }
    
    private void waitYourTurn() {
    	do {
    		try {
    			// Get game status
    			GameStatus status = controller.status(token);
    			
    			// Update game
    			this.updateGame(status);
    			
    			// If game is not running or it is your turn, break the loop
    			if(!status.getStatus().equals(GameStatus.Status.RUNNING) || 
    					status.getYourTurn())
    				break;
    			
				// Sleep for 1 second
				Thread.sleep(1000);
			} catch (ErrorException e) {
				this.showMessageError("Erro ao recuperar o status do jogo", e.getMessage());
				System.exit(-1);
			} catch (InterruptedException e) {
				this.showMessageError("A thread foi interrompida", e.getMessage());
				System.exit(-1);
			}
    	} while(true);
    }
    
    // GUI methods =========================================================
    
    /**
     * This method is called when the user enter its user name in GUI.
     *
     * @param username The chosen user name.
     */
    private void enterUsername(String username) {
        updateScreen(loadingScreen);
        try {
        	// Register user name
			token = controller.register(username);
			
			// Wait for game to start
			new SwingWorker<Void,Object>() {
				@Override
				protected Void doInBackground() throws Exception {
					waitGameStart();
					return null;
				}
			}.execute();
		} catch (ErrorException e) {
			// Show message error
			this.showMessageError("Erro de autenticação.", e.getMessage());

			// Return to home screen
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
        if (board[row][col].getText().trim().isEmpty()) {
        	try {
        		// Play 
				controller.play(row, col, token);
				
				// TODO: Decide if remove it 
//	            drawPlay(row, col, gameInfo.getYourLabel());
//	            
//	            // Set the turn for the opponent
//	            setYourTurn(false);
	            
	            // Update screen // TODO: Check if it can be removed
//	            updateScreen(gameScreen); 
	            
	            // Wait your turn
	            new SwingWorker<Void, Object>() {
					@Override
					protected Void doInBackground() throws Exception {
						waitYourTurn();
						return null;
					}
	            }.execute();
			} catch (ErrorException e) {
				// Show a message error
				showMessageError("Jogada inválida", e.getMessage());
			}
        }
    }
    
    
    
}
