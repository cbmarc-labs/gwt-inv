package cbmarc.inventory.client;

import cbmarc.inventory.client.mvp.LoadingPresenter;
import cbmarc.inventory.client.mvp.LoadingView;
import cbmarc.inventory.client.mvp.MainPresenter;
import cbmarc.inventory.client.mvp.MainView;
import cbmarc.inventory.client.mvp.Presenter;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;

public class AppController implements Presenter {
	private final HandlerManager eventBus;
	
	@SuppressWarnings("unused")
	private final LoadingPresenter loading;
	private final MainPresenter main;
  
	public AppController(HandlerManager eventBus) {
		this.eventBus = eventBus;
		this.main = new MainPresenter(this.eventBus, new MainView());
		this.loading = new LoadingPresenter(this.eventBus, new LoadingView());
		
		bind();
	}
	
	private void bind() {
	}
	
	public void go(final HasWidgets container) {
		container.clear();
		main.go(container);
	}
}
