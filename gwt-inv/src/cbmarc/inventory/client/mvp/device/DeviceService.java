/**
 * 
 */
package cbmarc.inventory.client.mvp.device;

import java.util.ArrayList;
import java.util.List;

import cbmarc.inventory.shared.entity.Device;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author MCOSTA
 *
 */
@RemoteServiceRelativePath("deviceService")
public interface DeviceService extends RemoteService {
	Boolean delete(Long id) throws Exception;
	Device save(Device parte) throws Exception;
	
	void delete(ArrayList<Long> ids);
	List<Device> select(String filter);
	
	Integer count();
	Device selectById(Long id);
}
