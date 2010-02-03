/**
 * 
 */
package cbmarc.inventory.client.mvp;

import java.util.HashMap;
import java.util.Map;

import cbmarc.inventory.client.mvp.contact.ContactPresenter;
import cbmarc.inventory.client.mvp.contact.ContactView;
import cbmarc.inventory.client.mvp.departamento.DepartamentoPresenter;
import cbmarc.inventory.client.mvp.departamento.DepartamentoView;
import cbmarc.inventory.client.mvp.diario.DiarioPresenter;
import cbmarc.inventory.client.mvp.diario.DiarioView;
import cbmarc.inventory.client.mvp.parte.PartePresenter;
import cbmarc.inventory.client.mvp.parte.ParteView;

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
		Tree getNavigation();
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
		display.getNavigation().addSelectionHandler(
				new SelectionHandler<TreeItem>() {

			@Override
			public void onSelection(SelectionEvent<TreeItem> event) {
				TreeItem item = event.getSelectedItem();
				if (item == null) {
					item = display.getNavigation().getItem(0).getChild(0);
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
	
	/**
	 * 
	 */
	private void setupMainMenu() {
		Tree tree = display.getNavigation();
		
		TreeItem catFirst = tree.addItem("MENU");
		
		setupMainMenuOption(catFirst, new ContactPresenter(eventBus,
				new ContactView()),	"Contact");
		setupMainMenuOption(catFirst, new PartePresenter(eventBus, 
				new ParteView()), "Partes");
		setupMainMenuOption(catFirst, new DiarioPresenter(eventBus, 
				new DiarioView()), "Diario Partes");
		setupMainMenuOption(catFirst, new DepartamentoPresenter(eventBus,
				new DepartamentoView()), "Departamento");
		
		display.getNavigation().getItem(0).setState(true);
		display.getNavigation().setSelectedItem(
			display.getNavigation().getItem(0).getChild(0));
	}
	
	/**
	 * @param parent
	 * @param content
	 * @param title
	 */
	private void setupMainMenuOption(TreeItem parent, Presenter content,
			String title) {
		TreeItem option = parent.addItem(title);
		itemPresenters.put(option, content);
	}
}
