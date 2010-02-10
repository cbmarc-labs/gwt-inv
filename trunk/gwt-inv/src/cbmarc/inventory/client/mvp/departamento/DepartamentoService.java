/**
 * 
 */
package cbmarc.inventory.client.mvp.departamento;

import java.util.ArrayList;
import java.util.List;

import cbmarc.inventory.shared.entity.Departamento;
import cbmarc.inventory.shared.exception.ServerException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author MCOSTA
 *
 */
@RemoteServiceRelativePath("departamentoService")
public interface DepartamentoService extends RemoteService {
	Boolean delete(String key) throws ServerException;
	Departamento save(Departamento parte) throws ServerException;
	
	void delete(ArrayList<String> keys);
	List<Departamento> select(String filter);
	
	Integer count();
	Departamento selectByKey(String key);
}
