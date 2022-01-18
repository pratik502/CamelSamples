package com.cameldemo.rabbitmq;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RabbitMqObjectSend extends RouteBuilder{

    private Logger logger = LoggerFactory.getLogger(RabbitMqObjectSend.class);
    String inPath="file:/Users/pgadge/Documents/Project Notes/Apache Camel/inputbox?noop=true";
    String outPath="file:/Users/pgadge/Documents/Project Notes/Apache Camel/outbox";
    String outRabbitMqPath="rabbitmq:localhost:5672/myexchange?username=guest&password=guest&autoDelete=false&queue=myqueue";


    //rabbitmq://{{rabbitmq.url}}/{{rabbitmq.header.excahnge}}?exchangeType=headers&autoDelete=false&exchangePattern=InOnly
    @Override
    public void configure() throws Exception {
        try {
            CamelContext context = new DefaultCamelContext();
            MainProcessor route = new MainProcessor();
            route.addRoutesToCamelContext(context,inPath,outRabbitMqPath);
            while(true)
                context.start();
            //Thread.sleep(2000);
            //context.stop();
        } catch (Exception exe) {
            logger.info(exe.getMessage());
            exe.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        RabbitMqObjectSend obj=new RabbitMqObjectSend();
        obj.configure();

    }
}

/*
//String outRabbitMqPath="rabbitmq://localhost:5672/amq.direct?exchangeType=direct&autoDelete=false&exchangePattern=InOnly";
    //String outRabbitMqPath="rabbitmq:myexchange?queue=myqueue&autoDelete=false";
 */