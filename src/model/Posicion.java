package model;

public class Posicion {
	
	    private String msn;
	    private String type = "posición";

	    public Posicion (String msn){
	        this.msn = msn;
	       
	    }

	    public Posicion (){

	    }

	    public String getMsn() {
	        return msn;
	    }

	    public void setMsn(String msn) {
	        this.msn = msn;
	    }

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}
	    
	    

}
