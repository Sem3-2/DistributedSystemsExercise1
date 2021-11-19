import java.rmi.Naming;


public class HotelServer {
    public HotelServer() {
        try {
            RoomManager R = new RoomManagerImpl();
            Naming.rebind("rmi://localhost:1099/HotelServer", R);

            System.out.println("HotelServer Started Successfully!");
        } catch (Exception e) {
            System.out.println("HotelServer can't start, error:" + e);
        }
    }
    public static void main(String[] args) {
        new HotelServer();
    }
}