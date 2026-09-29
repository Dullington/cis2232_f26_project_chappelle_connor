package ca.hccis.files.entity;

import java.util.Scanner;

public class Tennis {

    private int id;
    private int setsPlayed;
    private String firstName;
    private String lastName;
    private String dateOfBirth;

    public Tennis() {
    }

    public Tennis(int id, int setsPlayed, String firstName, String lastName, String dateOfBirth) {
        this.id = id;
        this.setsPlayed = setsPlayed;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public void getInformation() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("How many sets have they played? (1-20) ");
        setsPlayed = scanner.nextInt();
        scanner.nextLine();
        System.out.print("What is their first name? ");
        firstName = scanner.nextLine();
        System.out.print("What is their last name? ");
        lastName = scanner.nextLine();
        System.out.print("What is their date of birth? ");
        dateOfBirth = scanner.nextLine();
    }

    public void edit(){
        String fName = ca.hccis.files.util.CisUtility.getInputString("First Name: ");
        String lName = ca.hccis.files.util.CisUtility.getInputString("Last Name: ");
        String dob = ca.hccis.files.util.CisUtility.getInputString("DOB: ");

        setFirstName(fName);
        setLastName(lName);
        setDateOfBirth(dob);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSetsPlayed() {
        return setsPlayed;
    }

    public void setSetsPlayed(int setsPlayed) {
        this.setsPlayed = setsPlayed;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return String.format(
                "Player: setsPlayed=%d, firstName='%s', lastName='%s', dateOfBirth='%s'",
                setsPlayed, firstName, lastName, dateOfBirth
        );
    }

}
