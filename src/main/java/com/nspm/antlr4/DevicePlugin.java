package com.nspm.antlr4;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.springframework.util.ResourceUtils;

import java.io.InputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;



public class DevicePlugin {
    public String configText;
    public String filename;

    public DevicePlugin(String filename){
        this.filename=filename;
    }


    public void setConfigText(){
        StringBuffer sb = new StringBuffer();
        try{
            File file = ResourceUtils.getFile("classpath:"+this.filename);
            InputStream inputStream = new FileInputStream(file);

            InputStreamReader isReader = new InputStreamReader(inputStream);
            //Creating a BufferedReader object
            BufferedReader reader = new BufferedReader(isReader);

            String str;
            while((str = reader.readLine())!= null){
                sb.append(str);
            }
//            System.out.println(sb.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        this.configText = sb.toString();
    }

    public void parse(){
        CodePointCharStream input = CharStreams.fromString(this.configText);
        ABTLexer lexer=new ABTLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ABTParser parser=new ABTParser(tokens);
        String[] arr=parser.getTokenNames();
        for (String s: arr
             ) {
            System.out.println(s);
        }
    }

    public static void main(String[] args) {
        DevicePlugin devicePlugin=new DevicePlugin("conf/min.conf");
        devicePlugin.setConfigText();
        devicePlugin.parse();

    }
}
