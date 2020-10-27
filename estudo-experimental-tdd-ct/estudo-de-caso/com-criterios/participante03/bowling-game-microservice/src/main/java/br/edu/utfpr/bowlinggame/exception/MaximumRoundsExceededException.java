package br.edu.utfpr.bowlinggame.exception;

public class MaximumRoundsExceededException extends RuntimeException {
    private static final long serialVersionUID = -1597779763246395468L;

    public MaximumRoundsExceededException(String message) {
        super(message);
    }
}
