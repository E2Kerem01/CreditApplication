import React from "react";
import LoginPage from "../pages/LoginPage";
import LanguageSelector from "../components/LanguageSelector";
import UserSignUpPage from '../pages/UserSignUpPage';
import { BrowserRouter as Router ,Route, Redirect, Switch} from 'react-router-dom';
import HomePage from "../pages/HomePage";
import TopBar from "../components/TopBar";
import UserPage from "../pages/UserPage";
import CreditPage from "../pages/CreditPage";
import {Authentication} from "../shared/AuthenticationContext";

class App extends React.Component {

  
  static  contextType = Authentication;
  render(){
      const isLoggedIn = this.context.state.isLoggedIn;
      const username = this.context.state.username;
      //const isLoginSuccess = this.context.state.isLoginSuccess;

      return (
      <div>
          <Router>
            <TopBar />
            <Switch> 
                <Route exact path="/" component={HomePage}/>
                {!isLoggedIn && <Route path="/login" component={LoginPage} />}

                <Route path="/signup" component={UserSignUpPage}/>

                <Route path="/user/:username" component={props => {
                  return <UserPage {...props} username={username} />
                }}/>

                <Route path="/calculate" component={props => {
                  return <CreditPage {...props} username={username}/>
                }}/>

                <Redirect to="/" />
            </Switch>
          </Router>
          <LanguageSelector/>
          
      </div>
    );
  }

}

export default App;
