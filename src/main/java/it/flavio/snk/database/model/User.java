package it.flavio.snk.database.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the "user" database table.
 * 
 */
@Entity
@Table(name="\"user\"")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private int userId;
	private List<Message> messages;
	private List<User> users1;
	private List<User> users2;

	public User() {
	}


	@Column(name="\"name\"")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Id
	@Column(name="\"user_id\"")
	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}


	//bi-directional many-to-one association to Message
	@OneToMany(mappedBy="user")
	public List<Message> getMessages() {
		return this.messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public Message addMessage(Message message) {
		getMessages().add(message);
		message.setUser(this);

		return message;
	}

	public Message removeMessage(Message message) {
		getMessages().remove(message);
		message.setUser(null);

		return message;
	}


	//bi-directional many-to-many association to User
	@ManyToMany
	@JoinTable(
		name="follower_user"
		, joinColumns={
				@JoinColumn(name="follower_id", referencedColumnName="user_id")
			}
		, inverseJoinColumns={
				@JoinColumn(name="followed_id", referencedColumnName="user_id")
			}
		)
	public List<User> getUsers1() {
		return this.users1;
	}

	public void setUsers1(List<User> users1) {
		this.users1 = users1;
	}


	//bi-directional many-to-many association to User
	@ManyToMany(mappedBy="users1")
	public List<User> getUsers2() {
		return this.users2;
	}

	public void setUsers2(List<User> users2) {
		this.users2 = users2;
	}

}