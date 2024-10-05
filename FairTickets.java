import java.util.Iterator;
import java.util.ListIterator;

public class FairTickets {

    public static void main(String[] args) {
        int npeople = 0;
        int ntickets = 0;

        if (args.length != 2) {
            throw new IllegalArgumentException("Please, type two arguments.");
        }

        try {
            npeople = Integer.parseInt(args[0]);
            if (npeople <= 0) {
                throw new IllegalArgumentException("Invalid number of people. Please type a positive number.");
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please, insert a number of people and a number of tickets.");
            System.exit(0);
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount of people. Please, type a number.");
            System.exit(0);
        }

        try {
            ntickets = Integer.parseInt(args[1]);
            if (ntickets <= 0) {
                throw new IllegalArgumentException("Invalid number of tickets. Please type a positive number.");
            } else if (ntickets % (2*npeople) != 0) {
                throw new IllegalArgumentException("Invalid number of tickets. Please, type a number that is a multiple of twice the number of people.");
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please, insert a number of people and a nubmer of tickets.");
            System.exit(0);
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount of tickets. Please, type a number.");
            System.exit(0);
        }

        People person = new People(npeople, ntickets);
        person.printpeople();
    }

    public static class People {
        MyLinkedList<String> names;
        MyLinkedList<MyLinkedList<Integer>> tickets;
    
    
        public People (int npeople, int ntickets) {
            this.names = new MyLinkedList<>();
            for (int i = 0; i < npeople; i++) {
                this.names.add("Person " + (i+1));
            }
    
            this.tickets = new MyLinkedList<>();
            for (int i = 0; i < npeople; i++) {
                MyLinkedList<Integer> ticket = new MyLinkedList<>();
                this.tickets.add(ticket);
            }
    
            assigntickets(ntickets);
        }
    
        private void assigntickets(int ntickets) {
            ListIterator<MyLinkedList<Integer>> iterator = this.tickets.listIterator();
            int seat = 1;
            boolean rightdirection = true;
            while (seat <= ntickets) {
                if (rightdirection) {
                    if (iterator.hasNext()) {
                        iterator.next().add(seat);
                        seat++;
                    } else {
                        rightdirection = false;
                    }
                } else {
                    if (iterator.hasPrevious()) {
                        iterator.previous().add(seat);
                        seat++;
                    } else {
                        rightdirection = true;
                    }
                }
            }
        }

        public int calcsum(MyLinkedList<Integer> numbers) {
            int returnee = 0;
            for (int number : numbers) {
                returnee = returnee + number;
            }
            return returnee;
        }
    
        public void printpeople() {
            ListIterator<MyLinkedList<Integer>> iterator = this.tickets.listIterator();
            for (String person : this.names) {
                System.out.println(person + ":");
                MyLinkedList<Integer> nextTickets = iterator.next();
                for (Integer ticket : nextTickets) {
                    System.out.println("Ticket " + ticket);
                }
                System.out.println("Sum of tickets: " + calcsum(nextTickets));
                System.out.println();
            }
        }
    
    }

}