class Transaction {
    public String from;
    public String to;
    public double amount;

    public Transaction(String from, String to, double amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return from + " -> " + to + ": Rp" + amount;
    }
}