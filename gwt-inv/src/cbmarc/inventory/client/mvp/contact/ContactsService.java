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
	Boolean delete(Long id);
	Contact save(Contact contact);
	
	ArrayList<Contact> delete(ArrayList<Long> ids);
	ArrayList<Contact> select();
	
	Contact select(Long id);
}
