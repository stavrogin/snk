package it.flavio.snk.console;

import it.flavio.snk.database.model.Message;
import it.flavio.snk.database.model.User;

public class ConsoleImpl implements OutputWriter {

	@Override
	public void write(String message) {
		System.out.println(message);
	}
	
	@Override
	public void writeWallMessage(Message message) {
		String text = message.getMessage();
		String timeGap = OutputUtils.getTimeGapAsString(message);
		StringBuilder sb = new StringBuilder();
		sb.append(message.getUser().getName()).append(" - ").append(text).append(" ").append(timeGap);
		write(sb.toString());
	}
	
	@Override
	public void writeTimelineMessage(Message message) {
		String text = message.getMessage();
		String timeGap = OutputUtils.getTimeGapAsString(message);
		StringBuilder sb = new StringBuilder();
		sb.append(text).append(" ").append(timeGap);
		write(sb.toString());
	}

	@Override
	public void writeFriend(User user) {
		write(user.getName());
	}

}
