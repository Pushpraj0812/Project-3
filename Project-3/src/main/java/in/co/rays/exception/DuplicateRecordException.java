package in.co.rays.exception;

/**
 * DuplicateRecordException thrown when a duplicate record occurred.
 * 
 * @author Pushpraj Singh Kachhaway
 *
 */

public class DuplicateRecordException extends Exception {

	public DuplicateRecordException(String msg) {
		super(msg);
	}
}