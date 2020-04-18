import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

public class ReceiveLogsTopic  {
	public static final String EXCHANGE_NAME ="topic_logs";
	
	public static void main(String[] argv) throws Exception {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("localhost");
		
		Connection connection = connectionFactory.newConnection();
		Channel channel = connection.createChannel();
		
		channel.exchangeDeclare(EXCHANGE_NAME, "topic");
		
		String queueName = channel.queueDeclare().getQueue();
		
		if (argv.length < 1) {
			System.err.println("Usage: ReceiveLogsTopic [binding_key]...");
			System.exit(1);
		}
		 System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
		 
		 DeliverCallback deliverCallback = (consumerTag, delivery) -> {
			 String msg = new String(delivery.getBody(),"UTF-8");
			 System.out.println(" [x] Received '" +
			  delivery.getEnvelope().getRoutingKey() + "':'" + msg + "'");
			 
		 };
		 channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {});
		
	}
}