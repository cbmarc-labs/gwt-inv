/**
 * 
 */
package cbmarc.inventory.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import cbmarc.inventory.client.mvp.diario.DiarioService;
import cbmarc.inventory.shared.entity.Diario;
import cbmarc.inventory.shared.exception.ServerException;

import com.google.appengine.repackaged.com.google.common.collect.Lists;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author MCOSTA
 *
 */
@SuppressWarnings("serial")
public class DiarioServiceImpl extends RemoteServiceServlet 
		implements DiarioService {

	/**
	 * 
	 */
	public DiarioServiceImpl() {
	}

	/* (non-Javadoc)
	 * @see cbmarc.inventory.client.mvp.diarioparte.DiarioParteService#delete(java.lang.Long)
	 */
	@Override
	public Boolean delete(Long id) throws ServerException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try {
			pm.currentTransaction().begin();
			Diario diario = pm.getObjectById(Diario.class, id);
			pm.deletePersistent(diario);
			
			pm.currentTransaction().commit();
		} catch(Exception e) {
			pm.currentTransaction().rollback();
			throw new ServerException(e.toString());
		} finally {
			pm.close();
		}
		
		return true;
	}

	/* (non-Javadoc)
	 * @see cbmarc.inventory.client.mvp.diario.DiarioParteService#delete(java.util.ArrayList)
	 */
	@Override
	public void delete(ArrayList<Long> ids) {
		for (int i = 0; i < ids.size(); ++i) {
			try {
				delete(ids.get(i));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/* (non-Javadoc)
	 * @see cbmarc.inventory.client.mvp.diarioparte.DiarioParteService#select(java.lang.Long)
	 */
	@Override
	public Diario selectById(Long id) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String query = "select UNIQUE from " + Diario.class.getName()
			+ " where id == :registerId";
		Query q = pm.newQuery(query);
		
		Diario result = (Diario) q.execute(id);

		return result;
	}

	/* (non-Javadoc)
	 * @see cbmarc.inventory.client.mvp.diarioparte.DiarioParteService#select()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Diario> select(String filter) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<Diario> result;
		
		try {
			Query query = pm.newQuery(Diario.class);
			
			query.setFilter(filter);
			query.setOrdering("date desc");
			//query.setRange(first, first + count);
			
			result = (List<Diario>) query.execute();
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
	public Diario save(Diario bean) throws ServerException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		// Is a insert statement?
		if(bean.getId() == null) {
			Query query = pm.newQuery(Diario.class);
			query.setResult("count(this)");
			Integer count = (Integer)query.execute();
			
			// Register limit
			if(count > 25) 
				throw new ServerException("Limit exceeded.");
			
			bean.setDate(new Date());
		}

		try {				
			pm.currentTransaction().begin();
			pm.makePersistent(bean);
			pm.currentTransaction().commit();
		} catch(Exception e) {
			pm.currentTransaction().rollback();
			throw new ServerException(e.toString());
		} finally {
			pm.close();
		}
		
		return bean;
	}

	/* (non-Javadoc)
	 * @see cbmarc.inventory.client.mvp.diarioparte.DiarioParteService#count()
	 */
	@Override
	public Integer count() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		final Query query = pm.newQuery(Diario.class);
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
