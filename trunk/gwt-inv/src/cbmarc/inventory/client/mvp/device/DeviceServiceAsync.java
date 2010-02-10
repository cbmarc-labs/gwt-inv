/**
 * 
 */
package cbmarc.inventory.client.mvp.device;

import java.util.ArrayList;
import java.util.List;

import cbmarc.inventory.shared.entity.Device;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author MCOSTA
 *
 */
public interface DeviceServiceAsync {
	public void delete(String key, AsyncCallback<Boolean> callback);
	public void save(Device device, AsyncCallback<Device> callback);
	
	public void count(AsyncCallback<Integer> callback);
	
	public void delete(ArrayList<String> keys, AsyncCallback<Void> callback);
	public void select(String filter, AsyncCallback<List<Device>> callback);
	public void selectByKey(String key, AsyncCallback<Device> callback);
}
