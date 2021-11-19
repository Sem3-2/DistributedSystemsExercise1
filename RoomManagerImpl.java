import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

class Rooms {
    int v;
    int w;
    int x;
    int y;
    int z;

    public Rooms() {
        v = 10;
        w = 20;
        x = 5;
        y = 3;
        z = 2;
    }

    int isAvailable(String type) {
        if(type.equals("0"))
            return v;
        if(type.equals("1"))
            return w;
        if(type.equals("2"))
            return x;
        if(type.equals("3"))
            return y;
        if(type.equals("4"))
            return z;
        return 0;
    }

    int itCosts(String type) {
        if(type.equals("0"))
            return 55000;
        if(type.equals("1"))
            return 75000;
        if(type.equals("2"))
            return 80000;
        if(type.equals("3"))
            return 150000;
        if(type.equals("4"))
            return 230000;
        return 0;
    }
}

class Reservation {
    String roomType;
    String reservationName;
    int reservationPrice;

    public Reservation(String type, String name, int price) {
        roomType = type;
        reservationName = name;
        reservationPrice = price;
    }
}

public class RoomManagerImpl extends UnicastRemoteObject implements RoomManager {

    Rooms rooms = new Rooms();
    ArrayList<Reservation> reservations = new ArrayList<Reservation>();

    public RoomManagerImpl() throws RemoteException {
        super();
    }

    public String list() throws RemoteException {
        String message = "";
        message = "\n- - - List of available rooms - - -\n";
        message += rooms.v + " rooms of Type 0 available at the price of " + rooms.itCosts("0") + "UGX per night\n";
        message += rooms.w + " rooms of Type 1 available at the price of " + rooms.itCosts("1") + "UGX per night\n";
        message += rooms.x + " rooms of Type 2 available at the price of " + rooms.itCosts("2") + "UGX per night\n";
        message += rooms.y + " rooms of Type 3 available at the price of " + rooms.itCosts("3") + "UGX per night\n";
        message += rooms.z + " rooms of Type 4 available at the price of " + rooms.itCosts("4") + "UGX per night\n";
        return message;
    }

    public String book(String type, String name) throws RemoteException {
        String message = "";
        int cost = rooms.itCosts(type);

        if(rooms.isAvailable(type) >= 1) {
            reservations.add(new Reservation(type, name, cost));
            message = "Reservation created successfully!\n";
            message += "Cost: " + cost + "UGX\n";
            switch(type) {
                case "0": rooms.v --; break;
                case "1": rooms.w --; break;
                case "2": rooms.x --; break;
                case "3": rooms.y --; break;
                case "4": rooms.z --; break;
            }
        } else {
            message = "Something went wrong during the reservation process!\n";
            message += "There are " + rooms.isAvailable(type) + " available!\n";
            message += "Cost: " + rooms.itCosts(type)  + "UGX\n";
        }
        return message;
    }

    public String guests() throws RemoteException {
        String message = "Type\tNumber\tName\tPrice\n";
        int i;
        for(i=0; i<reservations.size(); i++) {
            message += reservations.get(i).roomType + "\t";
            message += reservations.get(i).reservationName + "\t" + reservations.get(i).reservationPrice + "\n";
        }
        return message;
    }

    public String revenue() throws RemoteException {
        String message = "";
        int i, totalRevenue = 0;
        for(i=0; i<reservations.size(); i++) {
            message += reservations.get(i).roomType + "\t";
            message += reservations.get(i).reservationPrice + "\n";
            totalRevenue += reservations.get(i).reservationPrice;
        }
        message += "And the total revenue is " + totalRevenue;
        return message;
    }
}