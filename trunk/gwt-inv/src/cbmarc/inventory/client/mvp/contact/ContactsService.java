/**
 * 
 */
package cbmarc.inventory.client.mvp.contact;

import java.util.ArrayList;

import cbmarc.inventory.shared.entity.Contact;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author MCOSTA
 *
 */
@RemoteServiceRelativePath("contactsService")
public interface ContactsService extends RemoteService {
	Contact insert(Contact contact);
	Boolean delete(String id);
	Contact update(Contact contact);
	Contact save(Contact contact);
	
	ArrayList<Contact> delete(ArrayList<String> ids);
	ArrayList<Contact> getContacts();
	Contact getContact(String id);
}
