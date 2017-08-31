import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(value=Parameterized.class)
public class ConsoleMenuTest {
    enum Type {COMMAND, READ, WRITE, SORT, START_FROM_K, CARDS_DIAPASON}

    @Parameterized.Parameter(0)
    public Type type;
    @Parameterized.Parameter(1)
    public String command;
    @Parameterized.Parameter(2)
    public Boolean expectedBool;
    @Parameterized.Parameter(3)
    public Buyer[] buyers;
    @Parameterized.Parameter(4)
    public Buyer[] expectedBuyer;

    private ConsoleMenu consoleMenu;

    @Parameterized.Parameters
    public static Collection data() {
        return Arrays.asList( new Object[][] {
                { Type.COMMAND, "exit",             Boolean.FALSE,  new Buyer[0],               null },
                { Type.COMMAND, "some unused",      Boolean.TRUE,   new Buyer[0],               null },
                { Type.COMMAND, "showAll",          Boolean.TRUE,   new Buyer[0],               null },
                { Type.WRITE,   "getFromFile",      Boolean.TRUE,   new Buyer[]{new Buyer()},   null },
                { Type.WRITE,   "getFromFile",      Boolean.TRUE,   new Buyer[0],               null },
                { Type.WRITE,   "getFromFile",      Boolean.FALSE,  null,                       null },
                { Type.READ,    "getFromFile",      Boolean.TRUE,   new Buyer[0],               null },
                { Type.READ,    "getFromFile",      Boolean.FALSE,  null,                       null },
                { Type.SORT,    "sortAllByName",    Boolean.TRUE,   new Buyer[]{
                            new Buyer("Connor", "Alex", "Alex", "Some address", 1000, 1000),
                            new Buyer("Peter", "Alex", "Alex", "Some address", 1000, 1000),
                            new Buyer("Alex", "Alex", "Alex", "Some address", 1000, 1000),
                            new Buyer("Brayan", "Alex", "Alex", "Some address", 1000, 1000)},
                            new Buyer[]{
                                new Buyer("Alex", "Alex", "Alex", "Some address", 1000, 1000),
                                new Buyer("Brayan", "Alex", "Alex", "Some address", 1000, 1000),
                                new Buyer("Connor", "Alex", "Alex", "Some address", 1000, 1000),
                                new Buyer("Peter", "Alex", "Alex", "Some address", 1000, 1000)}},
                { Type.SORT, "sortAllByName", Boolean.TRUE, new Buyer[]{
                        new Buyer("Alexis", "", "", "Some address", 0, 0),
                        new Buyer("Peter", "", "", "Some address", 0, 0),
                        new Buyer("Alex", "", "", "Some address", 0, 0),
                        new Buyer("Brayan", "", "", "Some address", 0, 0)},
                        new Buyer[]{
                                new Buyer("Alex", "", "", "Some address", 0, 0),
                                new Buyer("Alexis", "", "", "Some address", 0, 0),
                                new Buyer("Brayan", "", "", "Some address", 0, 0),
                                new Buyer("Peter", "", "", "Some address", 0, 0)}},
                { Type.SORT, "sortAllByAddress", Boolean.TRUE, new Buyer[]{
                        new Buyer("Connor", "Alex", "Alex", "Address 55", 1000, 1000),
                        new Buyer("Peter", "Alex", "Alex", "Address 51", 1000, 1000),
                        new Buyer("Alex", "Alex", "Alex", "Some address", 1000, 1000),
                        new Buyer("Brayan", "Alex", "Alex", "Another address", 1000, 1000)},
                        new Buyer[]{
                                new Buyer("Peter", "Alex", "Alex", "Address 51", 1000, 1000),
                                new Buyer("Connor", "Alex", "Alex", "Address 55", 1000, 1000),
                                new Buyer("Brayan", "Alex", "Alex", "Another address", 1000, 1000),
                                new Buyer("Alex", "Alex", "Alex", "Some address", 1000, 1000)}},
                { Type.START_FROM_K, "showSurnamesStartFrom", Boolean.TRUE, new Buyer[]{
                        new Buyer("Connor", "King", "Alex", "Address 55", 1000, 1000),
                        new Buyer("Peter", "Martin", "Alex", "Address 51", 1000, 1000),
                        new Buyer("Alex", "Kristin", "Alex", "Some address", 1000, 1000),
                        new Buyer("Brayan", "Alex", "Alex", "Another address", 1000, 1000)},
                        new Buyer[]{
                                new Buyer("Connor", "King", "Alex", "Address 55", 1000, 1000),
                                new Buyer("Alex", "Kristin", "Alex", "Some address", 1000, 1000)}},
                { Type.CARDS_DIAPASON, "showCardsFromDiapason", Boolean.TRUE, new Buyer[]{
                        new Buyer("Connor", "Alex", "Alex", "Address 55", 1000, 1000),
                        new Buyer("Peter", "Alex", "Alex", "Address 51", 101, 1000),
                        new Buyer("Alex", "Alex", "Alex", "Some address", 286, 1000),
                        new Buyer("Brayan", "Alex", "Alex", "Another address", 578, 1000)},
                        new Buyer[]{
                                new Buyer("Peter", "Alex", "Alex", "Address 51", 101, 1000),
                                new Buyer("Alex", "Alex", "Alex", "Some address", 286, 1000)}},

        });
    }

    @Before
    public void initialize() {
        consoleMenu = new ConsoleMenu();
    }

    @Test
    public void testProcessCommandExit() {
        Assume.assumeTrue(type == Type.COMMAND);
        assertEquals(expectedBool, consoleMenu.processCommand(buyers, command));
    }

    @Test
    public void testProcessCommandSort() {
        Assume.assumeTrue(type == Type.SORT);
        consoleMenu.processCommand(buyers, command);
        assertArrayEquals(expectedBuyer, buyers);
    }

    @Test
    public void testProcessCommandFileWrite() {
        Assume.assumeTrue(type == Type.WRITE);
        assertEquals(expectedBool, File.writeToFile(buyers));
    }

    @Test
    public void testProcessCommandFileRead() {
        Assume.assumeTrue(type == Type.READ);
        assertEquals(expectedBool, File.getFromFile(buyers));
    }

    @Test
    public void testShowSurnamesStartFrom() {
        Assume.assumeTrue(type == Type.START_FROM_K);
        assertArrayEquals(expectedBuyer, consoleMenu.showSurnamesStartFrom(buyers, "K").toArray());
    }

    @Test
    public void testShowCardsFromDiapason() {
        Assume.assumeTrue(type == Type.CARDS_DIAPASON);
        assertArrayEquals(expectedBuyer, consoleMenu.showCardsFromDiapason(buyers, 0, 500).toArray());
    }
}