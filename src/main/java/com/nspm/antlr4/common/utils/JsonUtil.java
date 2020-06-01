package com.nspm.antlr4.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nspm.antlr4.common.domain.obj.DevicePO;

import java.util.List;


public class JsonUtil {
    public static String objToJson(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
//        System.out.println(obj.getClass());
        try{
            String json = mapper.writeValueAsString(obj);
            return json;
        }catch(Exception e){
            e.printStackTrace();

        }

//        ObjectMapper mapper = new ObjectMapper();
//        try{
////            for (String s: arr
////             ) {
////            System.out.println(s);
////        }
//            String jsonlist = mapper.writeValueAsString(lst);
//            System.out.println(jsonlist);
//            return jsonlist;
//        }catch(Exception e){
//            e.printStackTrace();
//
//        }
        return "";

    }
}
