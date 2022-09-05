import {HOST} from '../commons/hosts';
import RestApiClient from "../commons/api/rest-client";


const endpoint = {
    users: '/users',
    userID: '/users/',
    removeUser: '/users/remove/',
    updateUser: '/users/update'
};

function getUsers(callback) {
    let request = new Request(HOST.backend_api + endpoint.users, {
        method: 'GET',
    });
    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}

function getUserById(params, callback){
    let request = new Request(HOST.backend_api + endpoint.userID + params, {
       method: 'GET'
    });

    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}

function postUser(users, callback){
    let request = new Request(HOST.backend_api + endpoint.users , {
        method: 'POST',
        headers : {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(users)
    });

    console.log("URL: " + request.url);

    RestApiClient.performRequest(request, callback);
}

function updateUser(users, callback){
    let request = new Request(HOST.backend_api + endpoint.updateUser , {
        method: 'POST',
        headers : {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(users)
    });

    console.log("URL: " + request.url);

    RestApiClient.performRequest(request, callback);
}

function deleteUser(usersID, callback){
    let request = new Request(HOST.backend_api + endpoint.removeUser + usersID, {
        method: 'POST',
        headers : {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        }
    });

    console.log("URL: " + request.url);

    RestApiClient.performRequest(request, callback);
}

export {
    getUsers,
    getUserById,
    postUser,
    updateUser,
    deleteUser
};
