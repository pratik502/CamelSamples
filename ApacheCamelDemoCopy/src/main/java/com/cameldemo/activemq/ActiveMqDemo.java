package com.cameldemo.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

import javax.jms.ConnectionFactory;

public class ActiveMqDemo {

    public static void main(String[] args) throws Exception {
        CamelContext context=new DefaultCamelContext();
        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory();
        context.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
        context.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("file:/Users/pgadge/Documents/Project Notes/Apache Camel/inputbox?noop=true")
                      .to("activemq:queue:myqueue");
                        //to("rabbitmq://localhost:5672/myexchange?queue=myqueue&autoDelete=false")
            }
        });

        while (true)
            context.start();
    }
}
