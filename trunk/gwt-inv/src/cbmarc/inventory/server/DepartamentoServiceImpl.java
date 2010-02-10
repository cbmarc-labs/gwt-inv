/**
 * 
 */
package cbmarc.inventory.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import cbmarc.inventory.client.mvp.departamento.DepartamentoService;
import cbmarc.inventory.shared.entity.Departamento;
import cbmarc.inventory.shared.exception.ServerException;

import com.google.appengine.repackaged.com.google.common.collect.Lists;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author MCOSTA
 *
 */
@SuppressWarnings("serial")
public class DepartamentoServiceImpl extends RemoteServiceServlet 
		implements DepartamentoService {

	/**
	 * 
	 */
	public DepartamentoServiceImpl() {
	}

	@Override
	public Boolean delete(String key) throws ServerException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try {
			pm.currentTransaction().begin();
			Departamento Departamento = 
				pm.getObjectById(Departamento.class, key);
			pm.deletePersistent(Departamento);
			
			pm.currentTransaction().commit();
		} catch(Exception e) {
			pm.currentTransaction().rollback();
			throw new ServerException(e.toString());
		} finally {
			pm.close();
		}
		
		return true;
	}

	@Override
	public void delete(ArrayList<String> keys) {
		for (int i = 0; i < keys.size(); ++i) {
			try {
				delete(keys.get(i));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Departamento selectByKey(String key) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Departamento result = pm.getObjectById(Departamento.class, key);

		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Departamento> select(String filter) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<Departamento> result;
		
		try {
			Query query = pm.newQuery(Departamento.class);
			
			query.setFilter(filter);
			query.setOrdering("nombre");
			//query.setRange(first, first + count);
			
			result = (List<Departamento>) query.execute();
			result = Lists.newArrayList(pm.detachCopyAll(result));
		} finally {
			pm.close();
		}
		
		return result;
	}
	
	@Override
	public Departamento save(Departamento bean) throws ServerException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		// Is a insert statement?
		if(bean.getEncodedKey() == null) {
			Query query = pm.newQuery(Departamento.class);
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

	@Override
	public Integer count() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		final Query query = pm.newQuery(Departamento.class);
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
