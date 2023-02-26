import React, { Component } from 'react'

export const Authentication = React.createContext();


class AuthenticationContext extends Component {
    state = {
        isLoggedIn : false,
        username: undefined,
        email : undefined,
        password : undefined
      };
    
      onLoginSuccess = authState =>
      {
          this.setState({
            ...authState,
            // username : authState.username,
            // email : authState.email,
            // password: authState.password,
            isLoggedIn : true
          })
      }
    
    
      onLogoutSuccess = () => {
        this.setState({
           isLoggedIn : false,
           username : undefined
        })
      }  
  render() {
    return (
      <Authentication.Provider value={{
        state: {...this.state},
        onLoginSuccess: this.onLoginSuccess,
        onLogoutSuccess: this.onLogoutSuccess 
      }}>
         {this.props.children}
      </Authentication.Provider>
    )
  }
}

export default AuthenticationContext;