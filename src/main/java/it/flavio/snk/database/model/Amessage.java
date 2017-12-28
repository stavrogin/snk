package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the AMESSAGE database table.
 * 
 */
@Entity
@NamedQuery(name="Amessage.findAll", query="SELECT a FROM Amessage a")
public class Amessage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="MESSAGE_ID")
	private long messageId;

	private String insertts;

	@Column(name="\"MESSAGE\"")
	private String message;

	//bi-directional many-to-one association to Auser
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private Auser auser;

	public Amessage() {
	}

	public long getMessageId() {
		return this.messageId;
	}

	public void setMessageId(long messageId) {
		this.messageId = messageId;
	}

	public String getInsertts() {
		return this.insertts;
	}

	public void setInsertts(String insertts) {
		this.insertts = insertts;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Auser getAuser() {
		return this.auser;
	}

	public void setAuser(Auser auser) {
		this.auser = auser;
	}

}