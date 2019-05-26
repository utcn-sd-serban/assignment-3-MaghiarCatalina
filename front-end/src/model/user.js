import { EventEmitter } from "events";

class User extends EventEmitter{
    constructor(){
        super();
        this.state = {
            users: [],
            newUser:{
                username: "",
                password: ""
            },
            currentUser:{
                username: "",
                password: ""
            },
            loginCheck: false
        };
    }

    addUser(username,password){
        this.state = {
            ...this.state,
            users: this.state.users.concat([{
                username: username,
                password: password
            }])
        };
        this.emit("change",this.state);
    }

    changeNewUserProperty(property,value){
        this.state = {
            ...this.state,
            newUser: {
                ...this.state.newUser,
                [property]: value
            }
        };
        this.emit("change",this.state);
    }

    changeCurrentUserProperty(property,value){
        this.state = {
            ...this.state,
            currentUser: {
                ...this.state.currentUser,
                [property]: value
            }
        };
        this.emit("change",this.state);
    }

    changeLoginFlag(property,value){
        this.state={
            ...this.state,
            [property]: value
        };
        this.emit("change", this.state);
    }

    checkLoginInfo(){//(username,password){
        // var found = false;
        // for(let i=0; i<this.state.users.length; i++){
        //     if(this.state.users[i].username === username && this.state.users[i].password === password){
        //         found = true;
        //     }
        // }
        // if(found){
        //     this.changeLoginFlag("loginCheck",true);
        //     this.changeCurrentUserProperty("username", username);
        //     this.changeCurrentUserProperty("password",password);
        // }
        // else{
        //     this.changeLoginFlag("loginCheck",false);
        // }
    }
}

const user = new User();

export default user;