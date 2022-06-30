import * as types from "../action-types";
import { reqUserInfo } from "@/api/user";
import * as my from "@/myserverless/myserverless.js";

export const getUserInfo = (token) => (dispatch) => {
    return new Promise((resolve) => {
      my.data$myServerless(`PublicBackend$GetUserInfo`)
        .then((userInfo) => { 
          if (userInfo) {
            dispatch(setUserInfo(userInfo));
          } else {
            dispatch(resetUser());
          } 
          resolve(); 
        }) 
    });
  };
 
// //这个是原版使用mock的getUserInfo方法  
//export const getUserInfo = (token) => (dispatch) => {
//  return new Promise((resolve, reject) => {
//    reqUserInfo(token)
//      .then((response) => {
//        const { data } = response;
//        if (data.status === 0) {
//          const userInfo = data.userInfo;
//          dispatch(setUserInfo(userInfo));
//          resolve(data);
//        } else {
//          const msg = data.message;
//          reject(msg);
//        }
//      })
//      .catch((error) => {
//        reject(error);
//      });
//  });
//};

export const setUserToken = (token) => {
  return {
    type: types.USER_SET_USER_TOKEN,
    token,
  };
};

export const setUserInfo = (userInfo) => {
  return {
    type: types.USER_SET_USER_INFO,
    ...userInfo,
  };
};

export const resetUser = () => {
  return {
    type: types.USER_RESET_USER,
  };
};
