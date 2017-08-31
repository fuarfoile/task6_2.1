/*
 * Main.java 25/08/2017
 *
 * Created by Bondarenko Oleh
 */


import java.io.*;

public class File {

    private static final String DATA_FILE = "Buyers_data.ser";

    public static boolean getFromFile(Object[] objects) {
        if (objects == null) return false;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            for (int i = 0; i < objects.length; i++) {
                objects[i] = in.readObject();
            }
            System.out.println("Reading complete");
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (EOFException e) {
            System.out.println("End of file");
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean writeToFile(Object[] objects) {
        if (objects == null) return false;
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            for (Object object : objects) {
                out.writeObject(object);
            }
            System.out.println("Writing complete");
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}