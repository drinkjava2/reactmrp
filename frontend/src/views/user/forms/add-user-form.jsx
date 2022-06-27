import React, { Component } from "react";
import { Form, Input, Select, Modal } from "antd";
import { reqValidatUserID } from "E:/reactmrp/frontend/src/api/user";
import * as my from "E:/reactmrp/frontend/src/myserverless/myserverless.js";
const { TextArea } = Input;
class AddUserForm extends Component {
  validatUserID = async (rule, value, callback) => {
    if (value) {
      if (!/^[a-zA-Z0-9]{1,6}$/.test(value)) {
        callback("用户ID必须为1-6位数字或字母组合");
      }
      let status = await my.data$qryString(`#admin select 1 from users where userId=?`, value);
//      原版本用Mock是这样写的      
//      let res = await reqValidatUserID(value);
//      const { status } = res.data;
       if (status) {
        callback("该用户ID已存在");
      }
    } else {
      callback("请输入用户ID");
    }
    callback();
  };
  render() {
    const { visible, onCancel, onOk, form, confirmLoading } = this.props;
    const { getFieldDecorator } = form;
    const formItemLayout = {
      labelCol: {
        sm: { span: 4 },
      },
      wrapperCol: {
        sm: { span: 16 },
      },
    };
    return (
      <Modal
        title="编辑"
        visible={visible}
        onCancel={onCancel}
        onOk={onOk}
        confirmLoading={confirmLoading}
      >
        <Form {...formItemLayout}>
          <Form.Item label="用户ID:">
            {getFieldDecorator("id", {
              rules: [{ required: true, validator: this.validatUserID }],
            })(<Input placeholder="请输入用户ID" />)}
          </Form.Item>
          <Form.Item label="用户名称:">
            {getFieldDecorator("name", {
              rules: [{ required: true, message: "请输入用户名称!" }],
            })(<Input placeholder="请输入用户名称" />)}
          </Form.Item>
          <Form.Item label="用户角色:">
            {getFieldDecorator("role", {
              initialValue: "admin",
            })(
              <Select style={{ width: 120 }}>
                <Select.Option value="admin">admin</Select.Option>
                <Select.Option value="guest">guest</Select.Option>
              </Select>
            )}
          </Form.Item>
          <Form.Item label="用户描述:">
            {getFieldDecorator("description", {
            })(<TextArea rows={4} placeholder="请输入用户描述" />)}
          </Form.Item>
        </Form>
      </Modal>
    );
  }
}

export default Form.create({ name: "AddUserForm" })(AddUserForm);
