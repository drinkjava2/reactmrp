import request from 'E:/reactmrp/frontend/src/utils/request'
export function transactionList() {
  return request({
    url: '/transaction/list',
    method: 'get'
  })
}