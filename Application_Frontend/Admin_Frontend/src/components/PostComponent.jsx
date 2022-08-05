import React from "react";
import { getReportedPosts } from "../utils/ApiCalls";
import Avatar from "./Avatar";
import moment from 'moment';

export default function PostComponent(props){

    const getReports=()=>{
        getReportedPosts().then((res)=> console.log("Done"));
    }

    

    return(
        <div className="post">
            <div className="lin">
            <div className="lino" >
                <Avatar username={props.post.username}></Avatar>    
            <div className="inf">{props.post.username}<sub className="sb">{moment(props.post.targetDate).fromNow()}</sub></div></div>
            <button class="btn btn-danger" onClick={()=> props.handleDelete(props.post.id)}>Delete Post</button>
            <button class="btn btn-info" onClick={()=> props.handleRemoveReports(props.post.id)}>Remove</button>
            </div>
            <div class="cont">
            {(props.post.description != null) ? props.post.description 
                        : <img className="image-post" src={props.post.postImage}/>}
            
                {/* <h5>{props.post.description}</h5> */}
            </div>
        </div>
    );
}