/*
 * RandomString.java 28/07/2017
 *
 * Created by Bondarenko Oleh
 */


import java.util.Random;

public class RandomString {

    public static String getFirstName() {
        return Names.firstNames[new Random().nextInt(Names.firstNames.length)];
    }

    public static String getSecondName() {
        return Names.secondNames[new Random().nextInt(Names.secondNames.length)];
    }

    public static String getThirdName() {
        return Names.secondNames[new Random().nextInt(Names.secondNames.length)];
    }

    public static String getAddress() {
        return Addresses.address[new Random().nextInt(Addresses.address.length)] + " " + (new Random().nextInt(200) + 1);
    }
}