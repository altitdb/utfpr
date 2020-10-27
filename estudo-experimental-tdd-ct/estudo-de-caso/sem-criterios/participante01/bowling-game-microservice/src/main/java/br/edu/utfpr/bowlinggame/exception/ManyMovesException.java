package br.edu.utfpr.bowlinggame.exception;

public class ManyMovesException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ManyMovesException() {
		super("There are already ten completed plays");
	}


}
