import React from 'react';
import TypingCard from 'E:/reactmrp/frontend/src/components/TypingCard'
const GuestPage = () => {
  const cardContent = `这个页面只有admin和editor角色才可以访问，guest角色看不到`
  return ( 
    <div className="app-container">
      <TypingCard title='editor页面' source={cardContent}/>
    </div>
  );
}
 
export default GuestPage;