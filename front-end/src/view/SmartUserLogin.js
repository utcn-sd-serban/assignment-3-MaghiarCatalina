import React,{ Component } from "react";
import user from "../model/user";
import UserLogin from "./UserLogin";
import userLoginPresenter from "../presenter/UserLoginPresenter";

const mapModelStateToComponentState = modelState => ({
    username : modelState.newUser.username,
    password : modelState.newUser.password
});

export default class SmartUserLogin extends Component{
    constructor(){
        super();
        this.state = mapModelStateToComponentState(user.state);
        this.listener = modelState => this.setState(mapModelStateToComponentState(modelState));
        user.addListener("change", this.listener);
    }

    componentWillUnmount(){
        user.removeListener("change",this.listener);
    }

    render(){
        return(
            <UserLogin
            onChange = {userLoginPresenter.onChange}
            onLog = {userLoginPresenter.onLog}
            username = {this.state.username}
            password = {this.state.password}
            />
        );
    }
}