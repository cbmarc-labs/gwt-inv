/**
 * 
 */
package cbmarc.inventory.server;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.inject.internal.Lists;

import net.customware.gwt.dispatch.shared.ActionException;
import cbmarc.framework.server.PMF;
import cbmarc.inventory.shared.entity.Article;

/**
 * @author mcosta
 * 
 * SOURCE: http://www.ibm.com/developerworks/java/library/j-gaej3.html
 * SOURCE: http://stronglytypedblog.blogspot.com/2009/07/wicket-spring-jdo-on-google-app-engine.html
 *
 */
public class ArticleJdoDAO implements ArticleDAO {
	/*public Article get(final Long id) {
		final Person person = getPersistenceManager()
			.getObjectById(Person.class, id);
		return getPersistenceManager().detachCopy(person);
	}*/
	
	@SuppressWarnings("unchecked")
	@Override
	public void insert(Article article) throws ActionException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Article da = null;

		Query query = pm.newQuery(Article.class);
		query.setResult("count(this)");
		Integer count = (Integer)query.execute();
		
		if(count > 9) {
			query = pm.newQuery(Article.class);
			query.setOrdering("date asc");
			query.setRange(0, 1);

			List<Article> a = (List<Article>) query.execute();
			da = a.get(0);
		}

		try {
			pm.currentTransaction().begin();

			if(da != null) {
				pm.deletePersistent(da);
			}
			
			pm.makePersistent(article);
			pm.currentTransaction().commit();
		} catch(Exception cause) {
			pm.currentTransaction().rollback();
			throw new ActionException(cause);
		}  finally {
			pm.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Article> selectAll() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<Article> list;
		
		try {
			Query query = pm.newQuery(Article.class);
			query.setOrdering("date desc");
			//query.setRange(first, first + count);

			list = (List<Article>) query.execute();
			list = Lists.newArrayList(pm.detachCopyAll(list));
		} finally {
			pm.close();
		}
		
		return list; 
	}

	@Override
	public void delete(Article article) throws ActionException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try {
			pm.currentTransaction().begin();
			article = pm.getObjectById(Article.class, article.getId());
			pm.deletePersistent(article);
			
			pm.currentTransaction().commit();
		} catch(Exception cause) {
			pm.currentTransaction().rollback();
			throw new ActionException(cause);
		} finally {
			pm.close();
		}
	}

	@Override
	public void update(Article article) throws ActionException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String description = article.getDescription();

		try {
			pm.currentTransaction().begin();
			article = pm.getObjectById(Article.class, article.getId());
			article.setDescription(description);
			pm.makePersistent(article);
			pm.currentTransaction().commit();
		} catch(Exception cause) {
			pm.currentTransaction().rollback();
			throw new ActionException(cause);
		} finally {
			pm.close();
		}
	}

	@Override
	public int count() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		final Query query = pm.newQuery(Article.class);
		Integer res;

		query.setResult("count(this)");
		
		try {
			res = (Integer) query.execute();
		} finally {
			pm.close();
		}
		
		return res;
	}

	@Override
	public Article selectOne(Long id) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String query = "select UNIQUE from " + Article.class.getName()
			+ " where id == :articleId";
		Query q = pm.newQuery(query);
		
		Article article = (Article) q.execute(id);

		return article;
	}

}
