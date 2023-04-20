package br.com.uniasselvi.exception;


public class ParticipanteRegistrationException extends RuntimeException {
    private static final long serialVersionUID = 1L;

	public ParticipanteRegistrationException(String message) {
        super(message);
    }

    public ParticipanteRegistrationException(String message, Throwable cause) {
        super(message, cause);
    }
}