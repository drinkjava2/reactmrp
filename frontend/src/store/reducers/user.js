import * as types from "../action-types";
//import { getToken } from "E:/reactmrp/frontend/src/utils/auth";
import * as my from "E:/reactmrp/frontend/src/myserverless/myserverless.js";

const initUserInfo = {
  name: "",
  role: "",
  avatar:"",
  token: my.getMyToken(),
};
export default function user(state = initUserInfo, action) {
  switch (action.type) {
    case types.USER_SET_USER_TOKEN:
      return {
        ...state,
        token: action.token
      };
    case types.USER_SET_USER_INFO:
      return {
        ...state,
        name: action.name,
        role: action.role,
        avatar: action.avatar,
        token:action.token,
      };
    case types.USER_RESET_USER:
      return {};
    default:
      return state;
  }
}
