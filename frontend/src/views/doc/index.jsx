import React from 'react';
import TypingCard from '@/components/TypingCard'
const Doc = () => {
  const cardContent = `
  本项目<a href="https://gitee.com/drinkjava2/reactmrp" target="_blank">ReactMRP</a>是在难凉热血的<a href="https://github.com/NLRX-WJC/react-antd-admin-template" target="_blank">react-antd-admin-template前端框架模板</a>基础上修改而成，主要技术变动是将 Mock访问换成实际的后端MyServerless服务。
  
   本项目即是为了演示MyServerless的使用，也是一个实际的MRP项目。目前只刚完成了登录和用户管理两个业务模块，其余页面依然为功能演示。<br/><br/>

    关于难凉热血前端技术相关作者博客请戳这里 <a href="https://nlrx-wjc.github.io/Blog/" target="_blank">难凉热血的博客</a>。<br/><br/>
    
    关于MyServerless的介绍请参见 <a href="https://gitee.com/drinkjava2/myserverless" target="_blank">MyServerless</a>项目，它是我编写的一个开源Serverless工具，主要特点是将SQL和Java直接写在前端。<br/><br/>
      
    欢迎大家与我交流，如果觉得以上项目不错，也麻烦给赏个 star哈。<br/><br/>
  `
  return (
    <div className="app-container">
      <TypingCard title='项目说明' source={cardContent}/>
    </div>
  );
}

export default Doc;