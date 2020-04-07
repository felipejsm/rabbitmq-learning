package org.rabbit.send;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Sender {
    private static final String QUEUE_NAME = "hello";

    public void sendMessage(String message) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        try(Connection connection = connectionFactory.newConnection()) {
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME,false,false,false,null);
            channel.basicPublish("","hello-route",null,message.getBytes());
            System.out.println("[x] Sending message - ".concat(message));
        }
    }
}
