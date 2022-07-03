package com.gitee.drinkjava2.reactmrp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

@SuppressWarnings("all")
public class Temp {//临时类，用于调试前端小片段

    public static void main(String[] args) {
        List l = new ArrayList();
        for (int i = 0; i < 13; i++) {
            Map m = new HashMap<>();
            m.put("key", i);
            m.put("order_no", UUID.randomUUID().toString());
            m.put("price", (float) Math.round(10000 * new Random().nextFloat()) / 100);
            m.put("tag", new String[]{"pending", "success"}[new Random().nextInt(2)]);
            l.add(m);
        }
    }

}
