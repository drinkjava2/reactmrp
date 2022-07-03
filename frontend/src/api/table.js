import request from '@/utils/request'
import * as my from "@/myserverless/myserverless.js"

export function tableList(query) { 
   return my.data$java(`#public
            Map m=(Map) $1;
            String star=""; 
            if(!MyStrUtils.isEmpty(m.get("star")))
                star=new String[]{"★", "★★", "★★★"}[Integer.valueOf(""+m.get("star"))-1];
            Object[] sql=new Object[]{" from sample where 1=1 ",
                    noBlank(" and title like ?","%",m.get("title"),"%"),
                    notBlank(" and star=?", star),
                    notBlank(" and status=?", m.get("status"))
                    };
            List items=DB.qryMapList("select * ",sql,  pagin((int)m.get("pageNumber"), (int)m.get("pageSize")));
            int total=DB.qryIntValue("select count(*) ",sql);
            m.clear();
            m.put("items", items);
            m.put("total", total);
            return m;
      `, query);
}

export function deleteItem(id) {
    return my.$executeSql(`#admin delete from sample where id=?`, id);
}
export function editItem(data) {
   return my.$java(`#admin
            Map m=(Map)$1;
            DB.exe("update sample set ",// 
              "star=", que(m.get("star")), //
              " , status=", que(m.get("status")), //
              " , title=", que(m.get("title")), //
              " , date=", que(m.get("date")),//
              " where id =", que(m.get("id"))
            ); 
            `, data);
}