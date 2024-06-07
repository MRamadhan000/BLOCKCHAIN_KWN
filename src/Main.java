import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Blockchain blockchain = new Blockchain();
        // Create users
        User user1 = new User("User1", 0);
        User user2 = new User("User2", 0);
        User government = new User("Government", 0);

        // Add users to blockchain
        blockchain.addUser(user1);
        blockchain.addUser(user2);
        blockchain.addUser(government);

        // Add transactions
        blockchain.addTransaction(new Transaction("User1", "Government", 300000));
        blockchain.addTransaction(new Transaction("User2", "Government", 450000));

        //  Add facility transaction
        List<Contribution> contributions = new ArrayList<>();
        contributions.add(new Contribution(user1, 50000));
        contributions.add(new Contribution(user2, 150000));
        blockchain.addFacilityTransaction(new FacilityTransaction("Government", "School", 200000, contributions));

        // Print blockchain
        blockchain.printBlockchain();

        // Validate blockchain
        System.out.println("Blockchain valid: " + blockchain.isChainValid());
    }
}