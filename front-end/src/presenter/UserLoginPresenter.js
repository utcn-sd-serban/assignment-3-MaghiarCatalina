import user from "../model/user";

class UserLoginPresenter{

    onLog(){
        // var username = user.state.newUser.username;
        // var password = user.state.newUser.password;
        // user.checkLoginInfo(username,password);
        // if(user.state.loginCheck){
        //     user.changeNewUserProperty("username","");
        //     user.changeNewUserProperty("password","");
        //     window.location.assign("#/home"); 
        // }
        // else{
        //     window.location.assign("#/");
        // }
        user.checkLoginInfo();
        window.location.assign("#/home"); 
    }

    onChange(property, value){
        user.changeNewUserProperty(property,value);
    }
}

const userLoginPresenter = new UserLoginPresenter();

export default userLoginPresenter;