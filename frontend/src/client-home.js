import React from "react";
import Unauthorized from "./unauthorized";
import { withRouter } from 'react-router-dom';
import {HOST} from './commons/hosts';

import SockJS from "sockjs-client";
import Stomp from "webstomp-client";

var stompClient = null;

class ClientHome extends React.Component {

    constructor (props){
        super(props);
    }

    componentDidMount() {
         var socket = new SockJS(HOST.backend_api + "/notificationEndPoint");
         stompClient = Stomp.over(socket);
         stompClient.connect({}, function (frame) {
             console.log("Connected: " + frame);
             stompClient.subscribe("/topic/greetings", function (message) {
                 alert("EROARE" + message.body)
             });
         });
     }

    render() {
        return (
            <div>
                <p>Client Page</p>
            </div>
        );
    }
}

export default withRouter(ClientHome);