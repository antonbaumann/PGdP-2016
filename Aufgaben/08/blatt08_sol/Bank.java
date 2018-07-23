package pgdp;

public class Bank {

    private BankAccountList accounts;

    private class BankAccountList {

        public final BankAccount info;
        public BankAccountList next;

        public BankAccountList(BankAccount data) {
            this.info = data;
        }

        public void addElement(BankAccount b) {
            if (next == null) next = new BankAccountList(b);
            else next.addElement(b);
        }

        public BankAccountList removeElement(int accountnumber) {
            if (info.getAccountnumber() == accountnumber)
                return next;

            if (next != null) next = next.removeElement(accountnumber);
            return this;
        }
    }

    public int newAccount(String firstname, String surname) {
        int n = 0;
        for (BankAccountList i = accounts; i != null; i = i.next, ++n);

        BankAccount newAccount = new BankAccount(n, firstname, surname);
        if (accounts == null) accounts = new BankAccountList(newAccount);
        else accounts.addElement(newAccount);

        return n;
    }

    public void removeAccount(int accountnumber) {
        if (accounts != null) accounts = accounts.removeElement(accountnumber);
    }

    public Money getBalance(int accountnumber) {
        for (BankAccountList i = accounts; i != null; i = i.next) {
            if (i.info.getAccountnumber() == accountnumber) return i.info.getBalance();
        }

        return null;
    }

    public boolean depositOrWithdraw(int accountnumber, Money m) {
        for (BankAccountList i = accounts; i != null; i = i.next) {
            if (i.info.getAccountnumber() == accountnumber) {
                i.info.addMoney(m);
                return true;
            }
        }

        return false;
    }

    public boolean transfer(int from, int to, Money m) {
        BankAccount x = null, y = null;
        for (BankAccountList i = accounts; i != null; i = i.next) {
            if (i.info.getAccountnumber() == from) x = i.info;
            if (i.info.getAccountnumber() == to) y = i.info;
            if (x != null && y != null) break;
        }

        if (x == null || y == null) return false;

        x.addMoney(new Money(-m.getCent()));
        y.addMoney(m);

        return true;
    }
}
