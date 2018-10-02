import java.rmi.*;

public interface Server extends Remote {
	
	public byte[] grayscaleConvertion(byte[] imageBytes) throws RemoteException;
	
}
