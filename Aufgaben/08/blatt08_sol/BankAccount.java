package pgdp;

public class BankAccount {

    private final int accountnumber;
    private final String firstname;
    private final String surname;
    private Money balance;

    public BankAccount(int accountnumber, String fname, String sname) {
        this.accountnumber = accountnumber;
        this.firstname = fname;
        this.surname = sname;
        this.balance = new Money();
    }

    public int getAccountnumber() {
        return accountnumber;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getSurname() {
        return surname;
    }

    public Money getBalance() {
        return balance;
    }

    public void addMoney(Money m) {
        balance = balance.addMoney(m);
    }

    @Override
    public String toString() {
        return firstname + " " + surname + " (" + accountnumber + "): " + balance;
    }
}
