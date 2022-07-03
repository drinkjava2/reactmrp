import request from "@/utils/request";

export function tracker(data) {
    return {
        status: 1,
        message: "monitor",
      };
//  return request({
//    url: "/monitor",
//    method: "post",
//    data,
//  });
}
