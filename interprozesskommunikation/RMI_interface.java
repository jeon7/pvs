package _new.interprozesskommunikation;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMI_interface extends Remote {
	public String hello(String greeting) throws RemoteException; // 추상메소드 

}
