import * as types from "../action-types";
import { reqUserInfo } from "E:/react-mrp/frontend/src/api/user";

export const getUserInfo = (token) => (dispatch) => {
  console.log("Debug_getUserInfo1:"+JSON.stringify(token));
  return new Promise((resolve, reject) => {
 console.log("Debug_getUserInfo2:"+JSON.stringify(token));
    reqUserInfo(token)
      .then((response) => {
        const { data } = response;
        if (data.status === 0) {
          const userInfo = data.userInfo;
          dispatch(setUserInfo(userInfo));
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
