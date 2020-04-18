import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Channel;

public class EmitLogTopic  {
	public static final String EXCHANGE_NAME = "topic_logs";
	
	public static void main(String[] argv) throws Exception {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("localhost");
		
		try(Connection connection = connectionFactory.newConnection();
				Channel channel = connection.createChannel()) {
					channel.exchangeDeclare(EXCHANGE_NAME, "topic");
					
					String routingKey = argv[0];//getRouting(argv);
					String message = argv[1];//getMessage(argv);
					
					channel.basicPublish(EXCHANGE_NAME, routingKey, null,message.getBytes("UTF-8"));
					System.out.println(" [x] Sent '" + routingKey + "':'" + message + "'");
					
				}
	}
	
	private static 	String getRouting(String[] strings) {
		if(strings.length == 0) {
			return "rap.mainstream.logic";//music style.sub-genre.artist/band
		}
		return strings[0];
	}
	
	private static String getMessage(String[] strings) {
        if (strings.length < 2)
            return "You're watching a master @ work!";
        return joinStrings(strings, " ", 1);
    }
	
	private static String joinStrings(String[] strings, String delimiter, int startIndex) {
        int length = strings.length;
        if (length == 0) return "";
        if (length <= startIndex) return "";
        StringBuilder words = new StringBuilder(strings[startIndex]);
        for (int i = startIndex + 1; i < length; i++) {
            words.append(delimiter).append(strings[i]);
        }
        return words.toString();
    }
}



