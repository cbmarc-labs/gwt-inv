/**
 * 
 */
package cbmarc.inventory.client.mvp.parte;

import java.util.ArrayList;
import java.util.List;

import cbmarc.inventory.shared.entity.Parte;
import cbmarc.inventory.shared.exception.ServerException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author MCOSTA
 *
 */
@RemoteServiceRelativePath("parteService")
public interface ParteService extends RemoteService {
	Boolean delete(Long id) throws ServerException;
	Parte save(Parte parte) throws ServerException;
	
	List<Parte> delete(ArrayList<Long> ids);
	List<Parte> select();
	
	Integer count();
	Parte select(Long id);
}
