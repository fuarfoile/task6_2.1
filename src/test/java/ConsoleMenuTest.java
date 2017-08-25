import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ConsoleMenuTest {
    ConsoleMenu consoleMenu;
    Buyer[] buyers;

    @Before
    public void initialize() {
        consoleMenu = new ConsoleMenu();
        buyers = new Buyer[20];
        for (int i = 0; i < buyers.length; i++) {
            buyers[i] = new Buyer();
        }
    }

    @Test
    public void testProcessCommandExit() {
        assertFalse(consoleMenu.processCommand(buyers, "exit"));
        assertTrue(consoleMenu.processCommand(buyers, "some unused command"));
        assertTrue(consoleMenu.processCommand(buyers, "showAll"));
    }

    @Test
    public void testProcessCommandSortName() {
        consoleMenu.processCommand(buyers, "sortAllByName");
        Buyer prevBuyer = buyers[0];
        for (int i = 1; i < buyers.length; i++) {
            assertTrue(buyers[i].getFirstName().compareTo(prevBuyer.getFirstName()) >= 0);
            prevBuyer = buyers[i];
        }
    }

    @Test
    public void testProcessCommandSortAddresses() {
        consoleMenu.processCommand(buyers, "sortAllByAddress");
        Buyer prevBuyer = buyers[0];
        for (int i = 1; i < buyers.length; i++) {
            assertTrue(buyers[i].getAddress().compareTo(prevBuyer.getAddress()) >= 0);
            prevBuyer = buyers[i];
        }
    }

    @Test
    public void testProcessCommandFileWR() {
        Buyer[] newBuyers = new Buyer[buyers.length];
        for (int i = 0; i < buyers.length; i++) {
           newBuyers[i] = buyers[i].clone();
        }

        consoleMenu.processCommand(buyers, "writeToFile");
        for (int i = 0; i < buyers.length; i++) {
            buyers[i] = new Buyer();
        }
        consoleMenu.processCommand(buyers, "getFromFile");

        for (int i = 0; i < buyers.length; i++) {
            assertEquals(newBuyers[i], buyers[i]);
        }
    }

    @Test
    public void testShowSurnamesStartFrom() {
        ArrayList<Buyer> resBuyers = consoleMenu.showSurnamesStartFrom(buyers, "K");
        for (Buyer buyer : resBuyers) {
            assertTrue(buyer.getSecondName().startsWith("K"));
        }
    }

    @Test
    public void testShowCardsFromDiapason() {
        ArrayList<Buyer> resBuyers = consoleMenu.showCardsFromDiapason(buyers, 0, 4999_9999_9999_9999L);
        for (Buyer buyer : resBuyers) {
            assertTrue(buyer.getCreditCardNumber() >= 0 &&
                    buyer.getCreditCardNumber() <= 4999_9999_9999_9999L);
        }
    }
}
