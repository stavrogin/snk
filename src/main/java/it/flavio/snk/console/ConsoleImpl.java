package it.flavio.snk.console;

import it.flavio.snk.database.model.Message;

public class ConsoleImpl implements Console {

	@Override
	public void write(String message) {
		System.out.println(message);
	}
	
	@Override
	public void writeWallMessage(Message message) {
		String text = message.getMessage();
		String timeGap = ConsoleUtils.getTimeGapAsString(message);
		StringBuilder sb = new StringBuilder();
		sb.append(message.getUser().getName()).append(" - ").append(text).append(" ").append(timeGap);
		write(sb.toString());
	}
	
	@Override
	public void writeTimelineMessage(Message message) {
		String text = message.getMessage();
		String timeGap = ConsoleUtils.getTimeGapAsString(message);
		StringBuilder sb = new StringBuilder();
		sb.append(text).append(" ").append(timeGap);
		write(sb.toString());
	}

}
