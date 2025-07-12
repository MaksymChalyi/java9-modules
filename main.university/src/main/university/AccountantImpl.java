package main.university;

import main.api.Accountant;

public class AccountantImpl implements Accountant {

    @Override
    public void doJob() {
        System.out.println("I am AccountantImpl");
    }

}
