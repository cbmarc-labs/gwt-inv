/**
 * 
 */
package cbmarc.inventory.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import cbmarc.inventory.client.mvp.parte.ParteService;
import cbmarc.inventory.shared.entity.Parte;

import com.google.appengine.repackaged.com.google.common.collect.Lists;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author MCOSTA
 *
 */
@SuppressWarnings("serial")
public class ParteServiceImpl extends RemoteServiceServlet 
		implements ParteService {

	/**
	 * 
	 */
	public ParteServiceImpl() {
	}

	@Override
	public Boolean delete(Long id) throws Exception {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try {
			pm.currentTransaction().begin();
			Parte diarioparte = pm.getObjectById(Parte.class, id);
			pm.deletePersistent(diarioparte);
			
			pm.currentTransaction().commit();
		} catch(Exception e) {
			pm.currentTransaction().rollback();
			throw new Exception(e);
		} finally {
			pm.close();
		}
		
		return true;
	}

	@Override
	public List<Parte> delete(ArrayList<Long> ids) {
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

	@Override
	public Parte select(Long id) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String query = "select UNIQUE from " + Parte.class.getName()
			+ " where id == :registerId";
		Query q = pm.newQuery(query);
		
		Parte result = (Parte) q.execute(id);

		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Parte> select() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<Parte> result;
		
		try {
			Query query = pm.newQuery(Parte.class);
			
			query.setOrdering("date desc");
			//query.setRange(first, first + count);
			
			result = (List<Parte>) query.execute();
			result = Lists.newArrayList(pm.detachCopyAll(result));
		} finally {
			pm.close();
		}
		
		return result;
	}

	@Override
	public Parte save(Parte parte) throws Exception {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		System.out.println(parte.getAtu());
		
		if(parte.getId() == null) {
			// Perform an insert
			
			Query query = pm.newQuery(Parte.class);
			query.setResult("count(this)");
			Integer count = (Integer)query.execute();
			
			if(count < 25) {
				try {
					pm.currentTransaction().begin();
					
					parte.setDate(new Date());
					pm.makePersistent(parte);
					
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
				Parte object = pm.getObjectById(
						Parte.class, parte.getId());
				
				pm.currentTransaction().begin();
				pm.makePersistent(parte);
				pm.currentTransaction().commit();
			} catch(Exception e) {
				pm.currentTransaction().rollback();
				throw new Exception(e);
			} finally {
				pm.close();
			}
		}
		
		return parte;
	}

	@Override
	public Integer count() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		final Query query = pm.newQuery(Parte.class);
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
