package com.cameldemo.helloworld;

import org.apache.camel.builder.RouteBuilder;

public class HelloWorldRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        System.out.println("I like dominoes and Pizza Hut pizza");
    }
}
