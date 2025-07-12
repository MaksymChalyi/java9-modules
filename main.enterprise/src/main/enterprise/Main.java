package main.enterprise;

import main.api.Accountant;
//import main.labor.market.AccountantLMImpl;
//import main.university.AccountantImpl;

import java.util.ServiceLoader;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, Maksym!");
        ServiceLoader<Accountant> loader = ServiceLoader.load(Accountant.class);
        loader.forEach(Accountant::doJob);
        System.out.println("Done!");

//        Accountant accountant1 = new AccountantImpl();
//        Accountant accountant2 = new AccountantLMImpl();
//        accountant1.doJob();
//        accountant2.doJob();
    }
}