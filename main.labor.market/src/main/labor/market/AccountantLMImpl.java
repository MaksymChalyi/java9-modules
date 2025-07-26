package main.labor.market;

import main.api.Accountant;

public class AccountantLMImpl implements Accountant {

    private static int counter = 1;
    private final int id = counter++;

    {
        System.out.println("Creating AccountantLMImpl " + id);
    }

    public void doJob() {
        System.out.printf("I'm an accountant #%d from labor market%n", id);
    }
}
