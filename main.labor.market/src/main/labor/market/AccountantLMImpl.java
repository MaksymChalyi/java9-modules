package main.labor.market;

import main.api.Accountant;

public class AccountantLMImpl  implements Accountant {

    @Override
    public void doJob() {
        System.out.println("I am AccountantLMImpl");
    }
}
