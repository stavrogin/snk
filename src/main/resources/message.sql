CREATE TABLE IF NOT EXISTS message (
	message_id INTEGER PRIMARY KEY,
	user_id INTEGER NOT NULL,
	message TEXT NOT NULL,
	insertts TEXT NOT NULL,
	FOREIGN KEY(user_id) REFERENCES user(id)
);