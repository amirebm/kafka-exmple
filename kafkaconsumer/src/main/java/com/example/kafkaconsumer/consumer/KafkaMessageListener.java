package com.example.kafkaconsumer.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service

public class KafkaMessageListener {

    @KafkaListener(topics = "Amir-topic3",groupId = "Amir-GroupId-1")
    public void consume(String message){
        System.out.println("Consumer consumes the message "+message);
    }
}
