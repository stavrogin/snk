package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the AUSER database table.
 * 
 */
@Entity
@NamedQuery(name="Auser.findAll", query="SELECT a FROM Auser a")
public class Auser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="USER_ID")
	private long userId;

	private String name;

	//bi-directional many-to-many association to Auser
	@ManyToMany
	@JoinTable(
		name="AFOLLOWER_USER"
		, joinColumns={
			@JoinColumn(name="FOLLOWED_ID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="FOLLOWER_ID")
			}
		)
	private List<Auser> followers;

	//bi-directional many-to-many association to Auser
	@ManyToMany(mappedBy="followers")
	private List<Auser> followed;

	//bi-directional many-to-one association to Amessage
	@OneToMany(mappedBy="auser")
	private List<Amessage> amessages;

	public Auser() {
	}

	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Auser> getFollowers() {
		return this.followers;
	}

	public void setFollowers(List<Auser> followers) {
		this.followers = followers;
	}

	public List<Auser> getFollowed() {
		return this.followed;
	}

	public void setFollowed(List<Auser> followed) {
		this.followed = followed;
	}

	public List<Amessage> getAmessages() {
		return this.amessages;
	}

	public void setAmessages(List<Amessage> amessages) {
		this.amessages = amessages;
	}

	public Amessage addAmessage(Amessage amessage) {
		getAmessages().add(amessage);
		amessage.setAuser(this);

		return amessage;
	}

	public Amessage removeAmessage(Amessage amessage) {
		getAmessages().remove(amessage);
		amessage.setAuser(null);

		return amessage;
	}

}