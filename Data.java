public class Data<T> {
    private T naicsCode;
    private T zipCode;
    private T businessName;
    private T neighborhood;
    
    public Data(T _naicsCode, T _zipCode, T _businessName, T _neighborhood){
    	setNaicsCode(_naicsCode);
    	setZipCode(_zipCode);
    	setBusinessName(_businessName);
    	setNeighborhood(_neighborhood);
    }

    public boolean naicsCodeInRange(String _naicsCode) {
    	if (naicsCode == "")
    		return false;
    	String[] codeRange = ((String) naicsCode).split("-");
    	if (codeRange.length > 2)
    		return false;
		int naicsCodeLowerBound = Integer.parseInt(codeRange[0]);
		int naicsCodeUpperBound = Integer.parseInt(codeRange[1]);
		int naicsTarget = Integer.parseInt(_naicsCode);
		return naicsTarget >= naicsCodeLowerBound && naicsTarget <= naicsCodeUpperBound;
    }
    	
    public T getNaicsCode() {
        return naicsCode;
    }

    public void setNaicsCode(T data) {
        this.naicsCode = data;
    }
    
    public String getZipCode() {
        return (String) zipCode;
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