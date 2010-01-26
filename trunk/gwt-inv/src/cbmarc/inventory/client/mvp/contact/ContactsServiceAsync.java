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
	public void insert(Contact contact, AsyncCallback<Contact> callback);
	public void delete(String id, AsyncCallback<Boolean> callback);
	public void update(Contact contact, AsyncCallback<Contact> callback);
	public void save(Contact contact, AsyncCallback<Contact> callback);
	
	public void delete(ArrayList<String> ids, AsyncCallback<ArrayList<Contact>> callback);
	public void getContacts(AsyncCallback<ArrayList<Contact>> callback);
	public void getContact(String id, AsyncCallback<Contact> callback);
}
