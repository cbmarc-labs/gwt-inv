/**
 * 
 */
package cbmarc.inventory.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import cbmarc.inventory.client.mvp.contact.ContactsService;
import cbmarc.inventory.shared.entity.Contact;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author MCOSTA
 *
 */
@SuppressWarnings("serial")
public class ContactsServiceImpl extends RemoteServiceServlet 
		implements ContactsService {
	
	private final HashMap<Long, Contact> contacts = 
		new HashMap<Long, Contact>();

	/**
	 * 
	 */
	public ContactsServiceImpl() {
		//contacts = new ArrayList<Contact>();
		
		contacts.put(Long.parseLong("0"), new Contact(Long.parseLong("0"), "este es el cero", "0", "0"));
		contacts.put(Long.parseLong("1"), new Contact(Long.parseLong("1"), "este es el uno", "1", "1"));
		contacts.put(Long.parseLong("2"), new Contact(Long.parseLong("2"), "este es el dos", "2", "2"));
		contacts.put(Long.parseLong("3"), new Contact(Long.parseLong("3"), "este es el tres", "3", "3"));
		contacts.put(Long.parseLong("4"), new Contact(Long.parseLong("4"), "este es el cuatro", "4", "4"));
		contacts.put(Long.parseLong("5"), new Contact(Long.parseLong("5"), "este es el cinco", "5", "5"));
		contacts.put(Long.parseLong("6"), new Contact(Long.parseLong("6"), "este es el seis", "6", "6"));
		contacts.put(Long.parseLong("7"), new Contact(Long.parseLong("7"), "este es el siete", "7", "7"));
		contacts.put(Long.parseLong("8"), new Contact(Long.parseLong("8"), "este es el ocho", "8", "8"));
		contacts.put(Long.parseLong("9"), new Contact(Long.parseLong("9"), "este es el nueve", "9", "9"));
	}

	@Override
	public Boolean delete(Long id) {
		contacts.remove(id);
		
		return null;
	}

	@Override
	public ArrayList<Contact> delete(ArrayList<Long> ids) {
		for (int i = 0; i < ids.size(); ++i) {
			delete(ids.get(i));
		}
		
		return select();
	}

	@Override
	public Contact select(Long id) {
		return contacts.get(id);
	}

	@Override
	public ArrayList<Contact> select() {
		ArrayList<Contact> result = new ArrayList<Contact>();
		
		Iterator<Long> it = contacts.keySet().iterator();
		while(it.hasNext()) { 
			result.add(contacts.get(it.next()));
		}
		
		return result;
	}

	@Override
	public Contact save(Contact contact) {
		if(contact.getId() == null) {
			// perform a insert
			contact.setId((long) contacts.size());
			contacts.put(contact.getId(), contact);
		} else {
			// perform a update
			contacts.remove(contact.getId());
			contacts.put(contact.getId(), contact);
		}
		
		return contact;
	}

}
