package com.fxj.test;

import java.util.HashMap;
import java.util.Map;

public class Test {

    public static void main(String[] args) {
        Map map = new HashMap();
        int a = (map.get("abc")==null?0:(int)map.get("abc"))*1000;
        System.out.println(a);
    }

}
