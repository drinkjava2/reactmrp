import React from 'react';
import TypingCard from '@/components/TypingCard'
const Doc = () => {
  const cardContent = `
    本项目是在难凉热血的<a href="https://github.com/NLRX-WJC/react-antd-admin-template" target="_blank">react-antd-admin-template前端框架模板</a>基础上把mock换为MyServerless后端，以演示MyServerless的使用。<br/><br/>
    
    关于难凉热血前端技术相关作者博客请戳这里 <a href="https://nlrx-wjc.github.io/Blog/" target="_blank">难凉热血的博客</a>。<br/><br/>
    
    关于MyServerless的介绍请参见 <a href="https://gitee.com/drinkjava2/myserverless" target="_blank">MyServerless项目</a>，它是由我(Yong)写的一个开源Serverless服务，主要特点是将SQL和Java直接写在前端，而不需要放在云服务器上。<br/><br/>
    
    本项目主页位于这里 <a href="https://gitee.com/drinkjava2/reactmrp" target="_blank">ReactMRP</a>, 这是一个正在开发中的MRP系统，即是为了演示MyServerless的使用，也是一个实际的MRP项目。目前本项目只刚完成了登录和用户管理两个模块。<br/><br/>
 
    欢迎大家与我交流，如果觉得以上这些项目不错，也麻烦给赏个 star哈。<br/><br/>
  `
  return (
    <div className="app-container">
      <TypingCard title='项目说明' source={cardContent}/>
    </div>
  );
}

export default Doc;