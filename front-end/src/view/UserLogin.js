import React from "react";
import 'bootstrap/dist/css/bootstrap.css';

const UserLogin = ({username, password, onLog, onChange}) => (
<div className="jumbotron bg-dark text-white">
    <h2>Login</h2>
    <br/>
    <div> 
        <label>Username: </label>
        <input placeholder="username" value={username}
            onChange={ e => onChange("username", e.target.value)}
        />
        <br/>
        <label>Password: </label>
        <input placeholder="password" value={password}
            onChange={ e => onChange("password", e.target.value)}
        />
        <br/>
        <button className="btn btn-success " onClick={onLog}>Login</button>
    </div>
</div>
);

export default UserLogin;