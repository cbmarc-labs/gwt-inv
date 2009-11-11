package cbmarc.inventory.client.gin;

import cbmarc.framework.client.mvp.LoadingPresenter;
import cbmarc.framework.client.mvp.LoadingView;
import cbmarc.inventory.client.mvp.AppPresenter;
import cbmarc.inventory.client.mvp.BottomPresenter;
import cbmarc.inventory.client.mvp.BottomView;
import cbmarc.inventory.client.mvp.MainPresenter;
import cbmarc.inventory.client.mvp.MainView;
import cbmarc.inventory.client.mvp.TopPresenter;
import cbmarc.inventory.client.mvp.TopView;

import com.google.inject.Singleton;

import net.customware.gwt.presenter.client.DefaultEventBus;
import net.customware.gwt.presenter.client.Display;
import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.Presenter;
import net.customware.gwt.presenter.client.gin.AbstractPresenterModule;
import net.customware.gwt.presenter.client.place.PlaceManager;

public class AppClientModule extends AbstractPresenterModule {

	@Override
	protected void configure() {		
		bind(EventBus.class).to(DefaultEventBus.class).in(Singleton.class);
		bind(PlaceManager.class).in(Singleton.class);
		
		bindEagerPresenter(LoadingPresenter.class, LoadingPresenter.Display.class, LoadingView.class);
		
		bindPresenter(TopPresenter.class, TopPresenter.Display.class, TopView.class);
		bindPresenter(MainPresenter.class, MainPresenter.Display.class, MainView.class);
		bindPresenter(BottomPresenter.class, BottomPresenter.Display.class, BottomView.class);
		
		bind(AppPresenter.class).in(Singleton.class);
	}
	
	protected <D extends Display> void bindEagerPresenter(
			final Class<? extends Presenter> presenter,
			final Class<D> display,
			final Class<? extends D> displayImpl ) {
		bind(presenter).asEagerSingleton();
		bindDisplay(display, displayImpl);
	}
}