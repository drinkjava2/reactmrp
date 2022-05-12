import request from "E:/react-mrp/frontend/src/utils/request";

export function tracker(data) {
  return request({
    url: "/monitor",
    method: "post",
    data,
  });
}
