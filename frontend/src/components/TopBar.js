import React, { Component } from 'react';
import { withTranslation } from 'react-i18next';
import {Link} from 'react-router-dom';
import {Authentication} from '../shared/AuthenticationContext';

class TopBar extends Component {
  
  static contextType = Authentication;
  

  render() {
    const { t } = this.props;
    const {state, onLogoutSuccess, } = this.context;
    const {isLoggedIn,username} = state;
    let links = (
        <ul className='navbar-nav ms-auto'>
            <li>
            <Link className='nav-link' to="/login">
                           {t('Login')} 
            </Link>          
            </li>
            <li>
                        <Link className='nav-link' to={'/signup'}>
                            {t('Sign Up')}
                        </Link>
                        
                     </li>
        </ul> );
        
                if(isLoggedIn){
                    links = (
                        <ul className='navbar-nav ms-auto'>
                            <li>
                                <Link className='nav-link' to="/calculate">
                                {t('Credit Calculator')}
                                </Link>
                            </li>
                            <li>
                                <Link className='nav-link' to={`/user/${username}`}>
                                     {username}
                                </Link>
                            </li>
                            <li  style={{cursor: 'pointer'}}  onClick={onLogoutSuccess}>
                                <Link className='nav-link' to={'/login'}>
                                    {t('Logout')}
                                </Link> 
                            </li>
                        </ul> 
                    );
                }
        
        
            return (
              <div className='shadow-sm bg-light mb-2'>
                <navbar className='navbar navbar-light container navbar-expand'>
                <Link className='navbar-brand' to="/">
                    {t('Credit Application')} 
                </Link>
                    {links}
                
        
              </navbar>
              </div>  
              
            )
  }
}

export default withTranslation()(TopBar);