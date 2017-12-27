CREATE TABLE IF NOT EXISTS follower_user (
	follower_id INTEGER NOT NULL,
	followed_id INTEGER NOT NULL,
	FOREIGN KEY(follower_id) REFERENCES user(user_id),
	FOREIGN KEY(followed_id) REFERENCES user(user_id),
	PRIMARY KEY(follower_id, followed_id)
);