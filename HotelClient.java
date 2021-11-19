import java.rmi.Naming;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;

public class HotelClient {
    public static void main(String[] args) {
        try {
            RoomManager R = (RoomManager)Naming.lookup("rmi://" + args[1] + "/HotelServer");
            if(args.length < 2) {
                printCommands();
            }
            if(args[0].equals("list")) {
                System.out.println(R.list());
            }
            if(args[0].equals("book")) {
                System.out.println(R.book(args[2], args[3]));
                System.out.println(R.list());
            }
            if(args[0].equals("guests")) {
                System.out.println(R.guests());
            }
            if(args[0].equals("revenue")) {
                System.out.println(R.revenue());
            }
        }
        catch (MalformedURLException mfue) {
            System.out.println(mfue.getMessage());
        }
        catch (RemoteException re) {
            System.out.println(re.getMessage());
        }
        catch (NotBoundException nbe) {
            System.out.println(nbe.getMessage());
        }
        catch (ArrayIndexOutOfBoundsException aiobe) {
            System.out.println("- - - Hotel Reservation System - - -\n");
            System.out.println("List available rooms:\t list\t <hostname>");
            System.out.println("Create reservation:\t book\t <hostname> <type> <name>");
            System.out.println("View hotel guests:\t guests\t <hostname>");
            System.out.println("View Revenue:\t revenue\t <hostname>");
        }
    }

    public static void printCommands() {
        System.out.println("");
        System.out.println("java HotelClient list <server address>:");
        System.out.println("java HotelClient book <server address> <room type> <guest name>");
        System.out.println("java HotelClient guests <server address>:");
        System.out.println("java HotelClient revenue <server address>:");
        System.out.println("");
    }
}
