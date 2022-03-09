public class PayNotAcceptedException extends Exception{
    public String toString(){
        return ("We are sorry, but the money you are paying with is non-acceptable");
    }
}
