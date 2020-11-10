package com.example.tictactoe;

import com.example.tictactoe.controller.ClientController;
import com.example.tictactoe.gui.GUISwing;

public class ClientApp {
	public static void main(String[] args) {
		new GUISwing(new ClientController());
	}
}
