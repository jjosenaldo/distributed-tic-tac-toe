package com.example.tictactoe.model.response;

import org.springframework.http.HttpStatus;

public class RegistrationResponse extends Response {
	public static class Content {
		private String token;

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

	public RegistrationResponse(String status, String message, String token, HttpStatus httpStatus) {
		super(status, message, httpStatus);
		if (token != null)
			this.content = new Content(token);
		else
			this.content = null;
	}

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	public static RegistrationResponse noPlaceAvailable() {
		return new RegistrationResponse(Response.ERROR, "Não há vagas disponíveis.", null,
				HttpStatus.SERVICE_UNAVAILABLE);
	}

	public static RegistrationResponse nameAlreadyUsed() {
		return new RegistrationResponse(Response.ERROR, "O nome informado já está em uso.", null,
				HttpStatus.UNPROCESSABLE_ENTITY);
	}

	public static RegistrationResponse ok(String token) {
		return new RegistrationResponse(Response.OK, "Usuário registrado com sucesso.", token, HttpStatus.OK);
	}
}
