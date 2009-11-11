/**
 * 
 */
package cbmarc.inventory.client.ui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * @author mcosta
 *
 */
public class ArticlePanel extends Composite {
	private final VerticalPanel container;
	
	private final TextBox descriptionTextBox;
	
	private final Button submitButton;
	
	public ArticlePanel() {
		container = new VerticalPanel();
		initWidget(container);
		
		descriptionTextBox = new TextBox();
		container.add(descriptionTextBox);
		
		submitButton = new Button("Sumbit");
		container.add(submitButton);
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.Widget#onLoad()
	 */
	@Override
	protected void onLoad() {
		super.onLoad();
		descriptionTextBox.setFocus(true);
	}
}
