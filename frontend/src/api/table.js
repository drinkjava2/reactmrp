import request from '@/utils/request'
import * as my from "@/myserverless/myserverless.js"

export function tableList2(data) {
  console.log("list", data);  
  return request({
    url: '/table/list',
    method: 'post',
    data
  })
}


export function tableList(query) { 
   return my.data$java(`#public
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
      `, query);
}

export function deleteItem(data) {
    console.log("delete", data);
  return request({
    url: '/table/delete',
    method: 'post',
    data
  })
}
export function editItem(data) {
    console.log("edit", data);
  return request({
    url: '/table/edit',
    method: 'post',
    data
  })
}