import request from 'E:/reactmrp/frontend/src/utils/request'
export function excelList() {
  return request({
    url: '/excel/list',
    method: 'get'
  })
}