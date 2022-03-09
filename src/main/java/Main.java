public class Main {
    public static void main(String[] args) {
        SupermarketServiceImpl supermarket = SupermarketServiceImpl.getInstance();
        supermarket.menu();
    }
}
