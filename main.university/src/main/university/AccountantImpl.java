package main.university;

import main.api.Accountant;

public class AccountantImpl implements Accountant {

    private static int counter = 1;
    private final int id = counter++;
    {
        System.out.println("Creating Accountant " + id);
    }

    public void doJob() {
        System.out.printf("I'm an accountant #%d from University%n", id);
    }
}
