package com.gitee.drinkjava2.reactmrp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.github.drinkjava2.myserverless.BaseTemplate; 


@SuppressWarnings("all")
public class Temp extends BaseTemplate {

    @Override
	public Object executeBody() {
        if(1>0) {
            
            try {
                Date d=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("");
            } catch (ParseException e) {
                
            }
                    
		/* MYSERVERLESS BODY BEGIN */
//
//            
//            Sample s =new Sample().loadById($1);
//            s.setAuthor(author);
//            s.setDate(date);
//            s.setReadings(readings);
//            s.setStar(star);
//            s.setStatus(status);
//            s.setTitle(title);
//            s.update();
//            
            
            
        /* MYSERVERLESS BODY END */
        } 
        return null;
	}

}
