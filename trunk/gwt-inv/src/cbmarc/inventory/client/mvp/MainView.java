/**
 * 
 */
package cbmarc.inventory.client.mvp;


import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class MainView extends Composite implements MainPresenter.Display {
	interface uiBinder extends UiBinder<Widget, MainView> {}
	private static uiBinder uiBinder = GWT.create(uiBinder.class);
	
	@UiField Tree navigation;
	@UiField Panel content;
	
	public MainView() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public Widget asWidget() {
		  return this;
	}

	@Override
	public HasWidgets getContent() {
		return content;
	}

	@Override
	public Tree getNavigation() {
		return navigation;
	}
}
