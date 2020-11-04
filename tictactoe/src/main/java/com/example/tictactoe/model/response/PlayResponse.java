package com.example.tictactoe.model.response;

public class PlayResponse extends Response {
	public PlayResponse() {
		super(null, null);
	}
	
    public PlayResponse(String status, String message) {
    	super(status, message);
    }
    
    public void setContent(Object content) {
    	/* Nothing */
    }
    
    public Object getContent() {
    	return null;
    }
}