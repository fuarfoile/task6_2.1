/*
 * Buyer.java 25/08/2017
 *
 * Created by Bondarenko Oleh
 */


import java.io.Serializable;

public class Buyer implements Serializable, Cloneable {
    private String firstName;
    private String secondName;
    private String thirdName;
    private String address;
    private Long creditCardNumber;
    private Long bankAccountNumber;

    public Buyer() {
        firstName = RandomString.getFirstName();
        secondName = RandomString.getSecondName();
        thirdName = RandomString.getThirdName();
        address = RandomString.getAddress();
        creditCardNumber = (long) (Math.random() * 9999_9999_9999_9999L); //1000
        bankAccountNumber = (long) (Math.random() * 999_999_999_999_999_999L);
    }
    public Buyer(String firstName, String secondName, String thirdName, String address, long creditCardNumber, long bankAccountNumber) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.thirdName = thirdName;
        this.address = address;
        this.creditCardNumber = creditCardNumber;
        this.bankAccountNumber = bankAccountNumber;
    }

    @Override
    public Buyer clone(){
        Buyer newBuyer = new Buyer();
        newBuyer.firstName = firstName;
        newBuyer.secondName = secondName;
        newBuyer.thirdName = thirdName;
        newBuyer.address = address;
        newBuyer.creditCardNumber = creditCardNumber;
        newBuyer.bankAccountNumber = bankAccountNumber;

        return newBuyer;
    }

    @Override
    public boolean equals(Object obj){
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Buyer)) {
            return false;
        }
        Buyer otherBuyer = (Buyer) obj;

        return  (otherBuyer.firstName.equals(firstName) &&
            otherBuyer.secondName.equals(secondName) &&
            otherBuyer.thirdName.equals(thirdName) &&
            otherBuyer.address.equals(address) &&
            otherBuyer.creditCardNumber.equals(creditCardNumber) &&
            otherBuyer.bankAccountNumber.equals(bankAccountNumber));
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getThirdName() {
        return thirdName;
    }

    public String getAddress() {
        return address;
    }

    public Long getCreditCardNumber() {
        return creditCardNumber;
    }

    public Long getBankAccountNumber() {
        return bankAccountNumber;
    }

    @Override
    public String toString() {
        String zeros = "00000000000000000000";

        String result = firstName + " " + secondName + " " + thirdName;
        result += "\n  Address: " + address;

        String card = zeros.substring(0, 16 - creditCardNumber.toString().length()) + creditCardNumber;
        card = card.substring(0, 4) + "_" + card.substring(4, 8) + "_" + card.substring(8, 12) + "_" + card.substring(12, 16);

        String bankNumber = zeros.substring(0, 18 - bankAccountNumber.toString().length()) + bankAccountNumber;

        result += "\n  Credit Card Number: " + card + "\n  Bank Account Number: " + bankNumber;
        return result;
    }
}