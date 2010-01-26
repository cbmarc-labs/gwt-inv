/**
 * 
 */
package cbmarc.inventory.client.mvp.contact;

import java.util.List;

import cbmarc.inventory.shared.entity.Contact;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author MCOSTA
 *
 */
@RemoteServiceRelativePath("contactsService")
public interface ContactsService extends RemoteService {
	Boolean delete(Long id) throws Exception;
	Contact save(Contact contact) throws Exception;
	
	List<Contact> delete(List<Long> ids);
	List<Contact> select();
	
	Integer count();
	
	Contact select(Long id);
}
