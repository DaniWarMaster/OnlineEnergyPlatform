import {HOST} from '../commons/hosts';
import RestApiClient from "../commons/api/rest-client";


const endpoint = {
    sensor: '/sensor',
    sensorID: '/sensor/',
    removeSensor: '/sensor/remove/',
    updateSensor: '/sensor/update'
};

function getSensors(callback) {
    let request = new Request(HOST.backend_api + endpoint.sensor, {
        method: 'GET',
    });
    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}

function getSensorById(params, callback){
    let request = new Request(HOST.backend_api + endpoint.sensorID + params, {
       method: 'GET'
    });

    console.log(request.url);
    RestApiClient.performRequest(request, callback);
}

function postSensor(sensor, callback){
    let request = new Request(HOST.backend_api + endpoint.sensor , {
        method: 'POST',
        headers : {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(sensor)
    });

    console.log("URL: " + request.url);

    RestApiClient.performRequest(request, callback);
}

function updateSensor(sensor, callback){
    let request = new Request(HOST.backend_api + endpoint.updateSensor , {
        method: 'POST',
        headers : {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(sensor)
    });

    console.log("URL: " + request.url);

    RestApiClient.performRequest(request, callback);
}

function deleteSensor(sensorID, callback){
    let request = new Request(HOST.backend_api + endpoint.removeSensor + sensorID , {
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
    getSensors,
    getSensorById,
    postSensor,
    updateSensor,
    deleteSensor
};
