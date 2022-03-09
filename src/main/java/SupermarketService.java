import java.util.Scanner;

public interface SupermarketService {
    void startUp();
    void menu();
    void productInventoryInfo(String inventoryInfo, String cashRegisterInfo);
    ProductStorage checkIfProductIsInStock(String productName);
    boolean checkIfCashIsAccepted (double cashGiven);
    void giveChange(double change);
    Change calculateIfEnoughChange(double changeAmount);
    void transaction (Scanner sc);
    int calcBillsAndCoin (double value, int quantity, double changeAmount);
}
