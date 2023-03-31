public class Data<T> {
    private T naicsCode;
    private T zipCode;
    private T businessName;
    private T neighborhood;
    private T endDate;
    private T startDate;
    private T businessType;
    
    public Data(T _naicsCode, T _zipCode, T _businessName, T _neighborhood, T _endDate, T _startDate, T _businessType){
    	setNaicsCode(_naicsCode);
    	setZipCode(_zipCode);
    	setBusinessName(_businessName);
    	setNeighborhood(_neighborhood);
    	setEndDate(_endDate);
    	setStartDate(_startDate);
    	setBusinessType(_businessType);
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
    
    public T getEndDate() {
    	return endDate;
    }
    
    public void setEndDate(T data) {
    	this.endDate = data;
    }
    
    public T getStartDate() {
    	return startDate;
    }
    
    public void setStartDate(T data) {
    	this.startDate = data;
    }
    
    public T getBusinessType() {
    	return businessType;
    }
    
    public void setBusinessType(T data) {
    	this.businessType = data;
    }
}