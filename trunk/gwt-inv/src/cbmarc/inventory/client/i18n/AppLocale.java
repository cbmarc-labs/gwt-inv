/**
 * 
 */
package cbmarc.inventory.client.i18n;

import com.google.gwt.core.client.GWT;

/**
 * @author MCOSTA
 *
 */
public class AppLocale {
	private static AppConstants _const = null;
	
	public static AppConstants constants() {
		if (_const == null)
			_const = (AppConstants)GWT.create(AppConstants.class);
		
		return _const;
	}
	
	private static AppMessages _mess = null;
	
	public static AppMessages messages() {
		if (_mess == null)
			_mess = (AppMessages)GWT.create(AppMessages.class);
		
		return _mess;
	}
}
