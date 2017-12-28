package it.flavio.snk.database.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the "message" database table.
 * 
 */
@Entity
@Table(name="MESSAGE")
@NamedQuery(name="Message.findAll", query="SELECT m FROM Message m")
public class Message implements Serializable {
	
	private static final long serialVersionUID = 9118581415180131961L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="message_id")
	private Integer messageId;
	
	@Column(name="message")
	private String message;
	
	@Column(name="insertts")
	private Date insertts;
	
	@ManyToOne
	private User user;

	public Message() {
	}

	public Date getInsertts() {
		return this.insertts;
	}

	public void setInsertts(Date insertts) {
		this.insertts = insertts;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
	public int getMessageId() {
		return this.messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}