
var arr = [ //sample value in the database
    {
        key: "navin",
        value: "secret key",
        timeout: 0
    }, {
        key: "balaji",
        value: "secret key",
        timeout: 0
    }
];

const createKey = (keys, value, timeout = 0) => {

    const keyConfirmation = arr.find((k) => {
        return k.key === keys;
    })

    if (!keyConfirmation) { //permit if the key is not present in database


        if (/^[a-zA-Z]*$/g.test(keys)) { //checks the key character is a character 
            if (arr.length < (1024 * 1020 * 1024) && keys.length <= (32) && value.length <= (16 * 1024 * 1024)) {
                if (timeout == 0) {
                    arr.push({
                        key: keys,
                        value: value,
                        timeout: timeout
                    })
                    console.log(`${keys} key is inserted`);
                } else {
                    arr.push({
                        key: keys,
                        value: value,
                        timeout: new Date().getTime() + timeout
                    })
                    console.log(`${keys}key is inserted`);
                }
            } else {
                console.log("Memory Limit Exceded"); //it throws memory overflow exception 
            }

        } else {
            console.log("The Key contains some Other Symbols other than Alphabets");  //throws error key valu contains other than alphabets
        }
    } else {
        console.log("The key is already Present");
    }
}


const readKey = (incomingKey) => {

    const keyConfirmationRead = arr.find((k) => { //check the key is present in database
        return k.key === incomingKey;
    })

    if (keyConfirmationRead) { //if exist then search the value

        const keyRead = arr.find((k) => {
            return k.key === incomingKey;
        })

        if (keyRead.timeout == 0) {
            console.log(`${keyRead.key} key and the ${keyRead.value}`);
        } else {
            if (new Date().getTime() < keyRead.timeout) { //comparing current time with key time

                console.log(`${keyRead.key} key and the ${keyRead.value}`);
            } else {
                console.log("The Key and Value are Expried due to TTL Property");
            }
        }

    } else {
        console.log("The Key is Not Available in the Database");
    }

}



const deleteKey = (incommingdeleteKey) => {
    const keyConfirmationdelete = arr.find((k) => { //checks if the key is present in database
        return k.key === incommingdeleteKey;
    })

    if (keyConfirmationdelete) {
        arr = arr.filter(item => item.key !== incommingdeleteKey); //filter or delete the key from the database
        console.log(`${keyConfirmationdelete.key} key is Deleted`)
    } else {
        console.log(`${keyConfirmationdelete.key} Key is not Present in database`);
    }

}


module.exports = {
    createKey,
    readKey,
    deleteKey,
}