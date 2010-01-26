/**
 * 
 */
package cbmarc.inventory.client.mvp.contact;

import java.util.ArrayList;

import cbmarc.inventory.shared.entity.Contact;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author MCOSTA
 *
 */
public interface ContactsServiceAsync {
	public void delete(Long id, AsyncCallback<Boolean> callback);
	public void save(Contact contact, AsyncCallback<Contact> callback);
	
	public void delete(ArrayList<Long> ids, AsyncCallback<ArrayList<Contact>> callback);
	public void select(AsyncCallback<ArrayList<Contact>> callback);
	public void select(Long id, AsyncCallback<Contact> callback);
}
