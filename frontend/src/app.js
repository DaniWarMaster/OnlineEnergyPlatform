import React from 'react'
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom'
import NavigationBar from './navigation-bar'
import Home from './home/home';
import PersonContainer from './person/person-container'
import DevicesContainer from './devices/devices-container'
import SenzorsContainer from './senzors/senzors-container'
import UserContainer from './users/user-container'
import AdminHome from './admin-home';
import ClientHome from './client-home';

import ErrorPage from './commons/errorhandling/error-page';
import styles from './commons/styles/project-style.css';


class App extends React.Component {



    render() {

        return (
            <div className={styles.back}>
            <Router>
                <div>
                    <NavigationBar />
                    <Switch>

                        <Route
                            exact
                            path='/'
                            render={() => <Home/>}
                        />

                        <Route
                            exact
                            path='/person'
                            render={() => <PersonContainer/>}
                        />

                        <Route
                            exact
                            path='/admin-home'
                            render={() => <AdminHome/>}
                        />

                        <Route
                            exact
                            path='/client-home'
                            render={() => <ClientHome/>}
                        />

                        <Route
                            exact
                            path='/users'
                            render={() => <UserContainer/>}
                        />

                        <Route
                            exact
                            path='/devices'
                            render={() => <DevicesContainer/>}
                        />

                        <Route
                            exact
                            path='/senzors'
                            render={() => <SenzorsContainer/>}
                        />

                        {/*Error*/}
                        <Route
                            exact
                            path='/error'
                            render={() => <ErrorPage/>}
                        />

                        <Route render={() =><ErrorPage/>} />
                    </Switch>
                </div>
            </Router>
            </div>
        )
    };
}

export default App
