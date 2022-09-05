import React from 'react';
import * as API_SENSORS from "./sensor-api"
import APIResponseErrorMessage from "../commons/errorhandling/api-response-error-message";
import LoginForm from "../home/login-form";
import Unauthorized from "../unauthorized";

import {
    Button,
    Card,
    CardHeader,
    Col,
    Modal,
    ModalBody,
    ModalHeader,
    Row
} from 'reactstrap';

import SensorForm from "./senzors-form";
import SensorTable from "./sensor-table";
import SensorEditForm from "./sensor-edit-row";

class SenzorsContainer extends React.Component {
    constructor(props) {
        super(props);
        this.toggleForm = this.toggleForm.bind(this);
        this.handleUpdateEvent = this.handleUpdateEvent.bind(this);
        this.handleDeleteEvent = this.handleDeleteEvent.bind(this);
        this.cancelAction = this.cancelAction.bind(this);
        this.reload = this.reload.bind(this);
        this.reload2 = this.reload2.bind(this);
        this.state = {
            selected: false,
            collapseForm: false,
            tableData: [],
            isLoaded: false,
            isEditing: false,
            editedSensor: null,
            errorStatus: 0,
            error: null
        };
    }

    fetchSensors() {
        return API_SENSORS.getSensors((result, status, err) => {

            console.log("Results: " + result);

            if (result !== null && status === 200) {
                this.setState({
                    tableData: result,
                    isLoaded: true
                });
            } else {
                this.setState(({
                    errorStatus: status,
                    error: err
                }));
            }
        });
    }

    cancelAction() {
        this.setState(
        {
            isEditing: false,
            editedSensor: null
        });
    }

    componentDidMount() {
        this.fetchSensors();
    }

    toggleForm() {
        this.setState({selected: !this.state.selected});
    }

    handleUpdateEvent(event) {
        var item = null;
        this.setState(
        {
            isEditing: false,
            editedSensor: null
        });

        API_SENSORS.getSensorById(event.target.id, (result, status, err) => {
            console.log("Item found: " + JSON.stringify(result));
            this.setState(
            {
                isEditing: true,
                editedSensor: result
            })

            //console.log("Item found: " + JSON.stringify(this.state.editedSensor));

        });



    }

    handleDeleteEvent(event) {
        //console.log("ENTERED -- Successfully deleted sensor with id: " + event.target.id);

        return API_SENSORS.deleteSensor(event.target.id, (result, status, err) => {
            if (result !== null && status === 200) {
                console.log("Successfully deleted sensor with id: " + result);

                this.reload2();
            } else {
                this.setState(({
                    errorStatus: status,
                    error: err
                }));
            }
        });
    }

    reload2() {
        this.setState({
            isLoaded: false,
            isEditing: false
        });
        this.fetchSensors();
    }
    reload() {
        this.setState({
            isLoaded: false,
            isEditing: false
        });
        this.toggleForm();
        this.fetchSensors();
    }

    render() {
        return (
            <div>
                {LoginForm.role == "ADMIN" && <CardHeader>
                    <strong> Senzors List </strong>
                </CardHeader>}


                {LoginForm.role == "ADMIN" && <Card>
                    <br/>
                    <Row>
                        <Col sm={{size: '8', offset: 1}}>
                            <Button color="primary" onClick={this.toggleForm}>Add Senzor </Button>
                        </Col>
                    </Row>
                    <br/>
                    <Row>
                        <Col sm={{size: '8', offset: 1}}>
                            {this.state.isLoaded && <SensorTable tableData = {this.state.tableData} handleEventUpdate = {this.handleUpdateEvent} handleDeleteEvent = {this.handleDeleteEvent}/>}
                            {this.state.errorStatus > 0 && <APIResponseErrorMessage
                                                            errorStatus={this.state.errorStatus}
                                                            error={this.state.error}
                                                        />   }
                        </Col>
                    </Row>
                    <Row>
                        <Col sm={{size: '8', offset: 1}}>
                            {this.state.isEditing && <SensorEditForm reloadHandler={this.reload2} sensorEdit={this.state.editedSensor} cancelAction={this.cancelAction}/>}
                        </Col>
                    </Row>
                </Card>}

                {LoginForm.role == "ADMIN" && <Modal isOpen={this.state.selected} toggle={this.toggleForm}
                       className={this.props.className} size="lg">
                    <ModalHeader toggle={this.toggleForm}> Add Sensor: </ModalHeader>
                    <ModalBody>
                        <SensorForm reloadHandler={this.reload}/>
                    </ModalBody>
                </Modal>}

                {LoginForm.role != "ADMIN" && <Unauthorized/>}
            </div>
        )

    }
}

export default SenzorsContainer;