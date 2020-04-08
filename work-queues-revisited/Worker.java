import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

public class Worker {
	
	private final static String TASK_QUEUE_NAME = "hello";
	
	public static void main(String[] args) {
		try {
			new Worker().consume();
		} catch(Exception e) {}
		
	}
	
	private void consume() throws Exception {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("localhost");
		
		Connection connection = connectionFactory.newConnection();
		Channel channel = connection.createChannel();
		
		channel.queueDeclare(TASK_QUEUE_NAME, false, false, false, null);
		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
		DeliverCallback deliverCallback = (consumerTag, delivery) -> {
			try {
				String message = new String(delivery.getBody(), "UTF-8");
				System.out.println(" [x] Received '" + message + "'");
				
				doWork(message);
				
				
			} catch(Exception e) {
				
			} finally {
				System.out.println(" [x] Done");
			}
		};
		boolean autoAck = true;
		channel.basicConsume(TASK_QUEUE_NAME, autoAck, deliverCallback, consumerTag ->{});
	}
	
	private static void doWork(String task) throws InterruptedException {
    for (char ch: task.toCharArray()) {
        if (ch == '.') Thread.sleep(1000);
    }
}

}