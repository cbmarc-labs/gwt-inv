/**
 * 
 */
package cbmarc.inventory.client.mvp.diario;

import java.util.ArrayList;
import java.util.List;

import cbmarc.inventory.shared.entity.Diario;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author MCOSTA
 *
 */
@RemoteServiceRelativePath("diarioParteService")
public interface DiarioService extends RemoteService {
	Boolean delete(Long id) throws Exception;
	Diario save(Diario parte) throws Exception;
	
	void delete(ArrayList<Long> ids);
	List<Diario> select(String filter);
	
	Integer count();
	Diario selectById(Long id);
}
