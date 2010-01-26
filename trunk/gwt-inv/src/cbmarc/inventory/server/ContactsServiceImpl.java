/**
 * 
 */
package cbmarc.inventory.server;

import java.util.ArrayList;

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
	
	private ArrayList<Contact> contacts;

	/**
	 * 
	 */
	public ContactsServiceImpl() {
		contacts = new ArrayList<Contact>();
		contacts.add(new Contact("0", "este es el cero", "0", "0"));
		contacts.add(new Contact("1", "este es el uno", "1", "1"));
		contacts.add(new Contact("2", "este es el dos", "2", "2"));
		contacts.add(new Contact("3", "este es el tres", "3", "3"));
		contacts.add(new Contact("4", "este es el cuatro", "4", "4"));
		contacts.add(new Contact("5", "este es el cinco", "5", "5"));
		contacts.add(new Contact("6", "este es el seis", "6", "6"));
		contacts.add(new Contact("7", "este es el siete", "7", "7"));
		contacts.add(new Contact("8", "este es el ocho", "8", "8"));
		contacts.add(new Contact("9", "este es el nueve", "9", "9"));
	}

	@Override
	public Boolean delete(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Contact> delete(ArrayList<String> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Contact getContact(String id) {
		return contacts.get(Integer.parseInt(id));
	}

	@Override
	public ArrayList<Contact> getContacts() {
		return contacts;
	}

	@Override
	public Contact insert(Contact contact) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Contact update(Contact contact) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Contact save(Contact contact) {
		// TODO Auto-generated method stub
		return null;
	}

}
