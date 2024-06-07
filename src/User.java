class User {
    public String name;
    public double balance;

    public User(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    public void adjustBalance(double amount) {
        this.balance += amount;
    }

    @Override
    public String toString() {
        return name + ": Rp" + balance;
    }
}