import axios from "axios";



export const signup = (body) => {
  return  axios
        .post('/api/v1/users', body);
        
}


export const login = creds => {
   return axios
        .post('/api/v1/auth', {}, {auth: creds});

}

export const changeLanguage = language => {

   axios.defaults.headers['accept-language'] = language;

}

export const calculate = (body) => {
   
   return axios 
         .post('/api/v1/calculate', body)

}

// export const deleteUser = (body) => {
   
//    return axios 
//          .post('/api/v1/calculate');

// }



