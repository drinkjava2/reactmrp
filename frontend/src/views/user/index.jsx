import React, { Component } from "react";
import { Card, Button, Modal, Table, message, Divider } from "antd";
import { getUsers, deleteUser, editUser, addUser } from "@/api/user";
import TypingCard from '@/components/TypingCard'
import EditUserForm from "./forms/edit-user-form"
import AddUserForm from "./forms/add-user-form"
import * as my from "@/myserverless/myserverless.js";

const { Column } = Table;
class User extends Component {
  state = {
    users: [],
    editUserModalVisible: false,
    editUserModalLoading: false,
    currentRowData: {},
    addUserModalVisible: false,
    addUserModalLoading: false,
  };
  
  getUsers = async () => { 
    let json=await my.$java(`#admin List<Map<String, Object>> users = DB.qryMapList("select userId as id,  name, description from users order by userId");
            for (Map<String, Object> m : users) {
                List<Object> roles = DB.qryList("select roleName from userRole where userId=", que(m.get("id"))," order by roleName desc");
                if(!roles.isEmpty())
                      m.put("role", roles.get(0));
            }
            return users;`);  
    const { data: users, code: status } = json; 
    if (status === 200) {
      this.setState({
          users
      })
    }
  }
  
  handleEditUser = (row) => {
    this.setState({
      currentRowData:Object.assign({}, row),
      editUserModalVisible: true,
    });
  };

  
    doDeleteUser  = (id) => {
      let result=my.syncData$javaTx(`#admin if("1".equals( DB.qryString("select 1 from userrole where roleName='developer' and userId=?", par($1)) ))
                      return "不能删除开发者用户!";
                  if("1".equals( DB.qryString("select 1 from users where myToken=",DB.que(myToken), " and userId=?", par($1)) ))
                      return "不能删除自身用户!";
                  DB.exe("delete from userrole where userId=", DB.que($1));                      
                  DB.exe("delete from users where userId=", DB.que($1));
                  return true;`, id);
      if(result===true){
        message.success("删除成功")
        this.getUsers();
      } else {
          message.error(result);
      }
    };    
    
    handleDeleteUser = (row) => {
        const { id } = row;
        Modal.confirm({
          title: "",
          content: "确定要删除用户吗?",
          okText: "确定",
          cancelText: "取消",
          onOk: () => {
             this.doDeleteUser(id);
          },
        });
      };
      
  handleEditUserOk = _ => {
    const { form } = this.editUserFormRef.props;
    form.validateFields((err, values) => {
      if (err) {
        return;
      }
      this.setState({ editModalLoading: true, });
      
      my.data$javaTx(`#admin 
              import com.github.drinkjava2.myserverless.util.MyStrUtils;
              Map<String,String> v= (Map<String,String>)$1;
              String role=DB.qryString("select roleName from userRole where roleName='developer' and userId=", que(v.get("id")));
              if("developer".equals(role))
                 return "编辑失败， developer不允许被编辑";
              if(MyStrUtils.isEmpty(v.get("name"))) 
                   return "编辑失败， name不能为空";
               DB.exe("update users set name=?, description=? where userId=?", par(v.get("name"), v.get("description"), v.get("id")));
               DB.exe("delete from userRole where userId=",que(v.get("id")));
               DB.exe("insert into userRole (userId, roleName) ", par(v.get("id"), v.get("role")), DB.VQ );    
               return "编辑成功!";`, values).then(( msg ) => {
                    this.setState( { editModalLoading: false } ); 
                    if ( msg==="编辑成功!" ) {
                        this.setState( { editUserModalVisible: false } ); 
                        message.success(msg);
                        this.getUsers();
                    } else {
                        this.setState( { editUserModalVisible: true} ); 
                        message.error(msg);
                    }
                    
                }
                ); 
      
    });
  };
  
  handleCancel = _ => {
    this.setState({
      editUserModalVisible: false,
      addUserModalVisible: false,
    });
  };

  handleAddUser = (row) => {
    this.setState({
      addUserModalVisible: true,
    });
  };

  handleAddUserOk = _ => {
      const { form } = this.addUserFormRef.props;
      form.validateFields(( err, values ) => {
          if ( err ) {
              return;
          }
          this.setState( { addUserModalLoading: true, } );
          my.data$javaTx(`#admin 
             import com.github.drinkjava2.myserverless.util.MyStrUtils;
             Map<String,String> v= (Map<String,String>)$1;
            if(MyStrUtils.isEmpty(v.get("name"))) //只查name, 不检查userId，因为主键为空不可能插入
                 return null;
             String pwd=com.gitee.drinkjava2.reactmrp.config.ProjectTokenSecurity.encodePassword("123");
             DB.exe("insert into users (userId, name, description, password) ", par(v.get("id"), v.get("name"), v.get("description"), pwd), DB.VQ ); 
             DB.exe("insert into userRole (userId, roleName) ", par(v.get("id"), v.get("role")), DB.VQ );  
             return true;`, values).then(( result ) => {
                  this.setState( { addUserModalLoading: false } );
                  if ( result ) {
                      this.setState( { addUserModalVisible: false } );
                      message.success( "添加成功!" );
                      this.getUsers();
                  } else {
                      this.setState( { addUserModalVisible: true} );
                      message.success( "添加失败,请重试!" );
                  }
              }
              );
      } );
  };
  
  componentDidMount() {
    this.getUsers()
  }
  render() {
    const { users } = this.state
    const title = (
      <span>
        <Button type='primary' onClick={this.handleAddUser}>添加用户</Button>
      </span>
    )
    const cardContent = `在这里，你可以对系统中的用户进行管理，例如添加一个新用户，或者修改系统中已经存在的用户。`
    return (
      <div className="app-container">
        <TypingCard title='用户管理' source={cardContent} />
        <br/>
        <Card title={title}>
          <Table bordered rowKey="id" dataSource={users} pagination={false}>
            <Column title="用户ID" dataIndex="id" key="id" align="center"/>
            <Column title="用户名称" dataIndex="name" key="name" align="center"/>
            <Column title="用户角色" dataIndex="role" key="role" align="center"/>
            <Column title="用户描述" dataIndex="description" key="description" align="center" />
            <Column title="操作" key="action" width={195} align="center"render={(text, row) => (
              <span>
                <Button type="primary" shape="circle" icon="edit" title="编辑" onClick={this.handleEditUser.bind(null,row)}/>
                <Divider type="vertical" />
                <Button type="primary" shape="circle" icon="delete" title="删除" onClick={this.handleDeleteUser.bind(null,row)}/>
              </span>
            )}/>
          </Table>
        </Card>
        <EditUserForm
          currentRowData={this.state.currentRowData}
          wrappedComponentRef={formRef => this.editUserFormRef = formRef}
          visible={this.state.editUserModalVisible}
          confirmLoading={this.state.editUserModalLoading}
          onCancel={this.handleCancel}
          onOk={this.handleEditUserOk}
        />  
        <AddUserForm
          wrappedComponentRef={formRef => this.addUserFormRef = formRef}
          visible={this.state.addUserModalVisible}
          confirmLoading={this.state.addUserModalLoading}
          onCancel={this.handleCancel}
          onOk={this.handleAddUserOk}
        />  
      </div>
    );
  }
}

export default User;
