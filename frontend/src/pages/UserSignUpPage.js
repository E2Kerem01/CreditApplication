import React from "react";
import {signup} from "../api/apiCalls";
import Input from "../components/Input";
import {withTranslation} from 'react-i18next';
import ButtonWithProgress from "../components/ButtonWithProgress";
import { withApiProgress } from "../shared/ApiProgress";


class UserSignUpPage extends React.Component{

    state = {
        username: null,
        email: null,
        password: null,
        passwordRepeat: null,
        responseMessage: "",
        errors: {
            
        }

    };

    

    onChange = event => {
        const { t } = this.props;
        const {name, value} = event.target;
        const errors = {...this.state.errors};
        errors[name] = undefined

        if(name==='password' || name==='passwordRepeat'){
            if(name==='password' && value !== this.state.passwordRepeat ){
                errors.passwordRepeat = t('Password mismatch');
            } 
            else if(name==='passwordRepeat' && value !== this.state.password){
                errors.passwordRepeat = t('Password mismatch');
            }
            else{
                errors.passwordRepeat = undefined
            }
        }

  
        this.setState({
            [name] : value,  errors
        });
    };
    


    onClickSignUp = async event => {
        event.preventDefault();
        const {username,email,password} = this.state;
        const body = {
            username,
            email,
            password
        };
        
        //const { push } = this.props.history;

        try {
            const response =  await signup(body);
            this.setState({ responseMessage: response.data.message });
            //push('/login')
        } catch (error) {
            if(error.response.data.validationErrors){
                this.setState({ errors: error.response.data.validationErrors });
            }
    
        }
    
    };

    

    // onChangeColor = color => {
    //     const { username } = this.props;
    //     username.onChangeColor(blue);
    // }



    render(){
        const { errors,responseMessage} = this.state;
        const { username,email,password, passwordRepeat } = errors;
        const {pendingApiCall,t} = this.props;
        return(
            <div className="container">
                <form  id = 'signPageArea'>
                    <h1 className="text-center">{t('Credit Application')}</h1>
                    <Input name="username" label={t('Username')} error={username} onChange={this.onChange}></Input>
                    <Input name="email" label={t('Email')} error={email} onChange={this.onChange}></Input>
                    <Input name="password" label={t('Password')} error={password} onChange={this.onChange} type="password"></Input>
                    <Input name="passwordRepeat" label={t('Password Repeat')} error={passwordRepeat} onChange={this.onChange} type="password"></Input>
                    <div className="text-center">
                        <ButtonWithProgress 
                            onClick={this.onClickSignUp} 
                            disabled={pendingApiCall || passwordRepeat!==undefined}
                            pendingApiCall={pendingApiCall}
                            text={t('Sign Up')}
                            >
                             {pendingApiCall &&  <span className="spinner-border spinner-border-sm"> </span>  } 
                             {t('Sign Up')}
                        </ButtonWithProgress>
                    </div>
                    <br></br>
                    <div className='container' id='signupresponse' ><p onChange={this.onChange}>{responseMessage}</p></div>
                    <br></br>
                </form>
            </div>
        );
    }
}


const UserSignUpPageWithApiProgress = withApiProgress(UserSignUpPage,'/api/v1/users')
const UserSignUpPageWithTranslations = withTranslation()(UserSignUpPageWithApiProgress)
export default UserSignUpPageWithTranslations;