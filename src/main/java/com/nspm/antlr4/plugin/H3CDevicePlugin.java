package com.nspm.antlr4.plugin;

import com.nspm.antlr4.gen.H3CLexer;
import com.nspm.antlr4.gen.H3CParser;
import com.nspm.antlr4.h3c.device.impl.H3CVisitorOverall;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.springframework.util.ResourceUtils;

import java.io.InputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;


public class H3CDevicePlugin {
    public String configText;
    public String filename;

    public H3CDevicePlugin(String filename){
        this.filename=filename;
    }

    // file, to string, to Lexer
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
                sb.append(str+"\n");
//                System.out.println(sb.toString());
            }
//            System.out.println(sb.toString());
        }catch (Exception e){
            e.printStackTrace();
        }

        this.configText = sb.toString();
//        this.configText.substring(0,this.configText.length()-1);
    }

    public void parse(){
        CodePointCharStream input = CharStreams.fromString(this.configText);
        H3CLexer lexer=new H3CLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        H3CParser parser=new H3CParser(tokens);
        H3CVisitorOverall h3CVisitorOverall= new H3CVisitorOverall();
        String ss= h3CVisitorOverall.createDeviceData(parser);
//        GenericDevicePO myResult =new GenericDevicePO();
//        String[] arr=parser.getTokenNames();
//        for (String s: arr
//             ) {
//            System.out.println(s);
//        }
//        System.out.println(ss);
    }

    public static void main(String[] args) {
        H3CDevicePlugin devicePlugin=new H3CDevicePlugin("conf/min.conf");
        devicePlugin.setConfigText();
        devicePlugin.parse();

    }
}
