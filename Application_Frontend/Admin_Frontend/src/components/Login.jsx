import React from "react";
import axios from 'axios';
import {Form, Button} from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.css';
import './styles.css';
import { useState } from "react";
import { useNavigate } from 'react-router-dom';
import { validateLogin } from "../utils/ApiCalls";
import { useEffect } from "react";

export default function Login(){

    const navigate= useNavigate();

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const change=(event)=>{
        if(event.target.id=='username'){
            setUsername(event.target.value);
        }
        if(event.target.id=='password'){
            setPassword(event.target.value);
        }
    }

    const handleSubmit=()=>{
        console.log("SUB:::", username, password);
        let body={
            username: username,
            password
        }
        validateLogin(body).then((res)=>{
            console.log("Da::", res);
           if(res.data.length!=0) {
            navigate("/admin");
            sessionStorage.setItem("token",res.data.token);
            sessionStorage.setItem("name",username);
            
            axios.interceptors.request.use(
                (config) => {
                    // if (this.isUserLoggedIn()) {
                        config.headers.authorization = "Bearer " +res.data.token
                    // }
                    return config
                }
                )
            // sessionStorage.setItem("type",res.data.isAdmin);
           }
           else alert("Invalid Creds");
        });
        // navigate("/login");
    }

    useEffect(()=>{
        if(sessionStorage.getItem("name")!=null) {navigate("/admin")}
        else navigate("/");
    })

    return(
        <div class="MainApp">
        <div class="App">
            <div class="LoginComponent">
                <div class="Description">
                    <h1>FRIENDBOOK <br/>ADMIN</h1>
                </div>
                <div class="login">
                    <div class="container">
                        <div class="base-container">
                            <div class="content">
                                <div class="form">
                                    <div class="form-group">
                                        <div class="form-group">
                                            <label for="username">Username</label>
                                            <input class="form-control" type="text" id="username" placeholder="username" value={username} onChange={change}/>
                                        </div>
                                        <div class="form-group">
                                            <label for="password">Password</label>
                                            <input class="form-control" type="password" id="password" placeholder="password" value={password} onChange={change}/>
                                        </div>
                                    </div>
                                </div>
                                <br/>
                            <div class="footerBtn">
                                <button type="button" class="btn btn-success" name="login" onClick={handleSubmit}>Login</button>
                                {/* <button type="button" name="register" class="btn btn-info" onClick={()=>(navigate("/register"))}>register</button> */}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div> 
    
    );
}


 

//     <div className="loginInput">
//     <Form className="login">
//         <h2>Login</h2>
//             <Form.Group className="mb-3" controlId="formBasicEmail">
//                 <Form.Label>Username</Form.Label>
//                 <Form.Control id="username" type="text"  value={username} onChange={change}/>
//             </Form.Group>
//             <Form.Group className="mb-3" controlId="formBasicEmail">
//                 <Form.Label>Password</Form.Label>
//                 <Form.Control id="password" type="password" value={password} onChange={change} />
//             </Form.Group>
            
//     </Form>
//     <Button variant="primary" type="submit" onClick={handleSubmit}>
//     LOGIN
// </Button>&nbsp;&nbsp;&nbsp;
// <Button variant="secondary" type="submit" onClick={()=>(navigate("/register"))}>Register</Button>
// </div>