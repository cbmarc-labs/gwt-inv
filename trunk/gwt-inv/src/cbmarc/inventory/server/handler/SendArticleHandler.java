/**
 * 
 */
package cbmarc.inventory.server.handler;

import java.util.Date;

import cbmarc.framework.shared.rpc.GenericResult;
import cbmarc.inventory.server.ArticleDAO;
import cbmarc.inventory.server.ArticleJdoDAO;
import cbmarc.inventory.shared.entity.Article;
import cbmarc.inventory.shared.rpc.SendArticle;

import com.google.inject.Inject;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.ActionException;

/**
 * @author MCOSTA
 *
 */
public class SendArticleHandler implements 
		ActionHandler<SendArticle, GenericResult> {
	// private ArticleDAO articleDAO = new ArticleMockDAO();
	private ArticleDAO articleDAO = new ArticleJdoDAO();
	
	@Inject
	public SendArticleHandler() {
	}

	@Override
	public GenericResult execute(final SendArticle action, 
			final ExecutionContext context) throws ActionException {

		try {
			Article article = new Article(action.getArticle());
			article.setDate(new Date());
			articleDAO.insert(article);
			
			return new GenericResult(action.getArticle(), true);
		} catch(Exception cause) {
			throw new ActionException(cause);
		}
	}

	@Override
	public Class<SendArticle> getActionType() {
		return SendArticle.class;
	}

	@Override
	public void rollback(SendArticle action, GenericResult result,
			ExecutionContext context) throws ActionException {
		// TODO Auto-generated method stub
		
	}

}
