import React from "react";
import LoginForm from "./home/login-form";
import Unauthorized from "./unauthorized";
import { withRouter } from 'react-router-dom';


class AdminHome extends React.Component {

    constructor (props){
        super(props);
    }

    redirectToAdminClients(e) {
        e.preventDefault();
        this.props.history.push('/users');
    }
    redirectToAdminDevices(e) {
        e.preventDefault();
        this.props.history.push('/devices');
    }
    redirectToAdminSensors(e) {
        e.preventDefault();
        this.props.history.push('/senzors');
    }
    render() {
        return (
            <div>
                {LoginForm.role=="ADMIN" && <button onClick={(e) => this.redirectToAdminClients(e)}>Manage clients</button>}
                {LoginForm.role=="ADMIN" && <button onClick={(e) => this.redirectToAdminDevices(e)}>Manage devices</button>}
                {LoginForm.role=="ADMIN" && <button onClick={(e) => this.redirectToAdminSensors(e)}>Manage sensors</button>}
                {LoginForm.role=="CLIENT" && <Unauthorized/>}
                {LoginForm.role=="" && <Unauthorized/>}
            </div>
        );
    }
}

export default withRouter(AdminHome);