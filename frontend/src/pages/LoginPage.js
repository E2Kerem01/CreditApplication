import React, { Component } from 'react'
import Input from '../components/Input';
import { login} from "../api/apiCalls";
import {withTranslation} from 'react-i18next';
import ButtonWithProgress from '../components/ButtonWithProgress';
import { withApiProgress } from '../shared/ApiProgress';
import { Authentication } from '../shared/AuthenticationContext';


class LoginPage extends Component {
    static contextType = Authentication;
    state = {
        username : null,
        password : null,
        error: null
    };

   
    onChange = event => {
        const { name,value} = event.target;
        this.setState({
            [name]: value,
            error : null
        })
    }
  
    
  onClickLogin = async event => {
    event.preventDefault();
    const {username,password} = this.state;
    const {onLoginSuccess} = this.context;
    const creds = {
      username,
      password
    };

    const { push } = this.props.history;

    this.setState({
      error:null
    })
    try {
      const response = await login(creds)
      push('/');

      
      const authState = {
        ...response.data,
        password
      }

      onLoginSuccess(authState);
    } catch (apiError) {
      this.setState({
        error: apiError.response.data.message
      })
    }
    
  }  
   

  render() {
    const { t,pendingApiCall } = this.props;
    const {username,password,error} = this.state;
    const buttonEnabled = username && password;

    return (
      <div className='container'>
        
            <form id='loginPageArea'>
                <h1 className='text-center'>{t('Login')}</h1>
                <Input label={t('Username')} name="username" onChange={this.onChange}></Input>
                <Input label={t('Password')} name="password" type='password' onChange={this.onChange}></Input>
                {error && <div className="alert alert-danger">
                    {error} Please Try Again...
                </div>}
                <div className='text-center'>
                    <ButtonWithProgress 
                     onClick={this.onClickLogin} 
                     disabled = {!buttonEnabled || pendingApiCall} 
                     pendingApiCall={pendingApiCall}
                     text = {t('Login')}
                     >{t('Login')} 
                     
                    </ButtonWithProgress>
                </div>
            </form>
      </div>
    )
  }
}

  // const LoginPagewithTranslation = withTranslation()(LoginPage)

  // export default withApiProgress(LoginPagewithTranslation, '/api/v1/auth');


const LoginPageWithApiProgress = withApiProgress(LoginPage,'/api/v1/auth')
const LoginPagewithTranslation = withTranslation()(LoginPageWithApiProgress)
export default LoginPagewithTranslation;