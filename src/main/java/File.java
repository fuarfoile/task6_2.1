/*
 * Main.java 25/08/2017
 *
 * Created by Bondarenko Oleh
 */


import java.io.*;

public class File {

    private static final String DATA_FILE = "Buyers_data.ser";

    public static void getFromFile(Object[] objects) {

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            for (int i = 0; i < objects.length; i++) {
                objects[i] = in.readObject();
            }
            System.out.println("Reading complete");
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (EOFException e) {
            System.out.println("End of file");
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            e.printStackTrace();
        }
    }

    public static void writeToFile(Object[] objects) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            for (Object object : objects) {
                out.writeObject(object);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}