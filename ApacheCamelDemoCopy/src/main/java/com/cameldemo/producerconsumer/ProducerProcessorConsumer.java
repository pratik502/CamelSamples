package com.cameldemo.producerconsumer;

import org.apache.camel.*;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class ProducerProcessorConsumer {

    public static void main(String[] args) throws Exception {
        CamelContext context=new DefaultCamelContext();

        context.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct:start")
                        .process(new Processor() {
                            @Override
                            public void process(Exchange exchange) throws Exception {
                                String originalFileContent = (String) exchange.getIn().getBody(String.class);
                                String upperCaseFileContent = originalFileContent.toUpperCase();
                                exchange.getIn().setBody(upperCaseFileContent);
                            }
                        })
                        .to("seda:end");
            }});

        context.start();
        ProducerTemplate producerTemplate=context.createProducerTemplate();
        producerTemplate.sendBody("direct:start","New launches : Indi tandoori paneer,Creamy Tomato Pasta,Moroccon Spice,Unthinkable");

        ConsumerTemplate consumerTemplate=context.createConsumerTemplate();
        String messageGot=consumerTemplate.receiveBody("seda:end",String.class);

        System.out.println("Message got\n"+messageGot);

    }
}

/*
In this we have added Processor that simply change message case to UpperCase
 */