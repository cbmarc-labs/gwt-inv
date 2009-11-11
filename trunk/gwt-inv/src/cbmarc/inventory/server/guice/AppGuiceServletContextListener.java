package cbmarc.inventory.server.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

public class AppGuiceServletContextListener
		extends GuiceServletContextListener {
	@Override
	protected Injector getInjector() {
		return Guice.createInjector(
				new AppActionHandlerModule(), new AppServletModule());
	}
}