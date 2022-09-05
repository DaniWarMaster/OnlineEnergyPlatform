import React from 'react';
import * as API_DEVICES from "./device-api";
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

import DeviceForm from "./device-form";
import DeviceTable from "./device-table";
import EditDeviceForm from "./device-edit-row";

class DevicesContainer extends React.Component {
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
            editedDevice: null,
            errorStatus: 0,
            error: null
        };
    }

    fetchDevices() {
        return API_DEVICES.getDevices((result, status, err) => {

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
        this.fetchDevices();
    }

    toggleForm() {
        this.setState({selected: !this.state.selected});
    }

    handleUpdateEvent(event) {
        var item = null;
        this.setState(
        {
            isEditing: false,
            editedDevice: null
        });

        API_DEVICES.getDeviceById(event.target.id, (result, status, err) => {
            console.log("Device found: " + JSON.stringify(result));
            this.setState(
            {
                isEditing: true,
                editedDevice: result
            })

            //console.log("Item found: " + JSON.stringify(this.state.editedSensor));

        });
    }

    handleDeleteEvent(event) {
        //console.log("ENTERED -- Successfully deleted sensor with id: " + event.target.id);

        return API_DEVICES.deleteDevice(event.target.id, (result, status, err) => {
            if (result !== null && status === 200) {
                console.log("Successfully deleted device with id: " + result);

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
        this.fetchDevices();
    }

    reload() {
        this.setState({
            isLoaded: false
        });
        this.toggleForm();
        this.fetchDevices();
    }

    render() {
        return (
            <div>
                {LoginForm.role == "ADMIN" && <CardHeader>
                    <strong> Devices List </strong>
                </CardHeader>}


                {LoginForm.role == "ADMIN" && <Card>
                    <br/>
                    <Row>
                        <Col sm={{size: '8', offset: 1}}>
                            <Button color="primary" onClick={this.toggleForm}>Add Device </Button>
                        </Col>
                    </Row>
                    <br/>
                    <Row>
                        <Col sm={{size: '8', offset: 1}}>
                            {this.state.isLoaded && <DeviceTable tableData = {this.state.tableData} handleEventUpdate = {this.handleUpdateEvent} handleDeleteEvent = {this.handleDeleteEvent}/>}
                            {this.state.errorStatus > 0 && <APIResponseErrorMessage
                                                            errorStatus={this.state.errorStatus}
                                                            error={this.state.error}
                                                        />   }
                        </Col>
                    </Row>
                    <Row>
                        <Col sm={{size: '8', offset: 1}}>
                            {this.state.isEditing && <EditDeviceForm reloadHandler={this.reload2} deviceEdit={this.state.editedDevice} cancelAction={this.cancelAction}/>}
                        </Col>
                    </Row>
                </Card>}

                {LoginForm.role == "ADMIN" && <Modal isOpen={this.state.selected} toggle={this.toggleForm}
                       className={this.props.className} size="lg">
                    <ModalHeader toggle={this.toggleForm}> Add Device: </ModalHeader>
                    <ModalBody>
                        <DeviceForm reloadHandler={this.reload}/>
                    </ModalBody>
                </Modal>}

                {LoginForm.role != "ADMIN" && <Unauthorized/>}
            </div>
        )

    }
}

export default DevicesContainer;