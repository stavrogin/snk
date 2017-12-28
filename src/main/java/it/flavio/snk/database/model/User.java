package it.flavio.snk.database.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the "user" database table.
 * 
 */
@Entity
@Table(name = "USER")
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User implements Serializable {

	private static final long serialVersionUID = 1702461905410489333L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "user")
	private List<Message> messages;

	// bi-directional many-to-many association to User
	@ManyToMany
	@JoinTable(name = "follower_user", 
				joinColumns = {@JoinColumn(name = "follower_id", referencedColumnName = "user_id") }, 
				inverseJoinColumns = {@JoinColumn(name = "followed_id", referencedColumnName = "user_id") }
			)
	private List<User> followers;
	
	@ManyToMany(mappedBy = "followers")
	private List<User> followed;

	public User() {
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

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

	public List<User> getFollowers() {
		return this.followers;
	}

	public void setFollowers(List<User> followers) {
		this.followers = followers;
	}

	public List<User> getFollowed() {
		return this.followed;
	}

	public void setFollowed(List<User> followed) {
		this.followed = followed;
	}

}