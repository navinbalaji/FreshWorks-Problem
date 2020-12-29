const myFunction=require('./Sample');

//create a key with keyname, value and TTL property 
myFunction.createKey("dinesh",'my secret key',3600);

//try to read the deleted key after TTL Property
setTimeout(()=>{
    myFunction.readKey("dinesh");
},9000);


//create a new key with existing  key
setTimeout(()=>{
    myFunction.createKey("navin",'my secret value');
},9000);


//read the key and value of the key balaji
setTimeout(()=>{
    myFunction.readKey("balaji");
},9000);

//create a new key with existing  key without TTL property
setTimeout(()=>{
    myFunction.createKey("siva",'siva value');
},9000);


//read the siva's key value
setTimeout(()=>{
    myFunction.readKey("siva");
},9000);


//delete the key navin
setTimeout(()=>{
    myFunction.deleteKey("navin");
},9000);


//try to read the key navin
setTimeout(()=>{
    myFunction.readKey("navin");
},9000);

