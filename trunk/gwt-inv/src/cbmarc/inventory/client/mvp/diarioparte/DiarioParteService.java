/**
 * 
 */
package cbmarc.inventory.client.mvp.diarioparte;

import java.util.ArrayList;
import java.util.List;

import cbmarc.inventory.shared.entity.DiarioParte;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author MCOSTA
 *
 */
@RemoteServiceRelativePath("diarioParteService")
public interface DiarioParteService extends RemoteService {
	Boolean delete(Long id) throws Exception;
	DiarioParte save(DiarioParte parte) throws Exception;
	
	List<DiarioParte> delete(ArrayList<Long> ids);
	List<DiarioParte> select(String filter);
	
	Integer count();
	DiarioParte selectById(Long id);
}
