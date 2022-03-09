public class CashRegister {
    private int bill2Qty = 50;
    private int bill1Qty = 50;
    private int bill05Qty = 50;
    private int bill01Qty = 50;

    public int getBill2Qty() {
        return bill2Qty;
    }

    public void setBill2Qty(int bill2Qty) {
        this.bill2Qty = bill2Qty;
    }

    public int getBill1Qty() {
        return bill1Qty;
    }

    public void setBill1Qty(int bill1Qty) {
        this.bill1Qty = bill1Qty;
    }

    public int getBill05Qty() {
        return bill05Qty;
    }

    public void setBill05Qty(int bill05Qty) {
        this.bill05Qty = bill05Qty;
    }

    public int getBill01Qty() {
        return bill01Qty;
    }

    public void setBill01Qty(int bill01Qty) {
        this.bill01Qty = bill01Qty;
    }

    @Override
    public String toString() {
        return "Value: 2, quantity: " + getBill2Qty() + "\n" +
                "Value: 1, quantity: " + getBill1Qty() + "\n" +
                "Value: 0.5, quantity: " + getBill05Qty() + "\n" +
                "Value: 0.1, quantity: " + getBill01Qty();
    }
}
