/* File: Tuna.java
 * Author: Stanley Pieda
 * Date: August, 2018
 * Description: Simple data transfer object.
 */

package assignment.pkg1;

public class Tuna {
	private int id;
	private int recordNumber;
	private String omega;
	private String delta;
	private String theta;
	private String uuid;
	private boolean isLastTuna;
	
	public Tuna() {
		this(0,0,"","","", "");
	}
	
	public Tuna(int id, int recordNumber, String omega, String delta, String theta, String uuid) {
		this.id = id;
		this.recordNumber = recordNumber;
		this.omega = omega;
		this.delta = delta;
                this.theta = theta;
		this.uuid = uuid;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRecordNumber() {
		return recordNumber;
	}
	public void setRecordNumber(int recordNumber) {
		this.recordNumber = recordNumber;
	}
	public String getOmega() {
		return omega;
	}
	public void setOmega(String omega) {
		this.omega = omega;
	}
	public String getDelta() {
		return delta;
	}
	public void setDelta(String lambda) {
		this.delta = lambda;
	}
	public String getTheta() {
		return theta;
	}
	public void setTheta(String theta) {
		this.theta = theta;
	}
	public String getUUID() {
		return uuid;
	}
	public void setUUID(String uuid) {
		this.uuid = uuid;
	}
	public boolean isLastTuna() {
		return isLastTuna;
	}
	public void setLastTuna(boolean isLastTuna) {
		this.isLastTuna = isLastTuna;
	}
}
