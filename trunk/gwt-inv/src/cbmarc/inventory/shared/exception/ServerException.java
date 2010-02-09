/**
 * 
 */
package cbmarc.inventory.shared.exception;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author MCOSTA
 *
 */
public class ServerException extends Exception implements IsSerializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ServerException() {
	}
	
	public ServerException(String msg) {
		super(msg);
	}

}
