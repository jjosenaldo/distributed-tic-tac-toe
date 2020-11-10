package com.example.tictactoe.controller;

import com.example.tictactoe.model.GameInfo;
import com.example.tictactoe.model.GameStatus;
import com.example.tictactoe.model.exception.ErrorException;

public interface IClientController {
	public GameInfo info(String token) throws ErrorException;
	public GameStatus status(String token) throws ErrorException;
	public void play(int row, int col, String token) throws ErrorException;
	public String register(String name) throws ErrorException;
}