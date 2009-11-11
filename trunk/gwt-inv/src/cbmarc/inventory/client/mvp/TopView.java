/**
 * 
 */
package cbmarc.inventory.client.mvp;

import cbmarc.framework.client.mvp.AbstractView;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * @author MCOSTA
 *
 */
public class TopView extends AbstractView implements TopPresenter.Display  {
	private final HorizontalPanel outer;
	private final VerticalPanel inner;
	private final Hyperlink topHyperlink;
	
	public TopView() {
		outer = new HorizontalPanel();
		initWidget(outer);

		inner = new VerticalPanel();

		topHyperlink = new Hyperlink("Top", "Top");
		inner.add(topHyperlink);

		outer.setBorderWidth(1);
		outer.setStyleName("top");
		outer.setHorizontalAlignment(HorizontalPanel.ALIGN_RIGHT);
		outer.add(inner);
	}
	
	/**
	 * @return the loginHyperlink
	 */
	public HasClickHandlers getTopHyperlink() {
		return topHyperlink;
	}

}
