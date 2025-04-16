import java.util.*;

class TicketCounter {
    private int availableSeats;

    public TicketCounter(int seats) {
        this.availableSeats = seats;
    }

    // Synchronized method to book seats
    public synchronized boolean bookTicket(String customerName, int seatsRequested) {
        if (seatsRequested <= availableSeats) {
            System.out.println(customerName + " successfully booked " + seatsRequested + " seat(s).");
            availableSeats -= seatsRequested;
            return true;
        } else {
            System.out.println("Booking failed for " + customerName + ". Not enough seats.");
            return false;
        }
    }

    public synchronized int getAvailableSeats() {
        return availableSeats;
    }
}

// Booking thread
class BookingThread extends Thread {
    private TicketCounter counter;
    private String customerName;
    private int seatsToBook;

    public BookingThread(TicketCounter counter, String customerName, int seatsToBook, boolean isVIP) {
        this.counter = counter;
        this.customerName = customerName;
        this.seatsToBook = seatsToBook;
        this.setName(customerName);
        if (isVIP) {
            this.setPriority(Thread.MAX_PRIORITY);
        } else {
            this.setPriority(Thread.MIN_PRIORITY);
        }
    }

    @Override
    public void run() {
        counter.bookTicket(customerName, seatsToBook);
    }
}

public class TicketBookingSystem {
    public static void main(String[] args) {
        TicketCounter counter = new TicketCounter(5); // total 5 seats

        // Creating booking threads
        BookingThread vip1 = new BookingThread(counter, "VIP John", 2, true);
        BookingThread vip2 = new BookingThread(counter, "VIP Emma", 2, true);
        BookingThread regular1 = new BookingThread(counter, "Alice", 2, false);
        BookingThread regular2 = new BookingThread(counter, "Bob", 1, false);

        // Start booking
        vip1.start();
        vip2.start();
        regular1.start();
        regular2.start();

        // Wait for all threads to complete
        try {
            vip1.join();
            vip2.join();
            regular1.join();
            regular2.join();
        } catch (InterruptedException e) {
            System.out.println("Booking interrupted.");
        }

        System.out.println("\nFinal available seats: " + counter.getAvailableSeats());
    }
}
