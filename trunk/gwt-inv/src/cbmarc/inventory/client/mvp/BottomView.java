/**
 * 
 */
package cbmarc.inventory.client.mvp;

import cbmarc.framework.client.mvp.AbstractView;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;

/**
 * @author MCOSTA
 *
 */
public class BottomView extends AbstractView
		implements BottomPresenter.Display  {
	private final HorizontalPanel outer;
	
	public BottomView() {
		outer = new HorizontalPanel();
		initWidget(outer);

		outer.setBorderWidth(1);
		outer.setStyleName("bottom");
		outer.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
		outer.add(new HTML("Bottom"));
	}
}
