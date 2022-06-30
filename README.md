# ReactMRP
ReactMRP是一个微型MRP系统，主要特点是技术上基于MyServerless，将SQL和Java源码直接写在前端，从而实现最高的开发效率。  

# 架构
ReactMRP技术栈基于以下开源项目：  
Ant-Design - 这是一个UI库，提供多种Web UI组件  
React - 前端框架，是UI组件化的技术基础  
MyServerless  - 这是我写的微型Serverless工具，是前后端的桥梁，支持将SQL和Java写在前端并动态编择执行，并提供打包功能。  
Undertow - 这是一个Servlet服务器，作用是处理HTTP协议和管理线程。
jSqlBox - 这是我写的后端DAO工具，提供了多种数据库访问功能，如：参数内嵌式SQL/ORM/ActiveRecord/分库分表等。  
MySQL或H2 - 后端数据库，H2用于测试或演示  
可以看出以上是一个非常简短的全栈开发工具链，这是针对MRP系统或小型网站的特点设计的。MRP之类应用的特点是在线人数少，但是业务逻辑非常复杂且业务需求变换快，所以工具链不能太复杂，之所以采用MyServerless这种开发模式，也是针对这个特点设计的，也就是说，架构一定要扁平化，在拿到UI数据的第一时间就要直接捅到数据库里去访问，不需要通过API和文档充当中介中转，这样才能适应业务需求的快速变化。  

# 安全
ReactMRP的安全性是由MyServerless来保障的。相对于GraphQL/XXXAPI之类复杂的安全机制，MyServerless的安全机制很简单，几名话可以说清：  
1. 开发期用developer账户登录，developer可在在前端任意写SQL和Java，并发送到后端动态编译执行。  
2. 开发结束后，用MyServerless提供的打包工具将前端SQL和java代码抽取到后端，这样线上运行时前端是看不到SQL和Java源码的，而且线上运行配置成拒绝动态编译执行。  
3. 开发期对于每一个方法，由前端赋一个方法名，比如 my.$java(`#admin db.exe("delete from users");`); 这个方法名是admin，说明登录用户必须配置有admin权限才能运行，用这种方式可以精确控制每个方法的执行权限。无须登录的公开方法，方法必须起名为public前缀。public方法也可以直接写在后端，参见项目中PublicBackend.java示例。
关于本项目的安全设计，可以试着用developer和admin、guest账号登录体验一下就知道了，除了developer权限用户，其余账号都无权执行放在前端的动态SQL和Java。欢迎大家来攻破本项目的安全机制，虽然理论上是不可能的。  

# 开发
ReactMRP分为后端和前端两部分，分别位于backend和frontend两个目录下，开发阶段要同时启动后端和前端服务。对于团队开发，后端服务可以布置在远程，所有前端直接基于同一个远程后端进行开发，把SQL和业务代码直接写在前端PHP/HTML/JS/TS/JSX里即可。  

#  后端
后端基于[MyServerless](https://github.com/drinkjava2/myserverless)基础上进行配置和开发，后端通常不包含或只包含少量业务逻辑，一旦启动后通常就不用再管了，除了复杂业务外，所有业务都写在前端html或Javascript里，这样可以避免修改业务代码后需要反复重启后端。  

## 后端启动
双击run-server.bat即可启动后端服务

# 前端
前端是基于难凉热血的[react-antd-admin-template](https://nlrx-wjc.github.io/react-antd-admin-template/) 模板上修改而来。
目前只是刚刚完成了将用户登录和用户管理模块的Mock用实际的后端MyServerless服务代替，具体的MRP业务逻辑(物料/库存/订单等)还待添加。但是这个架构已经成型了，可以基于这个框架来进行CRUD，没有什么大的技术难点了，所以虽然还是个半成品，我也把它共享出来了。实际上，MRP/ERP之类的系统每个企业都不一样，都是需要定制修改的，所以框架本身架构是否支持快速开发才是最有价值的地方。  
目前这个开发框架的缺点是表单输入比较繁琐，不适合MRP/ERP系统的特点，这个在后续版本会继续改进。  

## 前端安装依赖  
npm install  
或如在国内，切换淘宝源，解决 npm 下载速度慢的问题:  
npm install --registry=https://registry.npm.taobao.org  

## 前端启动服务
npm start  
启动完成后会自动打开浏览器访问 [http://localhost:3000](http://localhost:3000)  

## 前端打包
1) 运行backend目录下的go-backend.bat，把SQL和Java抽取到后端，以实现安全性。  
2) 运行npm run build 生成发布包，并上传到生产服务器  

# 关于测试和文档
MyServerless这种开发模式决定了它在开发过程中已经手工反复测试过每个功能点了，所以单元测试、集成测试、API文档什么的统统都不需要了(已经跨过沟了，就没必要再回过头来填沟了)。项目可维护性重点要转到对业务的理解，要将ER图画出来，理清表之间的业务关联关系，以及UI和ER表之间的对应关系。如果出现修改一处其它地方会出错，只能说明开发者没有真正理解业务就开始开发了。  

   
# 附前端功能(有些是原模板项目的功能演示，与MRP业务功能无关，后续版本将删除)

```bash\
- 登录 / 注销

- 权限验证
  - 页面权限
  - 路由权限

- 全局功能
  - 动态侧边栏（支持多级路由嵌套）
  - 动态面包屑
  - 本地/后端 mock 数据
  - Screenfull全屏
  - 自适应收缩侧边栏

- 编辑器
  - 富文本
  - Markdown

- Excel
  - 导出excel
  - 导入excel
  - 前端可视化excel

- Zip
  - 导出zip

- 错误页面
  - 404

- 组件
  - 拖拽列表

- 表格
- Dashboard
- 引导页
- ECharts 图表
- 剪贴板
```

# 前端目录结构

```bash
├─ public                     # 静态资源
│   ├─ favicon.ico            # favicon图标
│   └─ index.html             # html模板
├─ src                        # 项目源代码
│   ├─ api                    # 所有请求
│   ├─ assets                 # 图片 字体等静态资源
│   ├─ components             # 全局公用组件
│   ├─ config                 # 全局配置
│   │   ├─ menuConfig.js      # 导航菜单配置
│   │   └─ routeMap.js        # 路由配置
│   ├─ lib                    # 第三方库按需加载
│   ├─ mock                   # 项目mock 模拟数据
│   ├─ store                  # 全局 store管理
│   ├─ styles                 # 全局样式
│   ├─ utils                  # 全局公用方法
│   ├─ views                  # views 所有页面
│   ├─ App.js                 # 入口页面
│   ├─ defaultSettings.js     # 全局默认配置
│   └─index.js                # 源码入口
├── .env.development          # 开发环境变量配置
├── .env.production           # 生产环境变量配置
├── config-overrides.js       # 对cra的webpack自定义配置
├── deploy.sh                 # CI部署脚本
├── .travis.yml               # 自动化CI配置
└── package.json              # package.json
```

## 版权 | License

前端：Apache 2.0 License
后端：MIT License

## 关注我 | About Me
[码云](https://gitee.com/drinkjava2)  
[Github](https://github.com/drinkjava2)  