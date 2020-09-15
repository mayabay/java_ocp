package util.crud;

import java.io.Serializable;

/**
 * Abstract base class for class AbstractCRUD .
 * 
 * GFN OCP Java course April 2020.
 * */
public abstract class AbstractEntity implements Serializable {
    
	private int id;

	/**
	 * Returns id of entity
	 * 
	 * @return unique identifier of entity
	 * */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier
     * 
     * @param new identifier for this entity 
     * */
    public void setId(int id) {
        this.id = id;
    }
}
