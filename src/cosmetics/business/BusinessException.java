package cosmetics.business;

public class BusinessException extends Exception {

	private static final long serialVersionUID = -551906835171529361L;

	private String[] args;

	/**
	 * Create a new instance of BusinessException.
	 */
	public BusinessException() {
		this.args = new String[0];
	}

	/**
	 * Create a new instance of BusinessException.
	 * 
	 * @param message the message to show.
	 */
	public BusinessException(final String message) {
		super(message);
		this.args = new String[0];
	}

	/**
	 * Create a new instance of BusinessException.
	 * 
	 * @param message the message to show.
	 * @param arg     the message argument.
	 */
	public BusinessException(final String message, final String arg) {
		super(message);
		this.args = new String[] { arg };
	}

	/**
	 * Create a new instance of BusinessException.
	 * 
	 * @param message the message to show.
	 * @param arg     the message argument.
	 * @param cause   the exception that caused this exception.
	 */
	public BusinessException(final String message, final String arg, final Throwable cause) {
		super(message, cause);
		this.args = new String[] { arg };
	}

	/**
	 * Create a new instance of BusinessException.
	 * 
	 * @param message the message to show.
	 * @param args    the message arguments.
	 */
	public BusinessException(final String message, final String[] args) {
		super(message);
		this.args = args;
	}

	/**
	 * Create a new instance of BusinessException.
	 * 
	 * @param message the message to show.
	 * @param args    the message arguments.
	 * @param cause   the exception that caused this exception.
	 */
	public BusinessException(final String message, final String[] args, final Throwable cause) {
		super(message, cause);
		this.args = args;
	}

	/**
	 * Create a new instance of BusinessException.
	 * 
	 * @param message the message to show.
	 * @param cause   the exception that caused this exception.
	 */
	public BusinessException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Create a new instance of BusinessException.
	 * 
	 * @param cause the exception that caused this exception.
	 */
	public BusinessException(final Throwable cause) {
		super(cause);
	}

	public String[] getArgs() {
		return args;
	}

}