package com.example.tictactoe.model;

import java.util.ArrayList;
import java.util.List;

import com.example.tictactoe.model.exception.GameHasNotStartedException;
import com.example.tictactoe.model.exception.GameIsFullException;
import com.example.tictactoe.model.exception.GameIsOverException;
import com.example.tictactoe.model.exception.NameAlreadyExistsException;
import com.example.tictactoe.model.exception.NotYourTurnException;
import com.example.tictactoe.model.exception.OutOfBoardException;
import com.example.tictactoe.model.exception.TokenDoesNotExistException;

public class Game {
	private List<Player> players;
	private Board board;
	private String currPlayerName;
	
	// ========= Singleton Methods ========== //
	private static Game instance = new Game();
	public static Game getInstance() {
		return instance;
	}
	private Game() {
		board = new Board();
		currPlayerName = null;
		players = new ArrayList<>();
	}
	// ====================================== //
	
	public String addPlayer(String name) throws GameIsFullException, NameAlreadyExistsException {
		if(players.size() >= 2)
			throw new GameIsFullException();
		for(Player player : players)
			if(player.getName().equals(name))
				throw new NameAlreadyExistsException();
		
		String token = this.generateToken();
		String label = players.isEmpty() ? "X" : "O";

		Player player = new Player(token, name, label);
		players.add(player);
		if(currPlayerName == null)
			currPlayerName = player.getName();
		
		return token;
	}
	
	public GameInfo getGameInfo(String token) throws TokenDoesNotExistException, GameHasNotStartedException {
		Player you = getPlayer(token);
		Player opponent = getOpponent(token);
		
		return new GameInfo(opponent.getName(), 
				opponent.getLabel(), 
				you.getName(), 
				you.getLabel(),
				currPlayerName);
	}
	
	public GameStatus getGameStatus(String token) throws TokenDoesNotExistException, GameHasNotStartedException {
		Player you = getPlayer(token);
		
		String status = null;
		Boolean isYourTurn = null;
		List<List<String>> board = null;
		List<List<Integer>> winCoordinates = getWinCoordinates();
		
		if(gameIsRunning()) {
			status = GameStatus.Status.RUNNING;
			isYourTurn = you.getName().equals(currPlayerName);
			board = this.board.toJSONMatrix();
		}
		else if(isDraw(token))
			status = GameStatus.Status.DRAW;
		else if(isLose(token))
			status = GameStatus.Status.LOSE;
		else if(isWin(token))
			status = GameStatus.Status.WIN;
		
		return new GameStatus(status, isYourTurn, board, winCoordinates);
	}
	
	public void play(int row, int col, String token) 
			throws TokenDoesNotExistException, 
			GameHasNotStartedException, 
			OutOfBoardException, 
			NotYourTurnException, 
			GameIsOverException {
		
		// Check if token is valid and if game has started
		Player you = getPlayer(token);
		
		// Check if row and col are not out of bounds
		if(row < 0 || col < 0 || row >= 3 || col >= 3)
			throw new OutOfBoardException();			
		
		// Check if it is your turn
		if(!you.getName().equals(currPlayerName))
			throw new NotYourTurnException();
		
		// Check if game is running
		if(!gameIsRunning())
			throw new GameIsOverException();
		
		board.setCell(row, col, you.getLabel());
	}

	// Private Methods
	// TODO: Ver como gerar bons tokens
	private String generateToken() {
		return Integer.toString(players.size());
	}

	private Player getPlayer(String token) throws TokenDoesNotExistException, GameHasNotStartedException {
		if(!gameHasStarted())
			throw new GameHasNotStartedException();
		if(players.get(0).getToken().equals(token))
			return players.get(0);
		else if(players.get(1).getToken().equals(token))
			return players.get(1);
		else
			throw new TokenDoesNotExistException();
	}
	
	private Player getOpponent(String yourToken) throws TokenDoesNotExistException, GameHasNotStartedException {
		if(!gameHasStarted())
			throw new GameHasNotStartedException();
		if(players.get(0).getToken().equals(yourToken))
			return players.get(1);
		else if(players.get(1).getToken().equals(yourToken))
			return players.get(0);
		else
			throw new TokenDoesNotExistException();
	}
	
	private boolean gameHasStarted() {
		return !(players.size() < 2);
	}
	
	private boolean gameIsRunning() {
		if(!gameHasStarted())
			return false;
		
		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 3; j++)
				if(board.getCell(i, j).equals(" "))
					return true;
		return false;
	}

	private boolean isDraw(String token) throws TokenDoesNotExistException {
		return gameHasStarted() && !gameIsRunning() && !isWin(token) && !isLose(token);
	}
	
	private boolean isWin(String token) throws TokenDoesNotExistException {
		if(gameIsRunning())
			return false;
		
		try {
			String yourLabel = getPlayer(token).getLabel();
			
			// Rows
			for(int i = 0; i < 3; i++) {
				boolean win = true;
				for(int j = 0; j < 3; j++) {
					if(!board.getCell(i, j).equals(yourLabel)) {
						win = false;
						break;
					}
				}
				
				if(win)
					return true;
			}
			
			// Columns
			for(int i = 0; i < 3; i++) {
				boolean win = true;
				for(int j = 0; j < 3; j++) {
					if(!board.getCell(j, i).equals(yourLabel)) {
						win = false;
						break;
					}
				}
				
				if(win)
					return true;
			}
			
			// First Diagonal
			boolean win = true;
			for(int i = 0; i < 3; i++) {
				if(!board.getCell(i, i).equals(yourLabel)) {
					win = false;
					break;
				}
			}
			if(win)
				return true;
			
			// Second Diagonal
			win = true;
			for(int i = 0; i < 3; i++) {
				if(!board.getCell(i, 2-i).equals(yourLabel)) {
					win = false;
					break;
				}
			}
			if(win)
				return true;
		} catch (GameHasNotStartedException e) {
			return false;
		}
		
		return false;
	}
	
	private boolean isLose(String token) throws TokenDoesNotExistException {
		try {
			String opponentToken = getOpponent(token).getToken();
			return !isWin(opponentToken);
		} catch (GameHasNotStartedException e) {
			return false;
		}
	}
	
	private List<List<Integer>> getWinCoordinates() {
		if(!gameHasStarted() || gameIsRunning())
			return null;
		
		// Rows
		for(int i = 0; i < 3; i++) {
			boolean win = true;
			String label = board.getCell(i, 0);
			for(int j = 1; j < 3; j++) {
				if(!board.getCell(i, j).equals(label)) {
					win = false;
					break;
				}
			}
			
			if(win) {
				List<List<Integer>> winCoordinates = new ArrayList<>();
				
				for(int j = 0; j < 3; j++) {
					List<Integer> tmp = new ArrayList<>();
					tmp.add(i);
					tmp.add(j);
					winCoordinates.add(tmp);
				}

				return winCoordinates;
			}
		}
		
		// Columns
		for(int i = 0; i < 3; i++) {
			boolean win = true;
			String label = board.getCell(0, i);
			for(int j = 1; j < 3; j++) {
				if(!board.getCell(j, i).equals(label)) {
					win = false;
					break;
				}
			}
			
			if(win) {
				List<List<Integer>> winCoordinates = new ArrayList<>();
				
				for(int j = 0; j < 3; j++) {
					List<Integer> tmp = new ArrayList<>();
					tmp.add(j);
					tmp.add(i);
					winCoordinates.add(tmp);
				}

				return winCoordinates;
			}
		}
		
		// First Diagonal
		boolean win = true;
		String label = board.getCell(0, 0);
		for(int i = 0; i < 3; i++) {
			if(!board.getCell(i, i).equals(label)) {
				win = false;
				break;
			}
		}
		if(win) {
			List<List<Integer>> winCoordinates = new ArrayList<>();
			
			for(int i = 0; i < 3; i++) {
				List<Integer> tmp = new ArrayList<>();
				tmp.add(i);
				tmp.add(i);
				winCoordinates.add(tmp);
			}

			return winCoordinates;
		}
		
		// Second Diagonal
		win = true;
		label = board.getCell(0,2);
		for(int i = 1; i < 3; i++) {
			if(!board.getCell(i, 2-i).equals(label)) {
				win = false;
				break;
			}
		}
		if(win) {
			List<List<Integer>> winCoordinates = new ArrayList<>();
			
			for(int i = 0; i < 3; i++) {
				List<Integer> tmp = new ArrayList<>();
				tmp.add(i);
				tmp.add(2-i);
				winCoordinates.add(tmp);
			}

			return winCoordinates;
		}
		
		return null;
			
	}
}
