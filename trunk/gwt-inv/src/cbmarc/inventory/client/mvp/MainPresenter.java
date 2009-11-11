/**
 * 
 */
package cbmarc.inventory.client.mvp;

import java.util.List;

import net.customware.gwt.dispatch.client.DispatchAsync;
import net.customware.gwt.presenter.client.DisplayCallback;
import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.place.Place;

import cbmarc.framework.client.mvp.AbstractPresenter;
import cbmarc.framework.shared.event.LoadingEvent;
import cbmarc.framework.shared.rpc.GenericResult;
import cbmarc.inventory.client.i18n.AppLocale;
import cbmarc.inventory.shared.entity.Article;
import cbmarc.inventory.shared.event.SendArticleEvent;
import cbmarc.inventory.shared.event.SendArticleEventHandler;
import cbmarc.inventory.shared.rpc.FetchArticles;
import cbmarc.inventory.shared.rpc.FetchArticlesResult;
import cbmarc.inventory.shared.rpc.SendArticle;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasValue;
import com.google.inject.Inject;

/**
 * @author MCOSTA
 *
 */
public class MainPresenter extends AbstractPresenter<MainPresenter.Display> {
	public interface Display extends AbstractPresenter.Display {
		public void setListArticles(String listArticles);
		public void setWestLabel(String westLabel);
		public HasValue<String> getNameTextBox();
		public HasClickHandlers getSubmitButton();
	}

	public static final Place PLACE = new Place("Main");
	
	private final DispatchAsync dispatcher;

	@Inject
	public MainPresenter(final Display display, final EventBus eventBus
			, final DispatchAsync dispatcher) {
		super(display, eventBus);
		this.dispatcher = dispatcher;
		bind();
	}

	@Override
	public Place getPlace() {
		return PLACE;
	}

	@Override
	protected void onBind() {
		doFetchAllArticles();
		
		registerHandler(eventBus.addHandler(SendArticleEvent.TYPE,
				new SendArticleEventHandler() {

					@Override
					public void onSendArticle(SendArticleEvent event) {
						display.setWestLabel("new value entered");
						doFetchAllArticles();
					}
			
		}));
		
		display.getSubmitButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if(display.getNameTextBox().getValue().length() > 25)
					Window.alert(AppLocale.constants().err_toolong());
				else
					doSend();
			}
			
		});
	}
	
	private void doFetchAllArticles() {
		dispatcher.execute(
				new FetchArticles(), 
				new DisplayCallback<FetchArticlesResult>(display) {

					@Override
					protected void handleFailure(Throwable e) {
						Window.alert("FAILURE: " + e.getMessage());
					}

					@Override
					protected void handleSuccess(FetchArticlesResult value) {
						List<Article> results = value.getArticles();
						StringBuilder sb = new StringBuilder();

						for (Article item : results) {
							sb.append(item.toString() + "<br>");
						}
						
						display.setListArticles(sb.toString());
					}
					
				});
	}
	
	private void doSend() {
		eventBus.fireEvent(new LoadingEvent(false));
		dispatcher.execute(
				new SendArticle(display.getNameTextBox().getValue()),
				new DisplayCallback<GenericResult>(display) {

					@Override
					protected void handleFailure(Throwable e) {
						eventBus.fireEvent(new LoadingEvent(true));
						Window.alert("FAILURE: " + e.getCause());
					}

					@Override
					protected void handleSuccess(GenericResult value) {
						eventBus.fireEvent(new LoadingEvent(true));
						eventBus.fireEvent(new SendArticleEvent());
					}
					
				});
	}
}
