/**
 * 
 */
package cbmarc.inventory.client.mvp;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class LoadingView extends PopupPanel	
		implements LoadingPresenter.Display {

	private final FlowPanel outer = new FlowPanel();

	public LoadingView() {
		final Grid grid = new Grid(1, 2);
		final Image ajaxImage = new Image(
				GWT.getModuleName() + "/img/ajax-loader.gif");

		grid.setWidget(0, 0, ajaxImage);
		grid.setText(0, 1, "LOADING ...");

		outer.add(grid);
		add(outer);
	}

	public Widget asWidget() {
		return this;
	}

	@Override
	public void startProcessing() {
		center();
		show();
	}

	@Override
	public void stopProcessing() {
		hide();
	}

	@Override
	public void showWidget() {
		startProcessing();
	}

}
