import React from "react";
import { HashRouter, Route, Switch, Redirect } from "react-router-dom";
import { useSelector, useDispatch, useStore } from "react-redux";
import { getUserInfo } from "E:/reactmrp/frontend/src/store/actions";
import Layout from "E:/reactmrp/frontend/src/views/layout";
import Login from "E:/reactmrp/frontend/src/views/login";

const Router =()=> { //原版是class，这里改成Hooks写法
	 const store=useStore();
	 console.log("store in router", store.getState());
	  
    const user = useSelector(state => state.user);
    const token=user.token;
    const role = user.role; 
    const dispatch=useDispatch(); 
    return (
      <HashRouter>
        <Switch>
          <Route exact path="/login" component={Login} />
          <Route
            path="/"
            render={() => {
              if (!token) {
                return <Redirect to="/login" />;
              } else {
                if (role) {
                  return <Layout />;
                } else {
                  getUserInfo(token)(dispatch).then(() => <Layout />);
                }
              }
            }}
          />
        </Switch>
      </HashRouter>
    );
  } 

export default Router;
 