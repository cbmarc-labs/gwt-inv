/**
 * 
 */
package cbmarc.inventory.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import cbmarc.inventory.client.mvp.contact.ContactsService;
import cbmarc.inventory.shared.entity.Contact;

import com.google.appengine.repackaged.com.google.common.collect.Lists;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author MCOSTA
 *
 */
@SuppressWarnings("serial")
public class ContactsServiceImpl extends RemoteServiceServlet 
		implements ContactsService {

	/**
	 * 
	 */
	public ContactsServiceImpl() {
	}

	/* (non-Javadoc)
	 * @see cbmarc.inventory.client.mvp.contact.ContactsService#delete(java.lang.Long)
	 */
	@Override
	public Boolean delete(Long id) throws Exception {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try {
			pm.currentTransaction().begin();
			Contact contact = pm.getObjectById(Contact.class, id);
			pm.deletePersistent(contact);
			
			pm.currentTransaction().commit();
		} catch(Exception e) {
			pm.currentTransaction().rollback();
			throw new Exception(e);
		} finally {
			pm.close();
		}
		
		return true;
	}

	/* (non-Javadoc)
	 * @see cbmarc.inventory.client.mvp.contact.ContactsService#delete(java.util.ArrayList)
	 */
	@Override
	public List<Contact> delete(ArrayList<Long> ids) {
		for (int i = 0; i < ids.size(); ++i) {
			try {
				delete(ids.get(i));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return select();
	}

	/* (non-Javadoc)
	 * @see cbmarc.inventory.client.mvp.contact.ContactsService#select(java.lang.Long)
	 */
	@Override
	public Contact select(Long id) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String query = "select UNIQUE from " + Contact.class.getName()
			+ " where id == :contactId";
		Query q = pm.newQuery(query);
		
		Contact result = (Contact) q.execute(id);

		return result;
	}

	/* (non-Javadoc)
	 * @see cbmarc.inventory.client.mvp.contact.ContactsService#select()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Contact> select() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<Contact> result;
		
		try {
			Query query = pm.newQuery(Contact.class);
			
			query.setOrdering("date desc");
			//query.setRange(first, first + count);
			
			result = (List<Contact>) query.execute();
			result = Lists.newArrayList(pm.detachCopyAll(result));
		} finally {
			pm.close();
		}
		
		return result;
	}

	/* (non-Javadoc)
	 * @see cbmarc.inventory.client.mvp.contact.ContactsService#save(cbmarc.inventory.shared.entity.Contact)
	 */
	@Override
	public Contact save(Contact contact) throws Exception {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		if(contact.getId() == null) {
			// Perform an insert
			
			Query query = pm.newQuery(Contact.class);
			query.setResult("count(this)");
			Integer count = (Integer)query.execute();
			
			if(count < 25) {
				try {
					pm.currentTransaction().begin();
					
					contact.setDate(new Date());
					pm.makePersistent(contact);
					
					pm.currentTransaction().commit();
				} catch(Exception e) {
					pm.currentTransaction().rollback();
					throw new Exception(e);
				} finally {
					pm.close();
				}
			} else {
				throw new Exception("Limit exceeded.");
			}
		} else {
			// Perform an update
			
			try {
				Contact c = pm.getObjectById(Contact.class, contact.getId());
				c.setFirstName(contact.getFirstName());
				c.setLastName(contact.getLastName());
				c.setEmailAddress(contact.getEmailAddress());
				
				pm.currentTransaction().begin();
				pm.makePersistent(contact);
				pm.currentTransaction().commit();
			} catch(Exception e) {
				pm.currentTransaction().rollback();
				throw new Exception(e);
			} finally {
				pm.close();
			}
		}
		
		return contact;
	}

	/* (non-Javadoc)
	 * @see cbmarc.inventory.client.mvp.contact.ContactsService#count()
	 */
	@Override
	public Integer count() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		final Query query = pm.newQuery(Contact.class);
		Integer res;

		query.setResult("count(this)");
		
		try {
			res = (Integer) query.execute();
		} finally {
			pm.close();
		}
		
		return res;
	}

}
