/**
 * 
 */
package cbmarc.inventory.client.mvp.diario;

import java.util.ArrayList;
import java.util.List;

import cbmarc.inventory.shared.entity.Diario;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author MCOSTA
 *
 */
public interface DiarioServiceAsync {
	public void delete(Long id, AsyncCallback<Boolean> callback);
	public void save(Diario diarioparte, AsyncCallback<Diario> callback);
	
	public void count(AsyncCallback<Integer> callback);
	
	public void delete(ArrayList<Long> ids, AsyncCallback<Void> callback);
	public void select(String filter, AsyncCallback<List<Diario>> callback);
	public void selectById(Long id, AsyncCallback<Diario> callback);
}
