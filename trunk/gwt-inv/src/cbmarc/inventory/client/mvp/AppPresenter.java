/**
 * 
 */
package cbmarc.inventory.client.mvp;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.inject.Inject;

/**
 * @author MCOSTA
 *
 */
public class AppPresenter {
	private HasWidgets container;
	
	private TopPresenter topPresenter;
	private MainPresenter mainPresenter;
	private BottomPresenter bottomPresenter;
	
	@Inject
	public AppPresenter(final DispatchAsync dispatcher
			,TopPresenter topPresenter, MainPresenter mainPresenter
			,BottomPresenter bottomPresenter) {
		this.topPresenter = topPresenter;
		this.mainPresenter = mainPresenter;
		this.bottomPresenter = bottomPresenter;
	}
	
	private void showMain() {
		container.clear();
		
		container.add(topPresenter.getDisplay().asWidget());
		container.add(mainPresenter.getDisplay().asWidget());
		container.add(bottomPresenter.getDisplay().asWidget());
	}
		
	public void go(final HasWidgets container) {
		this.container = container;
		
		showMain();
	}
}
