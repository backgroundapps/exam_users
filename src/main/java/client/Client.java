package client;

import client.views.user.UserFrame;
import common.ServerInterface;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

  private static ServerInterface server;

  public static ServerInterface getServer() throws RemoteException, NotBoundException {
    if (Client.server == null) {
      Registry registry = LocateRegistry.getRegistry("localhost", ServerInterface.RMI_PORT);
      Client.server = (ServerInterface) registry.lookup(ServerInterface.REFERENCE_NAME);
    }
    return Client.server;
  }

  public static void main(String[] args) {
    try {
      new UserFrame().setVisible(true);
    }
    catch (Exception e) {
      System.err.println("Client exception: " + e.toString());
      e.printStackTrace();
    }
  }
}
