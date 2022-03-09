import org.decimal4j.util.DoubleRounder;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SupermarketServiceImpl implements SupermarketService{

    private List<ProductStorage> products = new ArrayList<>();
    private CashRegister cashRegister = new CashRegister();

    private static SupermarketServiceImpl instance = null;

    public static SupermarketServiceImpl getInstance(){
        if (instance == null){
            instance = new SupermarketServiceImpl();
        }
        return instance;
    }

    @Override
    public void startUp() {
        products.add(new ProductStorage("Bread", 1.5, 100));
        products.add(new ProductStorage("Cola", 2.3, 50));
        products.add(new ProductStorage("Pork", 3.7, 76));
        products.add(new ProductStorage("Wine", 5.2, 36));
    }

    @Override
    public void menu() {
        Scanner sc = new Scanner(System.in);
        startUp();
        productInventoryInfo("Initial product inventory:", "Initial cash inventory");
        transaction(sc);
        while (true){
            productInventoryInfo("Updated product inventory:", "Updated cash inventory");
            transaction(sc);
        }
    }

    @Override
    public void productInventoryInfo(String inventoryInfo, String cashRegisterInfo) {
        System.out.println("-----------------------------");
        System.out.println(inventoryInfo);
        products.stream().forEach(System.out::println);
        System.out.println(cashRegisterInfo);
        System.out.println(cashRegister.toString());
        System.out.println("-----------------------------");
        System.out.println("At any time you can input 0 to cancel your transaction.\n");
    }

    @Override
    public ProductStorage checkIfProductIsInStock(String productName) {
        for (ProductStorage product : products) {
            try {
                if (product.getName().equalsIgnoreCase(productName) && product.getQuantity() <= 0) {
                    throw new SoldOutException();
                } else if (product.getName().equalsIgnoreCase(productName) && product.getQuantity() > 0){
                    return product;
                }
            } catch (SoldOutException e){
                System.out.println(e);
            }
        }
        return null;
    }

    @Override
    public boolean checkIfCashIsAccepted(double cashGiven) {
        try {
            if (cashGiven == 2) {
                return true;
            } else if (cashGiven == 1) {
                return true;
            } else if (cashGiven == 0.5) {
                return true;
            } else if (cashGiven == 0.1) {
                return true;
            } else {
                throw new PayNotAcceptedException();
            }
        } catch (PayNotAcceptedException e){
            System.out.println(e);
            return false;
        }
    }

    @Override
    public void giveChange(double change) {
        try {
            Change changeRecieved = calculateIfEnoughChange(change);
            if (changeRecieved == (null)) {
                throw new NotEnoughtChangeException();
            } else {
                return;
            }
        }catch (NotEnoughtChangeException e){
            System.out.println(e);
            return;
        }

    }

    @Override
    public Change calculateIfEnoughChange(double changeAmount) {
        changeAmount = DoubleRounder.round(changeAmount, 1);
        Change change = new Change();
        int amountOf2 = calcBillsAndCoin(2, cashRegister.getBill2Qty(), changeAmount);
        changeAmount = DoubleRounder.round(changeAmount - 2 * amountOf2, 1);
        if (changeAmount <= 0){
            System.out.println("Here is your change:");
            if(amountOf2 != 0) {
                change.setBill2Qty(amountOf2);
                cashRegister.setBill2Qty(cashRegister.getBill2Qty() - amountOf2);
                System.out.println("Value: 2, quantity: " + amountOf2);
            }
            return change;
        }
        int amountOf1 = calcBillsAndCoin(1, cashRegister.getBill1Qty(), changeAmount);
        changeAmount = DoubleRounder.round(changeAmount - 1 * amountOf1, 1);
        if (changeAmount <= 0){
            System.out.println("Here is your change:");
            if(amountOf2 != 0) {
                change.setBill2Qty(amountOf2);
                cashRegister.setBill2Qty(cashRegister.getBill2Qty() - amountOf2);
                System.out.println("Value: 2, quantity: " + amountOf2);
            }
            if(amountOf1 != 0) {
                change.setBill1Qty(amountOf1);
                cashRegister.setBill1Qty(cashRegister.getBill1Qty() - amountOf1);
                System.out.println("Value: 1, quantity: " + amountOf1);
            }
            return change;
        }
        int amountOf05 = calcBillsAndCoin(0.5, cashRegister.getBill05Qty(), changeAmount);
        changeAmount = DoubleRounder.round(changeAmount - 0.5 * amountOf05, 1);
        if (changeAmount <= 0){
            System.out.println("Here is your change:");
            if(amountOf2 != 0) {
                change.setBill2Qty(amountOf2);
                cashRegister.setBill2Qty(cashRegister.getBill2Qty() - amountOf2);
                System.out.println("Value: 2, quantity: " + amountOf2);
            }
            if(amountOf1 != 0) {
                change.setBill1Qty(amountOf1);
                cashRegister.setBill1Qty(cashRegister.getBill1Qty() - amountOf1);
                System.out.println("Value: 1, quantity: " + amountOf1);
            }
            if(amountOf05 != 0) {
                change.setBill05Qty(amountOf05);
                cashRegister.setBill05Qty(cashRegister.getBill05Qty() - amountOf05);
                System.out.println("Value: 0.5, quantity: " + amountOf05);
            }
            return change;
        }
        int amountOf01 = calcBillsAndCoin(0.1, cashRegister.getBill01Qty(), changeAmount);
        changeAmount = DoubleRounder.round(changeAmount - 0.1 * amountOf01, 1);
        if (changeAmount <= 0){
            System.out.println("Here is your change:");
            if(amountOf2 != 0) {
                change.setBill2Qty(amountOf2);
                cashRegister.setBill2Qty(cashRegister.getBill2Qty() - amountOf2);
                System.out.println("Value: 2, quantity: " + amountOf2);
            }
            if(amountOf1 != 0) {
                change.setBill1Qty(amountOf1);
                cashRegister.setBill1Qty(cashRegister.getBill1Qty() - amountOf1);
                System.out.println("Value: 1, quantity: " + amountOf1);
            }
            if(amountOf05 != 0) {
                change.setBill05Qty(amountOf05);
                cashRegister.setBill05Qty(cashRegister.getBill05Qty() - amountOf05);
                System.out.println("Value: 0.5, quantity: " + amountOf05);
            }
            if(amountOf01 != 0) {
                change.setBill01Qty(amountOf01);
                cashRegister.setBill01Qty(cashRegister.getBill01Qty() - amountOf01);
                System.out.println("Value: 0.1, quantity: " + amountOf01);
            }
            return change;
        } else {
            return null;
        }
    }

    @Override
    public void transaction(Scanner sc) {
        System.out.println("What would you like to buy? Type in the name of the desired product.");
        products.forEach(i -> System.out.print(i.getName() + "(price: " + i.getPrice() + ") "));
        System.out.println();
        String productSelected = sc.next();
        if (productSelected.equals("0")){
            System.out.println("Your transaction has been canceled");
            return;
        } else {
            ProductStorage selectedProduct = checkIfProductIsInStock(productSelected);
            if(selectedProduct == null){
                System.out.println("No such product found");
                return;
            }
            System.out.println("You are trying to buy " + selectedProduct.getName() + ". You need to pay " + selectedProduct.getPrice());
            System.out.println("Provide bill or coin (accepted values: 0.1, 0.5, 1, 2");
            double price = selectedProduct.getPrice();
            double cashSelected;
            double sumOfPay = 0;
            while (true){
                cashSelected = sc.nextDouble();
                if (!checkIfCashIsAccepted(cashSelected)){
                    break;
                }
                if(cashSelected == 2){
                    cashRegister.setBill2Qty(cashRegister.getBill2Qty() + 1);
                }
                if(cashSelected == 1){
                    cashRegister.setBill1Qty(cashRegister.getBill1Qty() + 1);
                }
                if(cashSelected == 0.5){
                    cashRegister.setBill05Qty(cashRegister.getBill05Qty() + 1);
                }
                if(cashSelected == 0.1){
                    cashRegister.setBill01Qty(cashRegister.getBill01Qty() + 1);
                }
                sumOfPay = DoubleRounder.round(sumOfPay + cashSelected, 1);
                if(price <= sumOfPay){
                    System.out.println("You have paid " + sumOfPay + " in total. Your change will be " + DoubleRounder.round(sumOfPay - price, 1));
                    giveChange(DoubleRounder.round(sumOfPay - price, 1));
                        if (checkIfProductIsInStock(productSelected) != null){
                            ProductStorage product = checkIfProductIsInStock(productSelected);
                            product.setQuantity(product.getQuantity() - 1);
                        }
                    break;
                }
                System.out.println("You have paid " + DoubleRounder.round(cashSelected, 1) + " in total. You still need to pay " + (DoubleRounder.round(price - sumOfPay, 1)));
            }
        }
    }

    @Override
    public int calcBillsAndCoin(double value, int quantity, double changeAmount) {
        int amountOfCoinOrBills = 0;
        for (int i = 1; i < quantity; i++) {
            if (DoubleRounder.round(changeAmount - value * i, 1) >= 0){
                amountOfCoinOrBills = i;
            } else {
                break;
            }
        }
        return amountOfCoinOrBills;
    }
}
