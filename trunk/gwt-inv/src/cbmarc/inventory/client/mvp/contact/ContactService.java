/**
 * 
 */
package cbmarc.inventory.client.mvp.contact;

import java.util.ArrayList;
import java.util.List;

import cbmarc.inventory.shared.entity.Contact;
import cbmarc.inventory.shared.exception.ServerException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author MCOSTA
 *
 */
@RemoteServiceRelativePath("contactService")
public interface ContactService extends RemoteService {
	Boolean delete(Long id) throws ServerException;
	Contact save(Contact contact) throws ServerException;
	
	List<Contact> delete(ArrayList<Long> ids);
	List<Contact> select();
	
	Integer count();
	Contact select(Long id);
}
