package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.List;

import common.*;
import server.process.UserProcess;

public class Server implements ServerInterface {

    public static void main(String args[]) {
        try {
            ServerInterface server = new Server();
            UnicastRemoteObject.exportObject(server, ServerInterface.RMI_PORT);

            Registry registry = LocateRegistry.createRegistry(ServerInterface.RMI_PORT);
            registry.rebind(ServerInterface.REFERENCE_NAME, server);

            System.out.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }

    /*



    */
    @Override
    public List<User> getUsers() throws RemoteException, SQLException {
        try {
            return new UserProcess().getUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void createUser(User user) throws RemoteException, SQLException {
        try {
            new UserProcess().create(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public User findUsersById(Long id) throws RemoteException, SQLException {
        try {
            return new UserProcess().findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public Boolean isValidLogin(String login) throws RemoteException, SQLException {
        try {
            return new UserProcess().isValidLogin(login);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public Long getNumberOfUsers() throws RemoteException, SQLException {
        return new UserProcess().getNumberOfUsers();
    }
}