package it.flavio.snk.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import it.flavio.snk.constants.Constants;
import it.flavio.snk.database.model.Message;
import it.flavio.snk.database.model.User;

public class DataServiceTest {
	
	private static final String MESSAGE_1 = "msg1";
	private static final String MESSAGE_2 = "msg2";
	private static final String MESSAGE_3 = "msg3";
	private static final String MESSAGE_4 = "msg4";
	private static final String MESSAGE_5 = "msg5";
	private static final String MESSAGE_6 = "msg6";
	
	private DataService dataService;
	private User testUser1;
	private User testUser2;
	private User testUser3;
	
	@Before
	public void setUp() {
		dataService = DataServiceFactory.getDataService(Constants.JPA_TEST_PERSISTENCE_UNIT_NAME);
		testUser1 = dataService.retrieveUser("testUser");
		testUser2 = dataService.retrieveUser("anotherUser");
		testUser3 = dataService.retrieveUser("anotherUser2");
		dataService.createMessage(testUser1.getName(), MESSAGE_1);
		dataService.createMessage(testUser1.getName(), MESSAGE_2);
		dataService.createMessage(testUser1.getName(), MESSAGE_3);
		dataService.createMessage(testUser2.getName(), MESSAGE_4);
		dataService.createMessage(testUser2.getName(), MESSAGE_5);
		dataService.createMessage(testUser3.getName(), MESSAGE_6);
	}
	
	@After
	public void tearDown() {
		dataService.deleteAllMessages();
		dataService.deleteAllUsers();
	}
	
	@Test
	public void testRetrieveUser() {
		User user1 = dataService.retrieveUser(testUser1.getName());
		assertNotNull(user1);
		assertEquals(user1, testUser1);
	}
	
	@Test
	public void testTimeline() {
		List<Message> user1Messages = dataService.getMessagesByUserName(testUser1.getName());
		assertTrue(user1Messages.size() == 3);
		
		List<Message> user2Messages = dataService.getMessagesByUserName(testUser2.getName());
		assertTrue(user2Messages.size() == 2);
		
		List<Message> user3Messages = dataService.getMessagesByUserName(testUser3.getName());
		assertTrue(user3Messages.size() == 1);
	}
	
	@Test
	public void testFollow() {
		List<User> followedUsers = dataService.getFollowedUsersByFollowerName(testUser1.getName());
		assertTrue(followedUsers.size() == 0);
		
		dataService.follow(testUser1.getName(), testUser2.getName());
		followedUsers = dataService.getFollowedUsersByFollowerName(testUser1.getName());
		assertTrue(followedUsers.size() == 1);
		
		List<User> userTest1Followers = dataService.getFollowersByUserName(testUser1.getName());
		assertTrue(userTest1Followers.size() == 0);
		
		List<User> userTest2Followers = dataService.getFollowersByUserName(testUser2.getName());
		assertTrue(userTest2Followers.size() == 1);
	}
	
	@Test
	public void testFollowedMessagesIncludingSelf() {
		dataService.follow(testUser1.getName(), testUser2.getName());
		List<Message> followedMessages = dataService.getAllFollowedUsersMessages(testUser1.getName());
		assertTrue(followedMessages.size() == 5);
		
		dataService.follow(testUser3.getName(), testUser2.getName());
		followedMessages = dataService.getAllFollowedUsersMessages(testUser3.getName());
		assertTrue(followedMessages.size() == 3);
		
		dataService.follow(testUser1.getName(), testUser3.getName());
		followedMessages = dataService.getAllFollowedUsersMessages(testUser1.getName());
		assertTrue(followedMessages.size() == 6);
	}

}
