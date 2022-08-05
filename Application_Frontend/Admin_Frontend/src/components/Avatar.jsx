import React, { Component } from 'react'
import Guest from "./assets/guest.jpeg"
export default function Avatar(props){


    const handleError = (e) => {
        e.target.src = Guest;
    }

    return(
        <div className="cmt-avatar">
            <img src={"http://localhost:8080" + '/avatar/' + props.username} onError={handleError} alt="avatar"/>
        </div>
    );
}