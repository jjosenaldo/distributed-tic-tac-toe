package client;

public interface IClientController {
	public String getYourLabel();
	public void play(int row, int col);
	public boolean register(String username);
}
