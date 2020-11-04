package com.example.tictactoe.model.response;

public class RegistrationResponse extends Response {
	public static class Content {
	    private String token;
	    
	    public Content() {
			token = null;
		}
	    
		public Content(String token) {
			this.token = token;
		}

		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}
	}
	
    private Content content;

    public RegistrationResponse() {
    	super(null, null);
    	content = null;
    }
    
    public RegistrationResponse(String status, String message, String token) {
    	super(status, message);
    	if(token == null)
    		content = null;
    	else
    		content = new Content(token);
    }

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}
}
