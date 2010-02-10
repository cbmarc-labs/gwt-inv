/**
 * 
 */
package cbmarc.inventory.client.mvp.departamento;

import java.util.ArrayList;
import java.util.List;

import cbmarc.inventory.shared.entity.Departamento;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author MCOSTA
 *
 */
public interface DepartamentoServiceAsync {
	public void delete(String key, AsyncCallback<Boolean> callback);
	public void save(Departamento departamento, AsyncCallback<Departamento> callback);
	
	public void count(AsyncCallback<Integer> callback);
	
	public void delete(ArrayList<String> keys, AsyncCallback<Void> callback);
	public void select(String filter, AsyncCallback<List<Departamento>> callback);
	public void selectByKey(String key, AsyncCallback<Departamento> callback);
}
