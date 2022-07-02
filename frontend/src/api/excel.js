import request from '@/utils/request'
import * as my from "@/myserverless/myserverless.js"; 

export function excelList() {
  return my.data$qryMapList(`#public select * from Sample limit  ?`, 20); 
}