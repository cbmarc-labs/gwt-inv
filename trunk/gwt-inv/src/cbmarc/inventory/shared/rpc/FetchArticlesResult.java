/**
 * 
 */
package cbmarc.inventory.shared.rpc;

import java.util.List;

import cbmarc.inventory.shared.entity.Article;


import net.customware.gwt.dispatch.shared.Result;

/**
 * @author mcosta
 *
 */
public class FetchArticlesResult implements Result {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Article> articles;

	@SuppressWarnings("unused")
	private FetchArticlesResult() {
	}
	
	public FetchArticlesResult(List<Article> articles) {
		this.articles = articles;
	}

	/**
	 * @return the articles
	 */
	public List<Article> getArticles() {
		return articles;
	}
}
