import React from "react";

const Input = (props) => {
    const {label,error,name,onChange,type} = props;
    const className = error ? "form-control is-invalid": "form-control" 
    return(
        <div class="form-group">
            <label>{label}</label>
            <input name={name} className={className} onChange={onChange} type={type}></input>
            <div className="invalid-feedback">{error}</div>
            <br></br>
        </div>
    )
} 

export default Input;