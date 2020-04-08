import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

public class NewTask {
	private final static String QUEUE_NAME = "hello";
	public static void main(String[] args) {
		try {
			String message = String.join(" ", argv);
			new NewTask().send(message);
		} catch(Exception e) {}
	}

	private void send(String message) throws Exception {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("localhost");
		
		try(Connection connection = connectionFactory.newConnection();Channel channel = connection.createChannel()) {
			channel.queueDeclare(QUEUE_NAME, false, false, false, null);
			channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
			System.out.println(" [x] Sent '" + message + "'");
		}
	}
}