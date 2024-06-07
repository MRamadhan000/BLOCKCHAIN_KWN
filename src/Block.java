import java.util.List;

class Block {
    public int index;
    public long timestamp;
    public List<Transaction> transactions;
    public String previousHash;
    public String hash;

    public Block(int index, long timestamp, List<Transaction> transactions, String previousHash, String hash) {
        this.index = index;
        this.timestamp = timestamp;
        this.transactions = transactions;
        this.previousHash = previousHash;
        this.hash = hash;
    }

    @Override
    public String toString() {
        StringBuilder transactionDetails = new StringBuilder();
        for (Transaction transaction : transactions) {
            transactionDetails.append(transaction.toString()).append("\n");
        }
        return "Index: " + index + "\nTimestamp: " + timestamp + "\nTransactions: \n" + transactionDetails.toString()
                + "Previous Hash: " + previousHash + "\nHash: " + hash;
    }
}