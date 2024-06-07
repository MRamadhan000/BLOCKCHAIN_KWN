import java.util.ArrayList;
import java.util.List;

class Blockchain {
    private List<Block> chain;
    private List<User> users;

    public Blockchain() {
        this.chain = new ArrayList<>();
        this.users = new ArrayList<>();

        // Genesis block
        this.chain.add(createGenesisBlock());
    }

    private Block createGenesisBlock() {
        return new Block(0, System.currentTimeMillis(), new ArrayList<>(), "0", calculateHash(0, System.currentTimeMillis(), new ArrayList<>(), "0"));
    }

    private String calculateHash(int index, long timestamp, List<Transaction> transactions, String previousHash) {
        StringBuilder dataToHash = new StringBuilder();
        dataToHash.append(index).append(timestamp);
        for (Transaction transaction : transactions) {
            dataToHash.append(transaction.toString());
        }
        dataToHash.append(previousHash);
        return String.valueOf(dataToHash.toString().hashCode());
    }

    public void addUser(User user) {
        users.add(user);
    }

    public User getUser(String name) {
        for (User user : users) {
            if (user.name.equals(name)) {
                return user;
            }
        }
        return null;
    }

    public void addTransaction(Transaction transaction) {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        String previousHash = chain.get(chain.size() - 1).hash;
        Block newBlock = new Block(chain.size(), System.currentTimeMillis(), transactions, previousHash, calculateHash(chain.size(), System.currentTimeMillis(), transactions, previousHash));

        // Update balances
        User fromUser = getUser(transaction.from);
        User toUser = getUser(transaction.to);
        if (fromUser != null && toUser != null) {
            fromUser.adjustBalance(transaction.amount);
            toUser.adjustBalance(transaction.amount);
        }
        chain.add(newBlock);
    }

    public void addFacilityTransaction(FacilityTransaction transaction) {
        double userAmount = 0;
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        String previousHash = chain.get(chain.size() - 1).hash;
        Block newBlock = new Block(chain.size(), System.currentTimeMillis(), transactions, previousHash, calculateHash(chain.size(), System.currentTimeMillis(), transactions, previousHash));

        // Update balances for each contribution
        for (Contribution contribution : transaction.contributions) {
            contribution.user.adjustBalance(-contribution.amount);
            userAmount+=contribution.amount;
        }

        // Add transaction amount to the government's balance
        User government = getUser(transaction.from);
        if (government != null) {
            government.adjustBalance(transaction.amount - (userAmount *2));
        }
        chain.add(newBlock);
    }

    public boolean isChainValid() {
        for (int i = 1; i < chain.size(); i++) {
            Block currentBlock = chain.get(i);
            Block previousBlock = chain.get(i - 1);

            if (!currentBlock.hash.equals(calculateHash(currentBlock.index, currentBlock.timestamp, currentBlock.transactions, currentBlock.previousHash))) {
                return false;
            }

            if (!currentBlock.previousHash.equals(previousBlock.hash)) {
                return false;
            }
        }
        return true;
    }

    public void printBlockchain() {
        for (Block block : chain) {
            System.out.println(block.toString());
        }

        System.out.println("Saldo akhir pengguna:");
        for (User user : users) {
            System.out.println(user.toString());
        }
    }
}