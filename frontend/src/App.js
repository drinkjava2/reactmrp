import React, { Component } from 'react';
import { Provider } from "react-redux";
import { ConfigProvider } from "antd";
import zhCN from "antd/es/locale/zh_CN";
import store from "./store";
import Router from "./router";

class App extends Component {
  render() { 
    console.log("Debug_APP1:"+JSON.stringify(this.state));
    console.log("Debug_APP2:"+JSON.stringify(this.props));
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
