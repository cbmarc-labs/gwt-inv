/**
 * 
 */
package cbmarc.inventory.client.mvp.parte;

import java.util.ArrayList;
import java.util.List;

import cbmarc.inventory.shared.entity.Parte;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author MCOSTA
 *
 */
public interface ParteServiceAsync {
	public void delete(Long id, AsyncCallback<Boolean> callback);
	public void save(Parte parte, AsyncCallback<Parte> callback);
	
	public void count(AsyncCallback<Integer> callback);
	
	public void delete(ArrayList<Long> ids, AsyncCallback<List<Parte>> callback);
	public void select(AsyncCallback<List<Parte>> callback);
	public void select(Long id, AsyncCallback<Parte> callback);
}
