public class ProductStorage extends Product{
    private int quantity;

    public ProductStorage(String productName, double productPrice, int productQuantity) {
        super(productName, productPrice);
        quantity = productQuantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return getName() + " quantity: " + quantity;
    }
}
