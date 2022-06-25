import React from 'react';
import TypingCard from 'E:/reactmrp/frontend/src/components/TypingCard'
const Doc = () => {
  const cardContent = `
    本项目是在《难凉热血的react-antd前端框架模板》基础上把mock换为实际的MyServerless后端调用，以演示MyServerless的使用。<br/>
    关于难凉热血前端技术相关作者博客请戳这里 <a href="https://nlrx-wjc.github.io/Blog/" target="_blank">难凉热血的博客</a>。<br/><br/><br/>
    
    关于MyServerless的介绍请参见这里 <a href="https://gitee.com/drinkjava2/myserverless" target="_blank">MyServerless项目</a>。<br/>
    欢迎大家与我交流，如果觉得以上项目不错，也麻烦给赏个 star 哈。
  `
  return (
    <div className="app-container">
      <TypingCard title='项目说明' source={cardContent}/>
    </div>
  );
}

export default Doc;