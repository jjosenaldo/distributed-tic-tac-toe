package com.example.tictactoe;

import com.example.tictactoe.controller.ClientController;
import com.example.tictactoe.controller.IClientController;
import com.example.tictactoe.gui.GUISwing;
import com.example.tictactoe.model.exception.ErrorException;

public class ClientApp {
	public static void test() {
		IClientController c = new ClientController();
		try {
			c.register("za");
			c.register("za");
			c.register("ze");
			c.register("zi");
		} catch (ErrorException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new GUISwing(new ClientController());
	}
}
