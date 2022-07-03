import React, { Component } from "react";
import { Table, Tag } from "antd";
import { transactionList } from "@/api/remoteSearch";
import * as my from "@/myserverless/myserverless.js";

const columns = [
  {
    title: "Order_No",
    dataIndex: "order_no",
    key: "order_no",
    width: 200,
  },
  {
    title: "Price",
    dataIndex: "price",
    key: "price",
    width: 195,
    render: text => (`$${text}`),
  },
  {
    title: "Status",
    key: "tag",
    dataIndex: "tag",
    width: 100,
    render: (tag) => (
      <Tag color={tag === "pending" ? "magenta" : "green"} key={tag}>
        {tag}
      </Tag>
    ),
  },
];

class TransactionTable extends Component {
  _isMounted = false;   // 这个变量是用来标志当前组件是否挂载
  state = {
    list: [],
  };
  fetchData = () => {
      my.data$java(`#public
        List l=new ArrayList();
        for (int i = 0; i<13; i++) {
            Map m=new HashMap<>();
            m.put("key", i);
            m.put("order_no", UUID.randomUUID().toString());
            m.put("price", (float)Math.round(1000000*new Random().nextFloat())/100);
            m.put("tag", new String[] {"pending", "success" }[new Random().nextInt(2)]);
            l.add(m);
        }
        return l;
        `).then((data) => {
          const list = data;
          if (this._isMounted) { 
            this.setState({ list });
          }
        }); 
  };
  componentDidMount() {
    this._isMounted = true;
    this.fetchData();
  }
  componentWillUnmount() {
    this._isMounted = false;
  }
  render() {
    return (
      <Table
        columns={columns}
        dataSource={this.state.list}
        pagination={false}
      />
    );
  }
}

export default TransactionTable;
