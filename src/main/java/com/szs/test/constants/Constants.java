package com.szs.test.constants;

import java.util.HashMap;
import java.util.Map;

public class Constants {

    //서비스 가입 가능한 유저 Map
    public static final Map<String, String> userMap;

    static {
        userMap = new HashMap<>();
        userMap.put("동탁","921108-1582816");
        userMap.put("관우","681108-1582816");
        userMap.put("손권","890601-2455116");
        userMap.put("유비","790411-1656116");
        userMap.put("조조","810326-2715702");
    }

}
