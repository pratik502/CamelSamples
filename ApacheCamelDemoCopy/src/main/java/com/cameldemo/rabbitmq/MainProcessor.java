package com.cameldemo.rabbitmq;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.csv.CsvDataFormat;


public class MainProcessor {
    public void addRoutesToCamelContext(CamelContext context,String inPath,String outPath) throws Exception {

        context.addRoutes(new RouteBuilder() {
            public void configure() {
                try {
                    CsvDataFormat csvDataFormat = new CsvDataFormat();
                    csvDataFormat.setHeaderDisabled(true);

                    csvDataFormat.setDelimiter(',');
                            from(inPath).
                            unmarshal().
                            csv().
                            marshal(csvDataFormat).process(new MainProcessor.customProcessor()).
                            to(outPath)
                                    .end();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

   static class customProcessor implements Processor {

        public customProcessor(){
            System.out.println("Created 37");
        }


            @Override
            public void process(Exchange exchange) throws Exception {
                String finalContent="";
                System.out.println("11Inside processor");
                String originalFileContent = (String) exchange.getIn().getBody(String.class);
                String upperCaseFileContent = originalFileContent.toUpperCase();
                String [] lineArr=upperCaseFileContent.split("\n");

                for(String line:lineArr) {
                    System.out.println("Line is "+line);
                    String[] studLine=line.split(",");
                    System.out.println(studLine[0]+" "+studLine[1]+" "+studLine[2]+" "+studLine[3]+" "
                    +studLine[4]+" "+studLine[5]);
                    double percentage=(Double.parseDouble(studLine[2])+Double.parseDouble(studLine[3])+
                            Double.parseDouble(studLine[4]))/300.0*100;

                    finalContent=finalContent+line.replaceAll("\r", "")
                            +","+percentage+"\n";
                    System.out.println("ff "+finalContent);
                    System.out.println("============================");
                }

                exchange.getIn().setBody(finalContent);
            }
    }



}

  /*  for(String line:lineArr){
                    System.out.println("45 Line is "+line);
                    String[] studLine=line.split(",");
                    Student student=new Student(Integer.parseInt(studLine[0]),studLine[1],
                            Double.parseDouble(studLine[2]),Double.parseDouble(studLine[3]),
                            Double.parseDouble(studLine[4]),Integer.parseInt(studLine[5]));

                    finalContent=finalContent+line+","+student.getAndSetCalculatedPercentage()+"\n";
                    System.out.println("Cc "+finalContent);
                    System.out.println("============================");


                     System.out.println("Line arr size "+ lineArr.length);
                System.out.println(lineArr[0]);
                System.out.println(lineArr[1]);
                System.out.println("++++++++++++++");
                }*/