package org.rabbit.main;

import com.sun.org.apache.xpath.internal.operations.String;
import org.rabbit.consume.Consumer;
import org.rabbit.send.Sender;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeoutException;

public class Principal {
    public static void main(String[] args) {
        System.out.println("[x] Main Class");
        Sender sender = new Sender();
        Consumer consumer = new Consumer();
        Arrays.asList("1", "2","3","4").forEach(l -> {
            try {
                sender.sendMessage("Everybody dies ".concat(l));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        });
        try {
            consumer.consumeMessage();
        } catch(IOException | TimeoutException e) {
            e.printStackTrace();
        }

    }
}
