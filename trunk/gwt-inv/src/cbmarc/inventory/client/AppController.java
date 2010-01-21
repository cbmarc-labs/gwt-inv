package cbmarc.inventory.client;

import cbmarc.inventory.client.mvp.MainPresenter;
import cbmarc.inventory.client.mvp.MainView;
import cbmarc.inventory.client.mvp.Presenter;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;

public class AppController implements Presenter {
  private final HandlerManager eventBus;
  
  public AppController(HandlerManager eventBus) {
    this.eventBus = eventBus;
    bind();
  }
  
  private void bind() {
  }
  
  public void go(final HasWidgets container) {
	Presenter presenter = new MainPresenter(eventBus, new MainView());
    presenter.go(container);
  }
}
