/**
 * 
 */
package cbmarc.inventory.client.mvp.diario;

import java.util.ArrayList;
import java.util.List;

import cbmarc.inventory.shared.entity.Diario;
import cbmarc.inventory.shared.exception.ServerException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author MCOSTA
 *
 */
@RemoteServiceRelativePath("diarioService")
public interface DiarioService extends RemoteService {
	Boolean delete(Long id) throws ServerException;
	Diario save(Diario parte) throws ServerException;
	
	void delete(ArrayList<Long> ids);
	List<Diario> select(String filter);
	
	Integer count();
	Diario selectById(Long id);
}
