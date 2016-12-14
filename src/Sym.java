/**
 * @author kenny
 * @version 12/12/16
 * This is the sym class
 * The purpose of it is to create symbols with a given type 
 */

public class Sym {
	private String type;
	
	/**
	 * Inititializes a symbol
	 * @param type
	 */
	
	public Sym(String type)
	{
		this.type = type;
	}
	
	/**
	 * Returns a symbols type
	 * @return type
	 */
	public String getType()
	{
		return this.type;
	}
	
	/**
	 * Returns the symbol's information
	 * Which will be used to "visualize" the symbool
	 */
	public String toString()
	{
	    return this.type;	
	}
}
