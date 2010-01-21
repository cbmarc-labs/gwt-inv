/**
 * 
 */
package cbmarc.inventory.client.mvp.diary;


import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class DiaryView extends Composite 
		implements DiaryPresenter.Display {

	interface uiBinder extends UiBinder<Widget, DiaryView> {}
	private static uiBinder uiBinder = GWT.create(uiBinder.class);
	
	public DiaryView() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public Widget asWidget() {
		  return this;
	}
}
