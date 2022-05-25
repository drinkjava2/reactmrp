import React, { Component } from 'react';
import { Provider } from "react-redux";
import { ConfigProvider } from "antd";
import zhCN from "antd/es/locale/zh_CN";
import store from "./store";
import Router from "./router";

class App extends Component {
  render() { 
    console.log("Debug_APP_state:"+JSON.stringify(this.state));
    console.log("Debug_APP_props:"+JSON.stringify(this.props));
    console.log("Debug_APP_store:"+JSON.stringify(store));
    console.log("==store====");
    console.log(store); 
    
    return (
      <ConfigProvider locale={zhCN}>
        <Provider store={store}>
          <Router />
        </Provider>
      </ConfigProvider>
    );
  }
}
 
export default App;
