import java.util.*;
import java.sql.Timestamp;

class createKey extends Thread{
     String name="",value="",method="",timeStamp="";
     
      static ArrayList<String> keys = new ArrayList<String>();
       static ArrayList<String> values = new ArrayList<String>();
      static ArrayList<String> TTL = new ArrayList<String>();
      Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    public createKey(String name,String value,String timeStamp,String method){
        this.name=name;
        this.value=value;
        this.method=method;
        this.timeStamp=timeStamp;
    }
    public createKey(String name,String method){
        this.name=name;
         this.method=method;
    }
    
    static int KeyChecking(String str){
          int tempret1=0;
        for (int i = 0; i < keys.size(); i++) {
           if(str.equals(keys.get(i))){
                tempret1=1;
            }
        }
      
        return tempret1;
    }
    
    
    static int getKeyinfo(String n){
        int tempret2=0;
        for (int i = 0; i < keys.size(); i++) {
           if(n.equals(keys.get(i))){
                tempret2=i;
            }
        }
        return tempret2;
    }
       
    public void run(){
        try{
           if(method.equalsIgnoreCase("c")){
               int temp1=KeyChecking(name);
               if(temp1==0){
                    if (keys.size() < (1024 * 1020 * 1024) && name.length() <= (32) && value.length() <= (16 * 1024 * 1024)) {
                      if (timeStamp.equals("0")) {
                          String t1=String.valueOf(timeStamp);
                         keys.add(name);
                     values.add(value);
                     TTL.add(t1);
                      } else {
                          String t2=String.valueOf(timestamp.getTime()+Integer.parseInt(timeStamp));
                    keys.add(name);
                    values.add(value);
                    TTL.add(t2);
                     }
                    
                     }else{
                         System.out.println("Memory Limit exceded");
                     }
               
               }else{
                   System.out.println("The Key is already Present in the database");
               }
               
               
           }else if(method.equalsIgnoreCase("r")){
                    
               int temp2=KeyChecking(name);
                 if(temp2==1){
                     int info=getKeyinfo(name);
                     if(TTL.get(info).equals("0")){
                         System.out.println(keys.get(info)+" "+values.get(info));
                     }else{
                         
                         if(timestamp.getTime()<Long.parseLong(TTL.get(info))){
                             System.out.println(keys.get(info)+" "+values.get(info));
                         }else{
                             System.out.println("The key is Expired due to TTL Property");
                         }
                     }
                     
                 }else{
                     System.out.println("The key is not in database");
                 }
               
           }else if(method.equalsIgnoreCase("d")){
               
               int temp3=KeyChecking(name);
               
               if(temp3==1){
                  
                  int keyinfo=getKeyinfo(name);
                  keys.remove(keyinfo);
                  values.remove(keyinfo);
                  TTL.remove(keyinfo);
                  
                  System.out.println("The key is deleted");
                   
               }else{
                     System.out.println("The key is not in database");
                 }
               
           }
        }catch(Exception e){
            System.out.println(e);
        }
    }
}

public class Main
{
	public static void main(String[] args) {
	    //key passing with keyname,value,TTL,methodname 
	    createKey c=new createKey("navin","value","3600","c");
	    
	   c.start();
	    try{
            Thread.sleep(9000);
        }catch(Exception e){
            System.out.println(e);
        }
	   createKey c1=new createKey("navin","r");
	    try{
            Thread.sleep(9000);
        }catch(Exception e){
            System.out.println(e);
        }
	   c1.start();
	   
	   //create a new key with existing  key
	    createKey c2=new createKey("navin","value","3600","c");
	    try{
            Thread.sleep(3000);
        }catch(Exception e){
            System.out.println(e);
        }
	   c2.start();
	   
	   //create a key with value without TTL property
	    createKey c3=new createKey("balaji","balaji value","0","c");
	    try{
            Thread.sleep(3000);
        }catch(Exception e){
            System.out.println(e);
        }
	   c3.start();
	   
	   
	    //create a key with value without TTL property
	    createKey c4=new createKey("balaji","r");
	    try{
            Thread.sleep(9000);
        }catch(Exception e){
            System.out.println(e);
        }
	   c4.start();
	   
	   
	   
	}
}

