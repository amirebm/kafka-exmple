package com.example.springboot_kafka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaMessagePublisher {

    @Autowired
    private KafkaTemplate<String,Object> template;

    public void  sendMessageToTopic(String message){

      CompletableFuture<SendResult<String,Object>> future= template.send("Amir-Topic",message);
      //*****future.get();*****//
//        you can do simply you can do future do get API call  (the above command) from the completable future
//        then the thread will wait for the results to come but it will definitely slow down the producer
//        as you know Capka is a fast in processing platform.
//
//        Therefore it's better to handle the results asynchronously so that the subsequent message does not wait
//        for the result of the previous messages.
//
//        Okay, now how we can do that? We can do this using a callback implementation. I'll show you how we can
//        do that. Let's remove this piece of code. We'll simply tell this future object: future when complete,
//        when the future will be complete, then I will use the Lambda. It will give me the result and also
//        it will give me the parameter of exception.
//
//        Now I can simply print whatever the things I need. So just add a comma here, and I'll just add some print statements.
//
      future.whenComplete((result,ex)->{
          if(ex==null){
              System.out.println("Sent Message=["+message+
          "] with offset=["+result.getRecordMetadata().offset()+ "]");
          }
          else {
              System.out.println("Unable to send Message=["+
                      message+"] due to :"+ex.getMessage());
          }
      });
        }
}
