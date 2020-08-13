package pkg_3;

class Tree {
	private String type;
	private double height; 
	Tree( String type, double height ){
		this.height = height;
		this.type = type;
	}
	String getType(){return type;}
	double getHeight(){return height;}

	public int hashCode(){
		int hc = 0;
		hc += 17 + (int)height;
		hc += type.hashCode(); 
		return hc;
	} 

	public boolean equals( Object obj ){
		if ( obj == null || !(obj instanceof Tree) ) return false;
		Tree other = (Tree)obj;
		if ( this.type == other.getType() ){
			if ( this.height == other.getHeight() ){ return true; }
		}
		return false;
	}
}
