import React, { Component } from 'react'
import { withTranslation } from 'react-i18next';


class HomePage extends Component {

    
    render() {

    // const {isLoggedIn} = this.props;
    // let message = 'Lütfen size verilebilecek kredi miktarını öğrenmek için kayıt olunuz yada giriş yapınız...';
    // if(!isLoggedIn){
    //     message = ' '
    // }
    
    return (

            <div className='container' style={{marginTop: '50px'}}>
                <h1>Kredi Başvuru Sistemine Hoşgeldiniz...</h1>
                <div>
                    <p style={{border : '20px'}}>
                        {/* {{message}} */}
                    </p>               
                </div>
            </div>
        )
    }
}
  
const HomePageWithTranslations = withTranslation()(HomePage)
export default HomePageWithTranslations;