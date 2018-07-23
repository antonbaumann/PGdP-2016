package pgdp;

public class Test {

    public static void main(String[] args) {
        int points = 0;

        /*
        Money
         */
        if (new Money(100).addMoney(new Money(-100)).getCent() == 0
                && new Money(10_000).addMoney(new Money(-5_000)).getCent() == 5_000)
            ++points;
        else
            System.out.println("addMoney failed");

        if ("100,01 Euro".equals(new Money(10001).toString())
                && "1,00 Euro".equals(new Money(100).toString())
                && "0,99 Euro".equals(new Money(99).toString())
                && "42,42 Euro".equals(new Money(4242).toString()))
            ++points;
        else
            System.out.println("€ != Euro");

        Money m1 = new Money(5);
        Money m2 = new Money(1);
        Money m3 = m1.addMoney(m2);
        if (m3 != m2 && m3 != m1 && m1.getCent() == 5 && m2.getCent() == 1)
            ++points;
        else
            System.out.println("Money is mutable");

        /*
        Bank
         */
        Bank b = new Bank();

        int num_reuss = b.newAccount("Andreas", "Reuss");
        assert (num_reuss != 42);
        b.depositOrWithdraw(num_reuss, new Money(1));

        int num_palenta = b.newAccount("Raphaela", "Palenta");
        assert (num_palenta != 42);
        b.depositOrWithdraw(num_palenta, new Money(10_000));

        Money balance_reuss_bevore = b.getBalance(num_reuss);
        b.transfer(num_reuss, num_reuss, new Money(100_000_000));
        Money balance_reuss_after = b.getBalance(num_reuss);
        if (balance_reuss_bevore.getCent() == balance_reuss_after.getCent())
            ++points;
        else
            System.out.println("self transfer failed");

        if (b.transfer(num_palenta, num_reuss, new Money(9_999)))
            ++points;
        else
            System.out.println("simple transfer failed");
        if (b.getBalance(num_reuss).getCent() == 10_000 && b.getBalance(num_palenta).getCent() == 1)
            ++points;
        else
            System.out.println("simple transfer (wrong balance)");

        Money balance_palenta_bevore = b.getBalance(num_palenta);
        if (!b.transfer(num_palenta, 42, new Money(42))
                && balance_palenta_bevore.getCent() == b.getBalance(num_palenta).getCent()
                && !b.transfer(42, num_palenta, new Money(42))
                && balance_palenta_bevore.getCent() == b.getBalance(num_palenta).getCent())
            points += 2;
        else
            System.out.println("Your method transfer is not transactional");

        b.removeAccount(num_reuss);
        if (b.getBalance(num_reuss) == null)
            ++points;
        else
            System.out.println("Account not removed");

        b = new Bank();
        b.removeAccount(42);

        System.out.println("You received " + points + " points.");
    }
}
