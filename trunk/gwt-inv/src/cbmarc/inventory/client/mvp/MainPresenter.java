/**
 * 
 */
package cbmarc.inventory.client.mvp;

import java.util.HashMap;
import java.util.Map;


import cbmarc.inventory.client.mvp.contact.ContactPresenter;
import cbmarc.inventory.client.mvp.contact.ContactView;
import cbmarc.inventory.client.mvp.diary.DiaryPresenter;
import cbmarc.inventory.client.mvp.diary.DiaryView;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class MainPresenter implements Presenter {
	
	public interface Display {
		Tree getMainMenu();
		HasWidgets getContent();
		Widget asWidget();
	}
	
	private final HandlerManager eventBus;
	private final Display display;
	private Map<TreeItem, Presenter> itemPresenters = 
		new HashMap<TreeItem, Presenter>();
	
	public MainPresenter(HandlerManager eventBus, Display view) {
	    this.eventBus = eventBus;
	    this.display = view;
	    
	    bind();
	}
	
	public void bind() {
		display.getMainMenu().addSelectionHandler(
				new SelectionHandler<TreeItem>() {

			@Override
			public void onSelection(SelectionEvent<TreeItem> event) {
				TreeItem item = event.getSelectedItem();
				if (item == null) {
					item = display.getMainMenu().getItem(0).getChild(0);
				}

				Presenter presenter = itemPresenters.get(item);

				if(presenter != null)
					presenter.go(display.getContent());
			}
			
		});
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		setupMainMenu();
	    container.add(display.asWidget());
	}
	
	private void setupMainMenu() {
		Tree tree = display.getMainMenu();
		
		TreeItem catFirst = tree.addItem("First");
		setupMainMenuOption(catFirst, 
			new ContactPresenter(eventBus, new ContactView()), "Contact");
		setupMainMenuOption(catFirst, 
			new DiaryPresenter(eventBus, new DiaryView()), "Diary");
		
		display.getMainMenu().getItem(0).setState(true);
		display.getMainMenu().setSelectedItem(
			display.getMainMenu().getItem(0).getChild(0));
	}
	
	private void setupMainMenuOption(TreeItem parent, Presenter content,
			String title) {
		TreeItem option = parent.addItem(title);
		itemPresenters.put(option, content);
	}
}
