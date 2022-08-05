import axios from 'axios'
import { API_URL, JPA_API_URL, GATEWAY_POST_JPA_API_URL} from '../../Constants'

class PostDataService {

    retrieveAllPosts(name) {
        return axios.get(`${GATEWAY_POST_JPA_API_URL}/users/${name}/posts`);
    }

    retrieveAll() {
        return axios.get(`${GATEWAY_POST_JPA_API_URL}/users/posts`);
    }

    retrievePost(name, id) {
        return axios.get(`${GATEWAY_POST_JPA_API_URL}/users/${name}/posts/${id}`);
    }

    deletePost(name, id) {
        return axios.delete(`${GATEWAY_POST_JPA_API_URL}/users/${name}/posts/${id}`);
    }

    uploadPost(file, username) {
        const formData = new FormData();
        formData.set("file", file);
        return axios.post(`${GATEWAY_POST_JPA_API_URL}/uploadPost/${username}`,formData,
        {
            headers: {
                'content-type': 'multipart/form-data'
            },
        });
    }

    updatePost(name, id, todo) {
        return axios.put(`${GATEWAY_POST_JPA_API_URL}/users/${name}/posts/${id}`, todo);
    }

    createPost(name, todo) {
        return axios.post(`${GATEWAY_POST_JPA_API_URL}/users/${name}/posts/`, todo);
    }

    retrievePostComments(name, id) {
        return axios.get(`${GATEWAY_POST_JPA_API_URL}/users/${name}/posts/${id}/comments`);
    }

    postComment(name, id, comment) {
        return axios.post(`${GATEWAY_POST_JPA_API_URL}/users/${name}/posts/${id}/comments`, comment);
    }

    likePost(body) {
        return axios.post(`${GATEWAY_POST_JPA_API_URL}/like`, body)
    }

    unLikePost(body) {
        return axios.post(`${GATEWAY_POST_JPA_API_URL}/unlike`, body)
    }

    reportPost(body) {
        return axios.post(`${GATEWAY_POST_JPA_API_URL}/post/report`, body)
    }

}

export default new PostDataService()