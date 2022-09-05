import React from 'react';

import {Container, Jumbotron} from 'reactstrap';
import LoginForm from './login-form';

const backgroundStyle = {
    backgroundPosition: 'center',
    backgroundSize: 'cover',
    backgroundRepeat: 'no-repeat',
    width: "100%",
    height: "1920px",
    backgroundColor: "#00b6e3"
};

const textStyle = {
    color: 'white',
    textAlign: "center"
};

class Home extends React.Component {


    render() {

        return (

            <div>
                <Jumbotron fluid style={backgroundStyle}>
                    <Container fluid>
                        <h1 className="display-3" style={textStyle}>Online Energy Utility Platform</h1>
                        <p className="lead" style={textStyle}> <b>Enabling real time monitoring of smart devices and senzors</b> </p>
                        <hr className="my-2"/>
                        <p  style={textStyle}> <b>This assignment represents the first module of the distributed software system - Assignment 1 -
                            for the Distributed Systems course. </b> </p>

                        <LoginForm history={this.props.history}/>
                    </Container>
                </Jumbotron>

            </div>
        )
    };
}

export default Home
