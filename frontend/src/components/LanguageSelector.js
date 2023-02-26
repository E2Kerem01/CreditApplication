import React from 'react';
import { withTranslation, WithTranslation } from 'react-i18next';
import { changeLanguage } from '../api/apiCalls';

const LanguageSelector = (props) => {

   const onChangeLanguage = language => {
        const { i18n } = props;
        i18n.changeLanguage(language);
        changeLanguage(language);
    };

    return (
        <div>
            <div className='container' id = 'languageSelector'>
                <img src="https://www.countryflagicons.com/FLAT/24/TR.png" onClick={() => onChangeLanguage('tr')} alt="Turkish Flag" style={{cursor: 'pointer'}}></img>
                <img src="https://www.countryflagicons.com/FLAT/24/GB.png" onClick={() => onChangeLanguage('en')} alt="England Flag" style={{cursor: 'pointer'}}></img>
            </div>
        </div>
    );
};

export default withTranslation()(LanguageSelector);