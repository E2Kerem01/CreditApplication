import React, { Component } from 'react';
import { withRouter} from 'react-router-dom';
import { Authentication } from '../shared/AuthenticationContext';

const ProfileCards = props => {
  
    const pathUsername = props.match.params.username;
    const loggedInUsername = props.username;
    let message = 'We cannot edit';
    if(pathUsername=== loggedInUsername){
           message = 'We can edit'
           
    }
    return (
        <div className='container'>
                {message}
        </div>
    ); 
};



class ProfileCardsContextWrapper extends Component {
    static contextType = Authentication;
  render() {
    return (
      <ProfileCards {...this.props} username={this.context.state.username}/>
    );
  }
}



export default withRouter(ProfileCardsContextWrapper);