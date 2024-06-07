import java.util.List;

class FacilityTransaction extends Transaction {
    public String facility;
    public List<Contribution> contributions;

    public FacilityTransaction(String from, String facility, double amount, List<Contribution> contributions) {
        super(from, facility, amount);
        this.facility = facility;
        this.contributions = contributions;
    }

    @Override
    public String toString() {
        StringBuilder contribs = new StringBuilder();
        for (Contribution contribution : contributions) {
            contribs.append(contribution.user.name).append(": Rp").append(contribution.amount).append(" ");
        }
        return from + " funds Building a " + facility + ": Rp" + amount + " Contributions: " + contribs.toString();
    }
}