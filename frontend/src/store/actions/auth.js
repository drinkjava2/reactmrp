import { setUserToken, resetUser } from "./user";
import { reqLogin, reqLogout } from "E:/reactmrp/frontend/src/api/login";
import { setToken, removeToken } from "E:/reactmrp/frontend/src/utils/auth";
import * as my from "E:/reactmrp/frontend/src/myserverless/myserverless.js";

export const login = (username, password) => (dispatch) => {
  return new Promise((resolve, reject) => {
    reqLogin({ username: username.trim(), password: password })
      .then((response) => {
        const { data } = response;
        if (data.status === 0) {
          const token = data.token;
          dispatch(setUserToken(token));
          setToken(token);
          resolve(data);
        } else {
          const msg = data.message;
          reject(msg);
        }
      })
      .catch((error) => {
        reject(error);
      });
  });
};

export const logout = (token) => (dispatch) => {
    return new Promise((resolve, reject) => {
        my.data$myServerless(`PublicBackend$Logout`)
        .then((result) => { 
            localStorage.setItem("myToken", null);              
            dispatch(resetUser());  
        })
    });
  };

  
// //这是原版使用mock的logout  
//export const logout = (token) => (dispatch) => {
//  return new Promise((resolve, reject) => {
//    reqLogout(token)
//      .then((response) => {
//        const { data } = response;
//        if (data.status === 0) {
//          dispatch(resetUser());
//          removeToken();
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
