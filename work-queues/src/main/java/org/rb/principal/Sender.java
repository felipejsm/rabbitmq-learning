package org.rb.principal.sender;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

public class Sender {

    static final String QUEUE_NAME = "hello";

    public static void main(String[] args) {
        try {
            new Sender().sendMessage(args[0]);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) throws Exception {
        message = String.join(" ", message);
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        try(Connection connection = connectionFactory.newConnection()) {
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        }
    }
}
