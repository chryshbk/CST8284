/* File: Tuna.java
 * Author: Stanley Pieda
 * Modified by: Chrystian Santos
 * Modified: November 2018
 * Date: August 2018
 * Description: Tuna generator class
 */
package datatransfer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;


/**
 * Transfer object for Tuna data
 * @author Stanley Pieda
 * Modified by: Chrystian Santos
 */
@Entity
@Table(name = "Tunas")
public class Tuna implements Serializable{
	/** Explicit serialVersionUID to avoid generating one automatically */
	private static final long serialVersionUID = 1L;
	
	/** ID value for database */
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        @Column(name = "id")
	private Integer id;
	
	/** recordNumber for database, originally matched a dataset file line number */
        @Column(name = "recordNumber")
	private int recordNumber;
	
	/** omega field */
        @Column(name = "omega", length = 42)
	private String omega;
	
	/** delta field */
        @Column(name = "delta", length = 42)
	private String delta;
	
	/** theta field */
        @Column(name = "theta", length = 42)
	private String theta;
	
	/** uuid field, contains UUID as String */
        @Column(name = "uuid", length = 42)
	private String uuid;
	
	/** see lab hand-out notes from assignment 1 */
        @Transient
        @Column(name = "isLastTuna")
	private boolean isLastTuna;
	
	/**
	 * Default constructor, sets id and recordNumber to zero, omega, delta, theta, and uuid to empty Strings
	 * @author Stanley Pieda
	 */
	public Tuna() {
		this(0,0,"","","","");
	}
	
	/**
	 * Telescoping constructor.
	 * @param id The id as Integer
	 * @param recordNumber The recordNumber as int
	 * @param omega The omega as String
	 * @param delta The delta as String
	 * @param theta The theta as String
	 * @param uuid The UUID as String
	 * @author Stanley Pieda
	 */
	public Tuna(Integer id, int recordNumber, String omega, String delta, String theta, String uuid) {
		this.id = id;
		this.recordNumber = recordNumber;
		this.omega = omega;
		this.delta = delta;
		this.theta = theta;
		this.uuid = uuid;
	}
	
	/**
         * @return id
         */
	public Integer getId() {
		return id;
	}
	
        /**
         * @param id the id to set
         */
	public void setId(Integer id) {
		this.id = id;
	}
	
        /**
         * @return recordNumber
         */
	public int getRecordNumber() {
		return recordNumber;
	}
	
        /**
         * @param recordNumber the record number to set
         */
	public void setRecordNumber(int recordNumber) {
		this.recordNumber = recordNumber;
	}
	
        /**
         * @return omega
         */
	public String getOmega() {
		return omega;
	}
	
        /**
         * @param omega the omega to set
         */
	public void setOmega(String omega) {
		this.omega = omega;
	}
	
        /**
         * @return delta
         */
	public String getDelta() {
		return delta;
	}
	
        /**
         * @param delta the delta to set
         */
	public void setDelta(String delta) {
		this.delta = delta;
	}
	
        /**
         * @return theta
         */
	public String getTheta() {
		return theta;
	}
	
        /**
         * @param theta the theta to set
         */
	public void setTheta(String theta) {
		this.theta = theta;
	}
	
        /**
         * @return uuid
         */
	public String getUUID() {
		return uuid;
	}
	
        /**
         * @param uuid the uuid to set
         */
	public void setUUID(String uuid) {
		this.uuid = uuid;
	}
	
        /**
         * @return isLastTuna
         */
	public boolean isLastTuna() {
		return isLastTuna;
	}
	
        /**
         * @param isLastTuna the isLastTuna to set
         */
	public void setLastTuna(boolean isLastTuna) {
		this.isLastTuna = isLastTuna;
	}
	
	/**
         * Overridden method toString.
         * @return Tuna Values
         */
	@Override
	public String toString() {
		return String.format("%d, %d, %s, %s, %s, %s", id, recordNumber, omega, delta, theta, uuid);
	}
}
