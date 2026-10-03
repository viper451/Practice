package com.example.DesignPattern.StraFac;

public class StraFac {

    public static void main(String args[]){

     PaymentStratergy stratergy  = PaymentFactory.paymentFactory("UPI");

     PaymentService paymentService = new PaymentService(stratergy);

     paymentService.pay();


    }
}

class UpiPayment implements PaymentStratergy {

    @Override
    public void pay(double amount) {
        // TODO
    }
}

class CreditCardPayment implements PaymentStratergy {

    @Override
    public void pay(double amount) {
        // TODO
    }
}

class NetBankingPayment implements PaymentStratergy {

    @Override
    public void pay(double amount) {
        // TODO
    }
}


interface PaymentStratergy{

    void pay(double amount);
}

class PaymentFactory{

    public static PaymentStratergy paymentFactory(String type) {
        if (type.equals("UPI")) {
            return new UpiPayment();
        }
        if (type.equals("NET")) {
            return new NetBankingPayment();
        }
        if (type.equals("CC")) {
            return new CreditCardPayment();
        }
        return  null;
    }

}

class PaymentService{

    private PaymentStratergy paymentStratergy;

    public PaymentService(PaymentStratergy paymentStratergy){
        this.paymentStratergy = paymentStratergy;
    }

    public void pay(){}

}


