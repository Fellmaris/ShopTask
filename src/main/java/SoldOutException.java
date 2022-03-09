public class SoldOutException extends Exception{
    public String toString(){
        return ("We are sorry, but the product you are trying to buy is sold out");
    }
}
