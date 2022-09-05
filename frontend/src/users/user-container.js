import React from 'react';
import * as API_USERS from "./user-api"
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

import UserForm from "./user-form";
import UserTable from "./user-table";
import EditUserRow from "./user-edit-row";

class UserContainer extends React.Component {
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
            editedUser: null,
            errorStatus: 0,
            error: null
        };
    }

    fetchUsers() {
        return API_USERS.getUsers((result, status, err) => {

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
        this.fetchUsers();
    }

    toggleForm() {
        this.setState({selected: !this.state.selected});
    }

    handleUpdateEvent(event) {
        var item = null;
        this.setState(
        {
            isEditing: false,
            editedUser: null
        });

        API_USERS.getUserById(event.target.id, (result, status, err) => {
            console.log("User found: " + JSON.stringify(result));
            this.setState(
            {
                isEditing: true,
                editedUser: result
            })

            //console.log("Item found: " + JSON.stringify(this.state.editedSensor));

        });
    }

    handleDeleteEvent(event) {
        //console.log("ENTERED -- Successfully deleted sensor with id: " + event.target.id);

        return API_USERS.deleteUser(event.target.id, (result, status, err) => {
            if (result !== null && status === 200) {
                console.log("Successfully deleted user with id: " + result);

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
        this.fetchUsers();
    }

    reload() {
        this.setState({
            isLoaded: false
        });
        this.toggleForm();
        this.fetchUsers();
    }

    render() {
        return (
            <div>
                {LoginForm.role == "ADMIN" && <CardHeader>
                    <strong> Users List </strong>
                </CardHeader>}


                {LoginForm.role == "ADMIN" && <Card>
                    <br/>
                    <Row>
                        <Col sm={{size: '8', offset: 1}}>
                            <Button color="primary" onClick={this.toggleForm}>Add User </Button>
                        </Col>
                    </Row>
                    <br/>
                    <Row>
                        <Col sm={{size: '8', offset: 1}}>
                            {this.state.isLoaded && <UserTable tableData = {this.state.tableData} handleEventUpdate = {this.handleUpdateEvent} handleDeleteEvent = {this.handleDeleteEvent}/>}
                            {this.state.errorStatus > 0 && <APIResponseErrorMessage
                                                            errorStatus={this.state.errorStatus}
                                                            error={this.state.error}
                                                        />   }
                        </Col>
                    </Row>
                    <Row>
                        <Col sm={{size: '8', offset: 1}}>
                            {this.state.isEditing && <EditUserRow reloadHandler={this.reload2} userEdit={this.state.editedUser} cancelAction={this.cancelAction}/>}
                        </Col>
                    </Row>
                </Card>}

                {LoginForm.role == "ADMIN" && <Modal isOpen={this.state.selected} toggle={this.toggleForm}
                       className={this.props.className} size="lg">
                    <ModalHeader toggle={this.toggleForm}> Add User: </ModalHeader>
                    <ModalBody>
                        <UserForm reloadHandler={this.reload}/>
                    </ModalBody>
                </Modal>}

                {LoginForm.role != "ADMIN" && <Unauthorized/>}
            </div>
        )

    }
}

export default UserContainer;