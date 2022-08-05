import axios from 'axios';


export const validateLogin= async(data)=>{
    return await axios.post("http://localhost:8085/authenticate",data);
}

export const getReportedPosts= async()=>{
    return await axios.get("http://localhost:8085/admin/post/reported");
}

export const deleteReportedPost= async(id)=>{
    return await axios.delete("http://localhost:8085/admin/"+id);
}

export const removePostReports= async(id)=>{
    return await axios.put("http://localhost:8085/admin/post/remove/"+id);
}

export const register= async(data)=>{
    return await axios.post("http://localhost:8085/registerUser",data);
}

