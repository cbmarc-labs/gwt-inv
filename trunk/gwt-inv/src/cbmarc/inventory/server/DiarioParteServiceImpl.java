/**
 * 
 */
package cbmarc.inventory.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import cbmarc.inventory.client.mvp.diarioparte.DiarioParteService;
import cbmarc.inventory.shared.entity.DiarioParte;

import com.google.appengine.repackaged.com.google.common.collect.Lists;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author MCOSTA
 *
 */
@SuppressWarnings("serial")
public class DiarioParteServiceImpl extends RemoteServiceServlet 
		implements DiarioParteService {

	/**
	 * 
	 */
	public DiarioParteServiceImpl() {
	}

	/* (non-Javadoc)
	 * @see cbmarc.inventory.client.mvp.diarioparte.DiarioParteService#delete(java.lang.Long)
	 */
	@Override
	public Boolean delete(Long id) throws Exception {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try {
			pm.currentTransaction().begin();
			DiarioParte diarioparte = pm.getObjectById(DiarioParte.class, id);
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

	/* (non-Javadoc)
	 * @see cbmarc.inventory.client.mvp.diarioparte.DiarioParteService#delete(java.util.ArrayList)
	 */
	@Override
	public List<DiarioParte> delete(ArrayList<Long> ids) {
		for (int i = 0; i < ids.size(); ++i) {
			try {
				delete(ids.get(i));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return select(null);
	}

	/* (non-Javadoc)
	 * @see cbmarc.inventory.client.mvp.diarioparte.DiarioParteService#select(java.lang.Long)
	 */
	@Override
	public DiarioParte selectById(Long id) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String query = "select UNIQUE from " + DiarioParte.class.getName()
			+ " where id == :registerId";
		Query q = pm.newQuery(query);
		
		DiarioParte result = (DiarioParte) q.execute(id);

		return result;
	}

	/* (non-Javadoc)
	 * @see cbmarc.inventory.client.mvp.diarioparte.DiarioParteService#select()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DiarioParte> select(String filter) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<DiarioParte> result;
		
		try {
			Query query = pm.newQuery(DiarioParte.class);
			
			query.setFilter(filter);
			query.setOrdering("date desc");
			//query.setRange(first, first + count);
			
			result = (List<DiarioParte>) query.execute();
			result = Lists.newArrayList(pm.detachCopyAll(result));
		} finally {
			pm.close();
		}
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see cbmarc.inventory.client.mvp.diarioparte.DiarioParteService#save(cbmarc.inventory.shared.entity.DiarioParte)
	 */
	@Override
	public DiarioParte save(DiarioParte diarioparte) throws Exception {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		// Is a insert statement?
		if(diarioparte.getId() == null) {
			Query query = pm.newQuery(DiarioParte.class);
			query.setResult("count(this)");
			Integer count = (Integer)query.execute();
			
			// Register limit
			if(count > 25) 
				throw new Exception("Limit exceeded.");
			
			diarioparte.setDate(new Date());
		}

		try {				
			pm.currentTransaction().begin();
			pm.makePersistent(diarioparte);
			pm.currentTransaction().commit();
		} catch(Exception e) {
			pm.currentTransaction().rollback();
			throw new Exception(e);
		} finally {
			pm.close();
		}
		
		return diarioparte;
	}

	/* (non-Javadoc)
	 * @see cbmarc.inventory.client.mvp.diarioparte.DiarioParteService#count()
	 */
	@Override
	public Integer count() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		final Query query = pm.newQuery(DiarioParte.class);
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
