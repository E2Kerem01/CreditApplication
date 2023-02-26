import i18n from "i18next";
import {initReactI18next} from 'react-i18next';


i18n.use(initReactI18next).init({
    resources: {
        en: {
            translations:{
                'Login' : 'Login',
                'Credit Application': 'Credit Application',
                'Sign Up' : 'Sign Up',
                'Password mismatch' : 'Password mismatch',
                'Username' : 'Username',
                'Email': 'Email',
                'Password' : 'Password',
                'Password Repeat' : 'Password Repeat',
                'Identification Number' : 'Identification Number',
                'Name Surname' : 'Name Surname',
                'Monthly Income' : 'Monthly Income',
                'Birth Date' : 'Birth Date',
                'Phone' : 'Phone',
                'Guarantee' : 'Guarantee',
                'Calculate' : 'Calculate',
                'Logout' : 'Logout',
                'Credit Calculator' : 'Credit Calculator'
            }
        },

        tr: {
            translations: {
                'Login' : 'Giriş Yap',
                'Credit Application' : 'Kredi Başvuru Kayıt',
                'Sign Up' : 'Kayıt Ol',
                'Password mismatch' : 'Aynı şifreyi giriniz.',
                'Username' : 'Kullanıcı Adı',
                'Email' : 'Email',
                'Password' : 'Şifre',
                'Password Repeat' : 'Şifreyi Tekrarla',
                'Identification Number' : 'TC Kimlik No',
                'Name Surname' : 'Ad Soyad',
                'Monthly Income' : 'Aylık Gelir',
                'Birth Date' : 'Doğum Tarihi',
                'Phone' : 'Telefon',
                'Guarantee' : 'Teminat',
                'Calculate' : 'Hesapla',
                'Logout' : 'Çıkış Yap',
                'Credit Calculator' : 'Kredi Hesaplama'
            }
        }
    },

    fallbackLng: 'tr',
    ns: ['translations'],
    defaultNS: 'translations',
    keySeparator: false,
    interpolation: {
        escapeValue:  false,
        formatSeparator: ','
    },
    react: {
        wait: true
    }
});

export default i18n