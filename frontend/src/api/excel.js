import request from '@/utils/request'
import * as my from "@/myserverless/myserverless.js"; 

export function excelList() {
  return my.data$java(`#public
        import java.text.SimpleDateFormat;
        import java.util.*;
        import com.github.javafaker.Faker;
        Faker f = new Faker(new Locale("zh-CN")); 
        List l=new ArrayList();
        for (int i = 0; i < 20; i++) { 
            Map m=new HashMap();
            m.put("id",  i);
            m.put("title",  f.book().title());
            m.put("author",  f.book().author());
            m.put("readings",  f.number().numberBetween(0, 1000));
            m.put("date",  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(f.date().birthday()));
            l.add(m);
        }
        return l;  
  `); 
}