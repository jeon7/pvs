package _new.interprozesskommunikation;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMI_implementation extends UnicastRemoteObject implements RMI_interface {
	
	protected RMI_implementation() throws RemoteException {
		super();
	}
	
	@Override
	public String hello(String greeting) throws RemoteException {
		String output = "Gruss: " + greeting;
		System.out.println("greeting client");
		return output;
	}
}
