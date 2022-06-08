import request from "E:/reactmrp/frontend/src/utils/request";

export function tracker(data) {
  return request({
    url: "/monitor",
    method: "post",
    data,
  });
}
