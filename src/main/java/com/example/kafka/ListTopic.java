package com.example.kafka;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;

public class ListTopic {

    public static void main(String[] args) {

        Properties props = new Config().build();

        try (AdminClient adminClient = AdminClient.create(props)) {
            ListTopicsResult topics = adminClient.listTopics();
            System.out.println("Topics: " + topics.names().get());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
