package common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

/**
 * Define a interface remota do servidor
 */
public interface ServerInterface extends Remote {

  /** nome para referenciar remotamente o servidor */
  String REFERENCE_NAME = "Server";

  int RMI_PORT = 1099;

  /**
   * Obtém todos os usuários do sistema.
   *
   * @return lista com os usuários
   * @throws RemoteException .
   */
  List<User> getUsers() throws RemoteException, SQLException;

  void createUser(User user) throws RemoteException, SQLException;

  User findUsersById(Long id) throws RemoteException, SQLException;

  Boolean isValidLogin(String login) throws RemoteException, SQLException;

  Long getNumberOfUsers() throws RemoteException, SQLException;

}
