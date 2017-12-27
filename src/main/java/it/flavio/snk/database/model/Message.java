package it.flavio.snk.database.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "message" database table.
 * 
 */
@Entity
@Table(name="\"message\"")
@NamedQuery(name="Message.findAll", query="SELECT m FROM Message m")
public class Message implements Serializable {
	private static final long serialVersionUID = 1L;
	private String insertts;
	private String message;
	private int messageId;
	private User user;

	public Message() {
	}


	@Column(name="\"insertts\"")
	public String getInsertts() {
		return this.insertts;
	}

	public void setInsertts(String insertts) {
		this.insertts = insertts;
	}


	@Column(name="\"message\"")
	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Id
	@Column(name="\"message_id\"")
	public int getMessageId() {
		return this.messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}


	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		})
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}