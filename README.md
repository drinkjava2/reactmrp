# ReactMRP
ReactMRP是一个微型MRP系统，主要特点是技术上基于MyServerless，将SQL和Java源码直接写在前端，从而实现最高的开发效率。  

# 架构
ReactMRP技术栈基于以下开源项目：  
Ant-Design - 这是一个UI库，提供多种Web UI组件  
React - 这是个前端框架，是UI组件化的基础  
MyServerless  - 这是我写的微型Serverless工具，是前后端的桥梁，支持将SQL和Java写在前端并动态编择执行，并提供打包功能。MyServerless是一个servlet，处理*.do后缀的访问，可以单独或与旧系统集成配置到Servlet服务器上运行。  
Undertow - 这是随便找的一个Servlet服务器，作用是处理HTTP协议和管理线程。  
jSqlBox - 这是我写的后端DAO工具，提供了较全的数据库存取功能，如：参数内嵌式SQL/ORM/ActiveRecord/DDL生成/分页/分库分表/事务等。  
H2或MySQL - 后端数据库，H2内存数据库用于演示，MySql用于调试和生产  
可以看出以上是一个非常短的全栈开发工具链，这是针对MRP/ERP之类小型网站的特点设计的。MRP之类应用特点是在线人数少，但是业务逻辑非常复杂且业务需求变换快，所以工具链不能复杂，之所以采用MyServerless这种开发模式，也是针对这个特点设计的，也就是说架构一定要扁平化，在拿到UI数据的第一时间就要直接捅到数据库里去访问，不要通过API和文档充当中介，这样才能减少沟通，适应业务需求的快速变化。  

# 安全
ReactMRP的安全性是由MyServerless来提供的，这个安全机制很简单，几名话可以说清：  
1. 开发期用具有developer权限的账户登录，可在前端任意写SQL和Java，并发送到后端动态编译执行。  
2. 开发期对于每一个方法，由前端赋一个方法前缀名，比如 my.$executeSql(`#ReadUsers delete from users where id>?`, 10); 这个ReadUsers方法前缀说明登录用户必须服务端配置有ReadUsers权限才能运行，用这种方式可以精确控制每个方法的执行权限。无须登录的公开方法必须起名为public前缀。如方法名省略，系统默认起名为default。
3. 发布前进行命名检查，防止方法名要求的权限与它的代码内容不符，比如上面这个ReadUsers方法，它的代码是在删库，和命名完全不符，所以要修改代码或者修改方法名。
4. 发布前，用MyServerless提供的打包工具将前端SQL和java代码抽取到后端，这样线上运行时前端是看不到SQL和Java源码的，而且线上运行配置成拒绝动态编译执行。  
5. 方法也可以采用传统前后分离模式直接写在后端，参见项目中PublicBackend$TokenLogin示例，签权依然是统一按方法名来判断。
关于本项目的安全设计，大家可以试着用developer和admin、guest账号登录体验一下就知道了，除了developer账号，其余账号都无权动态执行前端的SQL和Java。欢迎大家来找出本项目的安全漏破，虽然理论上是不存在的。  

# 开发
ReactMRP分为后端和前端两部分，分别位于backend和frontend两个目录下，开发阶段要同时启动后端和前端服务。对于团队开发，后端服务可以布置在远程，所有前端直接基于同一个远程后端进行开发，把SQL和业务代码直接写在前端PHP/HTML/JS/TS/JSX里即可。  

# 后端
后端基于[MyServerless](https://github.com/drinkjava2/myserverless)基础上进行配置和开发，后端通常不包含或只含有少量业务逻辑，一旦启动后就不用再管了，除了复杂业务外，所有业务都写在前端html或Javascript里，这样可以避免修改业务代码后需要反复重启后端。  

## 后端启动
运行backend目录下的run-server.bat即可启动后端服务
启动完成后会自动打开浏览器访问 [http://localhost:3000](http://localhost:3000) 

# 前端
前端是基于难凉热血的[react-antd-admin-template](https://nlrx-wjc.github.io/react-antd-admin-template/) 前端模板基础上修改而来。
目前只是刚刚完成了将用户登录和用户管理模块的Mock部分用实际数据库代替，具体的MRP业务逻辑(基础数据/BOM/库存管理/订单等)还待添加。但是这个架构已经成型了，可以基于当前框架进行CRUD，能看出MyServerless的优点了，所以虽然业务部分还没完成，我也把它共享出来了。实际上，MRP/ERP之类的系统每个企业都不一样，都是需要定制修改的，一个企业的MRP代码对另一个企业可能就是垃圾。框架本身是否支持快速开发、是否有可维护性才是最有价值的地方。  
目前这个开发框架的缺点是表单的增删改查比较繁琐，这个在后续版本会继续改进。  

## 前端安装依赖  
npm install  
或如在国内，使用淘宝源加快下载速度:  
npm install --registry=https://registry.npm.taobao.org  

## 前端启动
运行front目录下的run_npm_start.bat即可启动前端
启动完成后会自动打开浏览器访问 [http://localhost:3000](http://localhost:3000)  

## 前端打包
1) 运行backend目录下的go-backend.bat，把SQL和Java抽取到后端，以实现安全性。  
2) 运行npm run build 生成发布包，并上传到生产服务器  

# 关于测试和文档
MyServerless这种开发模式决定了它在开发过程中已经手工反复测试过每个功能点了，所以单元测试、集成测试、API文档什么的统统都不需要了(从沟上跨过去了，就没必要回头来填沟了)。项目可维护性重点要转到对业务的理解，将ER图导出来，理清表之间的业务关联，以及UI字段和ER表之间的对应关系。如果出现修改一处其它地方出错，只能说明开发者还没有理解业务就开始编程了。  

# 附前端功能(有些是原模板项目的功能演示，与MRP业务功能关联不大，暂时保留，后续版本将删除)

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
# IDE
IDE要看个人喜好。我用Eclipse安装TypeScript插件，就可同时导入和编辑前后端两个项目。

## 版权 | License
前端：MIT License
后端：Apache 2.0 License

## 关注我 | About Me
[码云](https://gitee.com/drinkjava2)  
[Github](https://github.com/drinkjava2)  
微信:yong99819981(加群请留言"drinkjava2开源技术群")  
