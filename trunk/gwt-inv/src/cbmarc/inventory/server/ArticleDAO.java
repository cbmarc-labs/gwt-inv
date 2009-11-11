/**
 * 
 */
package cbmarc.inventory.server;

import java.util.List;

import net.customware.gwt.dispatch.shared.ActionException;

import cbmarc.inventory.shared.entity.Article;

/**
 * @author mcosta
 *
 */
public interface ArticleDAO {
	void insert(Article article) throws ActionException;
	void delete(Article article) throws ActionException;
	void update(Article article) throws ActionException;
	
	int count();
	Article selectOne(Long id);
	List<Article> selectAll();
}
