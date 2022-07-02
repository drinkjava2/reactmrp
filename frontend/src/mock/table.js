import Mock from "mockjs";
import * as my from "@/myserverless/myserverless.js";

export default {
  tableList: (config) => {
     let result = my.syncData$java(`#public
            Map m=(Map) $1;
            Object[] sql=new Object[]{" from sample where 1=1 ",
                    noBlank(" and title like ?","%",m.get("title"),"%"),
                    notBlank(" and star=?", m.get("star")),
                    notBlank(" and status=?", m.get("status"))
                    };
            List items=DB.qryMapList("select * ",sql,  pagin((int)m.get("pageNumber"), (int)m.get("pageSize")));
            int total=DB.qryIntValue("select count(*) ",sql);
            m.clear();
            m.put("items", items);
            m.put("total", total);
            return m;
      `, JSON.parse( config.body) );
    return {
      code: 20000,
      data: result,
    };
  },
  
  
  deleteItem: (config) => {
      const { id } = JSON.parse(config.body);
      my.sync$executeSql(`#admin delete from sample where id=?`, id);
      return {
        code: 20000,
      };
    },
    
  
  
  editItem: (config) => {
    const data = JSON.parse(config.body);
    my.sync$java(`#admin
            Map m=(Map)$1;
            DB.exe("update sample set ",// 
              "star=", que(m.get("star")), //
              " , status=", que(m.get("status")), //
              " , title=", que(m.get("title")), //
              " , date=", que(m.get("date")),//
              " where id =", que(m.get("id"))
            ); 
            `, data);
    return {
      code: 20000,
    };
  },
};
