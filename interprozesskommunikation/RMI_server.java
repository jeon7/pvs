package _new.interprozesskommunikation;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMI_server {
	public static void main(String[] args) {
		int port = 1200;
		
		try {
			Registry registry = LocateRegistry.createRegistry(port);
			registry.rebind("Hello", new RMI_implementation());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
