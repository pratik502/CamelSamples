package com.cameldemo.filecopy;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class FileCopyMain {
    public static void main(String[] args) throws Exception {
        CamelContext context=new DefaultCamelContext();

        context.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("file:/Users/pgadge/Documents/Project Notes/Apache Camel/inputbox?noop=true")
                        .to("file:/Users/pgadge/Documents/Project Notes/Apache Camel/outputbox");
            }});

            while(true)
                context.start();
        }


    }

/*
In above example as soon as you copy/create file in inputBox1,it will be copied to outputbox
 */
