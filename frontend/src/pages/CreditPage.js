import React,{} from 'react'
import Input from '../components/Input';
import {calculate} from "../api/apiCalls";
import {withTranslation} from 'react-i18next';
import ButtonWithProgress from "../components/ButtonWithProgress";
import { withApiProgress } from "../shared/ApiProgress";
import { Authentication } from '../shared/AuthenticationContext';




class CreditPage extends React.Component {
 

  
  state = {
    username : this.props.username,
    tcIdentificationNumber : null,
    name : null,
    monthlyIncome : null,
    phoneNumber : null,
    birthDate : null,
    guarantee : null,
    responseMessage: "",
    errors: {
            
    }

  }

  
  
  
  onChange = event => {
    const {name, value} = event.target;
    const errors = {...this.state.errors};
    errors[name] = undefined
    this.setState({
        [name] : value,  
        error : null
    });
    this.setState({
      [name] : value,  errors
  });


  };
  static contextType = Authentication;

  
  
  onClickCalculate = async event => {
    event.preventDefault();
    
    const {username,tcIdentificationNumber,name,monthlyIncome,phoneNumber,birthDate,guarantee} = this.state;
    
    const body = {
        username,
        tcIdentificationNumber,
        name,
        monthlyIncome,
        phoneNumber,
        birthDate,
        guarantee,
        creditRating : 750
    };

    const { push } = this.props.history;
    
    

    try{
        
        const response = await calculate(body);
        this.setState({ responseMessage: response.data.message });
        
        push('/calculate');
        
  
    }catch (error){
        if(error.response.data.validationErrors){
            this.setState({errors: error.response.data.validationErrors});
        }
    }
  };


  render() {
    const { t,pendingApiCall } = this.props;
    const { errors,responseMessage } = this.state;
    const { tcIdentificationNumber,name,monthlyIncome, phoneNumber,birthDate } = errors;
    //const {responseData} = this.state;
    
    return (
      <div className='container'>
        <form id = 'creditPageArea'>
          <h1 className='text-center'>{t('Credit Application')}</h1>
              <Input label={t('Identification Number')} error={tcIdentificationNumber} name="tcIdentificationNumber" id="TcknAreas" onChange={this.onChange}></Input>
              <Input label={t('Name Surname')} error={name} name="name" id="nameAreas" onChange={this.onChange} ></Input>
              <Input label={t('Monthly Income')} error={monthlyIncome} name="monthlyIncome" id="BudgeAreas" onChange={this.onChange} ></Input>
              <Input label={t('Phone')} error={phoneNumber} name="phoneNumber" id="PhoneAreas" pattern="[0-9]{3}-[0-9]{2}-[0-9]{3}" onChange={this.onChange} ></Input>
              <Input label={t('Birth Date')} error={birthDate} name="birthDate" type='date' id="BirthDateAreas" onChange={this.onChange} ></Input>
              <Input label={t('Guarantee')}  name='guarantee' onChange={this.onChange}></Input>
            

              <div className='text-center'>
                    <ButtonWithProgress 
                     onClick={this.onClickCalculate} 
                     pendingApiCall={pendingApiCall}
                     text = {t('Calculate')}
                     >
                      {t('Calculate')} 
                    
                    </ButtonWithProgress>
              </div>

              <br></br>
              <div className='container' id='creditresponse' ><p onChange={this.onChange}>{responseMessage}</p></div>
              <br></br>
      
        </form>
      </div>
    );
  }
}

const CreditPageWithApiProgress = withApiProgress(CreditPage,'/api/v1/calculate')
const CreditPagewithTranslation = withTranslation()(CreditPageWithApiProgress)
export default CreditPagewithTranslation;