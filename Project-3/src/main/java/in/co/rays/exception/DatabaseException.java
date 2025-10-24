package in.co.rays.exception;

/**
 * DatabaseException is propogated by DAO classes when an unhandled Database
 * exception occurred.
 * 
 * @author Pushpraj Singh Kachhaway
 *
 */

public class DatabaseException extends Exception {

	public DatabaseException(String msg) {
		super(msg);
	}
}