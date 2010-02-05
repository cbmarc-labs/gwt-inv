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
	public void delete(Long id, AsyncCallback<Boolean> callback);
	public void save(Device device, AsyncCallback<Device> callback);
	
	public void count(AsyncCallback<Integer> callback);
	
	public void delete(ArrayList<Long> ids, AsyncCallback<Void> callback);
	public void select(String filter, AsyncCallback<List<Device>> callback);
	public void selectById(Long id, AsyncCallback<Device> callback);
}
