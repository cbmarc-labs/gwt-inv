/**
 * 
 */
package cbmarc.inventory.client.mvp;

import cbmarc.framework.client.mvp.AbstractView;
import cbmarc.inventory.client.i18n.AppLocale;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class MainView extends AbstractView implements MainPresenter.Display  {
	private final DockPanel outer;
	private HTML listArticles;
	private Label westLabel;
	private TextBox nameTextBox;
	private Button submitButton;

	public MainView() {
		VerticalPanel eastPanel = new VerticalPanel();
		VerticalPanel westPanel = new VerticalPanel();

		outer = new DockPanel();
		initWidget(outer);

		outer.setBorderWidth(1);
		outer.setStyleName("main");

		westLabel = new Label("no name");
		listArticles = new HTML();
		westPanel.add(new HTML("Main West"));
		westPanel.add(listArticles);
		westPanel.add(westLabel);

		eastPanel.add(new HTML(AppLocale.constants().simple_rpc()));
		eastPanel.add(createForm());

		outer.add(westPanel, DockPanel.WEST);
		outer.add(eastPanel, DockPanel.EAST);
	}
	
	private Widget createForm() {
		HorizontalPanel layout = new HorizontalPanel();
		
		layout.setSpacing(5);

		nameTextBox = new TextBox();
        submitButton = new Button(AppLocale.constants().submit());
        
		layout.add(new HTML(AppLocale.constants().name() + " :"));
		layout.add(nameTextBox);
		layout.add(submitButton);
		
		return layout;
	}

	@Override
	public HasValue<String> getNameTextBox() {
		return nameTextBox;
	}

	@Override
	public HasClickHandlers getSubmitButton() {
		return submitButton;
	}

	@Override
	public void setWestLabel(String westLabel) {
		this.westLabel.setText(westLabel);
	}

	@Override
	public void setListArticles(String listArticles) {
		this.listArticles.setHTML(listArticles);
	}
}
