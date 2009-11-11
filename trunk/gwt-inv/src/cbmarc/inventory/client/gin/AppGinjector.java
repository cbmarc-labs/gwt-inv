package cbmarc.inventory.client.gin;

import net.customware.gwt.dispatch.client.gin.ClientDispatchModule;
import net.customware.gwt.presenter.client.place.PlaceManager;
import cbmarc.inventory.client.mvp.AppPresenter;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

@GinModules({ ClientDispatchModule.class, AppClientModule.class })
public interface AppGinjector extends Ginjector {
	AppPresenter getAppPresenter();
	PlaceManager getPlaceManager();
}