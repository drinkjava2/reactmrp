import request from '@/utils/request'
export function tableList(data) {
  console.log("list", data);  
  return request({
    url: '/table/list',
    method: 'post',
    data
  })
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