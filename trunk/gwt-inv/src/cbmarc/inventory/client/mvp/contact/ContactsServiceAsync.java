/**
 * 
 */
package cbmarc.inventory.client.mvp.contact;

import java.util.List;

import cbmarc.inventory.shared.entity.Contact;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author MCOSTA
 *
 */
public interface ContactsServiceAsync {
	public void delete(Long id, AsyncCallback<Boolean> callback);
	public void save(Contact contact, AsyncCallback<Contact> callback);
	
	public void count(AsyncCallback<Integer> callback);
	
	public void delete(List<Long> ids, AsyncCallback<List<Contact>> callback);
	public void select(AsyncCallback<List<Contact>> callback);
	public void select(Long id, AsyncCallback<Contact> callback);
}
