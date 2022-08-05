import React, { Component } from 'react';
import { WEB_SOCKET_URL } from '../../Constants';

export default class StartSocket extends Component {
    static connect = () => {
        const Stomp = require('stompjs');
        let SockJS = require('sockjs-client');
        SockJS = new SockJS(WEB_SOCKET_URL);
        return Stomp.over(SockJS);
    }
}
