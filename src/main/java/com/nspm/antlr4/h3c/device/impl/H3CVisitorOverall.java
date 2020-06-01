package com.nspm.antlr4.h3c.device.impl;


import com.nspm.antlr4.gen.H3CParser;

public class H3CVisitorOverall extends H3CVisitorBasic {
    public H3CVisitorOverall() {
        //TODO automatically method stub
        super();
    }
    public String createDeviceData(H3CParser parser){
        H3CParser.ConfigContext ctx = parser.config();
        System.out.println("END CONFIG");
//        visitConfig(ctx);
        visit(ctx);

//        this.visitVersion(parser.version());
//        System.out.println("END VERSION");

//        System.out.println(ctx.getText());


//        return JsonUtil.objToJson(device);
//        System.out.println(device.getOsVersion());
//        System.out.println(JsonUtil.objToJson(device));
        return device.getOsVersion();
    }

}
