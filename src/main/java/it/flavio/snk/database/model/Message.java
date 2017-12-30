package it.flavio.snk.database.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the "message" database table.
 */
@Entity
@Table(name="MESSAGE")
@NamedQueries({
	@NamedQuery(name="Message.findAll", 
				query="SELECT m FROM Message m"),
	@NamedQuery(name="Message.findByUserName", 
				query="SELECT m FROM Message m WHERE m.user.name = :name"),
	@NamedQuery(name="Message.findByUserList", 
				query="SELECT m FROM Message m WHERE m.user.name IN :names")
}) 
public class Message implements Serializable {

	private static final long serialVersionUID = 9118581415180131961L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="MESSAGE_ID")
	private Integer messageId;

	@Column(name="INSERTTS")
	private Date insertts;

	@Column(name="MESSAGE")
	private String message;
	
	@ManyToOne
	@JoinColumn(name="USER_ID")
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

	
	public Integer getMessageId() {
		return this.messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}