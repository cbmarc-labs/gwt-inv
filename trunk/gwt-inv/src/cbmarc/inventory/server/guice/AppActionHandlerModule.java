package cbmarc.inventory.server.guice;

import cbmarc.inventory.server.handler.FetchArticlesHandler;
import cbmarc.inventory.server.handler.SendArticleHandler;
import net.customware.gwt.dispatch.server.guice.ActionHandlerModule;

/**
 * Module which binds the handlers and configurations
 *
 */
public class AppActionHandlerModule extends ActionHandlerModule {
	@Override
	protected void configureHandlers() {
		bindHandler(SendArticleHandler.class);
		bindHandler(FetchArticlesHandler.class);
	}
}