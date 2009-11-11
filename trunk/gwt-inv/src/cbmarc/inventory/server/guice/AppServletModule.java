package cbmarc.inventory.server.guice;

import net.customware.gwt.dispatch.server.service.DispatchServiceServlet;

import com.google.inject.servlet.ServletModule;

public class AppServletModule extends ServletModule {
	@Override
	protected void configureServlets() {
		serve("/inventory/dispatch").with(DispatchServiceServlet.class);
	}
}