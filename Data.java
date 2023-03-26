public class Data<T> {
    private T naicsCode;
    private T zipCode;
    private T businessName;
    private T neighborhood;

    public T getNaicsCode() {
        return naicsCode;
    }

    public void setNaicsCode(T data) {
        this.naicsCode = data;
    }
    
    public T getZipCode() {
        return zipCode;
    }

    public void setZipCode(T data) {
        this.zipCode = data;
    }
    
    public T getBusinessName() {
        return businessName;
    }

    public void setBusinessName(T data) {
        this.businessName = data;
    }
    
    public T getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(T data) {
        this.neighborhood = data;
    }
}