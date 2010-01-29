/**
 * 
 */
package cbmarc.inventory.client.mvp.diarioparte;

import java.util.ArrayList;
import java.util.List;

import cbmarc.inventory.shared.entity.DiarioParte;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author MCOSTA
 *
 */
public interface DiarioParteServiceAsync {
	public void delete(Long id, AsyncCallback<Boolean> callback);
	public void save(DiarioParte diarioparte, AsyncCallback<DiarioParte> callback);
	
	public void count(AsyncCallback<Integer> callback);
	
	public void delete(ArrayList<Long> ids, AsyncCallback<List<DiarioParte>> callback);
	public void select(AsyncCallback<List<DiarioParte>> callback);
	public void select(Long id, AsyncCallback<DiarioParte> callback);
}
