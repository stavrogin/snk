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
@NamedQueries({
	@NamedQuery(name = "User.findAll", 
				query = "SELECT u FROM User u"),
	@NamedQuery(name = "User.findFollowedByFollowerName", 
				query = "SELECT u FROM User u JOIN u.followers f WHERE f.userId = :userId"),
	@NamedQuery(name = "User.findFollowersByUserName", 
				query = "SELECT u FROM User u JOIN u.followed f WHERE f.userId = :userId"),
	@NamedQuery(name = "User.findUsersByName", 
				query = "SELECT u FROM User u WHERE u.name = :name")
})
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

	//bi-directional many-to-many association to User
	@ManyToMany
	@JoinTable(
		name="FOLLOWER_USER"
		, joinColumns={
			@JoinColumn(name="FOLLOWED_ID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="FOLLOWER_ID")
			}
		)
	private List<User> followers;

	//bi-directional many-to-many association to User
	@ManyToMany(mappedBy="followers")
	private List<User> followed;

	public User() {
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
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