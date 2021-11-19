import java.rmi.RemoteException;
import java.rmi.Remote;

public interface RoomManager extends Remote {
    public String list() throws RemoteException;
    public String book(String type, String name) throws RemoteException;
    public String guests() throws RemoteException;
    public String revenue() throws RemoteException;
}
