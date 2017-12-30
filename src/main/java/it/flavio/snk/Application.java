package it.flavio.snk;

import java.util.List;
import java.util.Scanner;

import it.flavio.snk.command.Command;
import it.flavio.snk.command.CommandFactory;
import it.flavio.snk.database.model.Message;
import it.flavio.snk.service.DataService;
import it.flavio.snk.service.DataServiceImpl;
import it.flavio.snk.utils.ConsoleUtils;

public class Application {

	private DataService dataService;
	
	public void setDataService(DataService dataService) {
		this.dataService = dataService;
	}
	
	public void console() {
//		http://www.javapractices.com/topic/TopicAction.do?Id=79
//		https://stackoverflow.com/questions/4203646/system-console-returns-null
//		https://stackoverflow.com/questions/26470972/trying-to-read-from-the-console-in-java/26473083#26473083
//		https://stackoverflow.com/questions/5032356/using-scanner-nextline
//		https://stackoverflow.com/questions/4378824/adding-in-clause-list-to-a-jpa-query
		System.out.println("Please insert a command");
		Scanner scanner = new Scanner(System.in);
		String input;
		
		do {
			input = scanner.nextLine();
			System.out.println("Comando: " + input);
			
			Command cmd = CommandFactory.getCommand(input, dataService);
			if (cmd != null) {
				cmd.execute();
			} else {
				ConsoleUtils.write("Command not recognized: skipping");
			}
//			CommandFactory.getCommand("flavio -> azzo vuoi ciccio", dataService);
//			CommandFactory.getCommand("flavio", dataService);
//			CommandFactory.getCommand("flavio follows giulia", dataService);
//			CommandFactory.getCommand("giulia wall", dataService);
			
		} while (!"exit".equals(input));
		scanner.close();

	}

	public void testDB() {
		DataServiceImpl ds = new DataServiceImpl();
		ds.retrieveUser("flavio");
		ds.retrieveUser("valentina");
		ds.retrieveUser("giorgia");
		ds.retrieveUser("giulia");
//		System.out.println(ds.getUserByName("flavio"));
//		System.out.println(ds.getUserByName("flavio2"));
		
		System.out.println(ds.getUserMessages("flavio").size());

		ds.follow("flavio", "valentina");
		ds.follow("valentina", "flavio");
		ds.follow("flavio", "giorgia");
		
		ds.follow("giulia", "valentina");
		ds.follow("giulia", "giorgia");
		
		System.out.println("flavio follows:");
		ds.getFollowedUsersByFollowerName("flavio").forEach(u -> System.out.println(u.getName()));
		System.out.println("******************");
		System.out.println("flavio is followed by:");
		ds.getFollowersByUserName("flavio").forEach(u -> System.out.println(u.getName()));
		System.out.println("******************");

		System.out.println("giulia follows:");
		ds.getFollowedUsersByFollowerName("giulia").forEach(u -> System.out.println(u.getName()));
		System.out.println("******************");
		System.out.println("giulia is followed by:");
		ds.getFollowersByUserName("giulia").forEach(u -> System.out.println(u.getName()));
		System.out.println("******************");
		
		System.out.println("flavio follows:");
		ds.getFollowedUsersByFollowerName("flavio").forEach(u -> System.out.println(u.getName()));
		System.out.println("******************");
		System.out.println("flavio is followed by:");
		ds.getFollowersByUserName("flavio").forEach(u -> System.out.println(u.getName()));
		System.out.println("******************");

		//ds.createMessage("valentina", "luv u");
		List<Message> messages = ds.getMessagesByUserName("flavio");
		List<Message> messages2 = ds.getMessagesByUserName("valentina");
		messages.forEach(m -> System.out.println(m.getMessageId() + m.getUser().getName() + m.getMessage() + m.getInsertts()));
		messages2.forEach(m -> System.out.println(m.getMessageId() + m.getUser().getName() + m.getMessage() + m.getInsertts()));
		
		System.out.println("***************** WALL ******************");
		List<Message> wall = ds.getAllFollowedUsersMessages("flavio");
		wall.forEach(m -> System.out.println(m.getMessageId() + m.getUser().getName() + m.getMessage() + m.getInsertts()));
		
		
//		ds.getAllUsers();
//		ds.getAllFollowers();
//		ds.getAllFollowed();
	}
}
