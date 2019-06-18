package _new.interprozesskommunikation;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class RMI_client {

	public static void main(String[] args) {
		try {
			// lookup()리턴타입: Remote interface 
			RMI_interface rmi_interface = (RMI_interface) Naming.lookup("rmi://127.0.0.1:1200/Hello");
			System.out.println(rmi_interface.hello("Welcome"));
			
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
