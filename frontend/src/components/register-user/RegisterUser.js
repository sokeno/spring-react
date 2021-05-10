import React, {useState} from 'react'
import InputRow from "../row/InputRow";
import Button from "../button/Button";
import PropTypes from "prop-types";
const RegisterUser = ({addUser}) => {
    const [user,setUser] = useState({
        name:'',
        surname:'',
        email:'',
        username:'',
        password:'',
        confirm_password:''
    });

    const handleInputChange = (e) =>{
        const {name, value} = e.target;

        setUser({
            ...user,
            [name] : value
        })
    }
    const onSubmit =(e) =>{
        e.preventDefault();
        if(user['password'] === user['confirm_password']){
            addUser(user);
            setUser({
                'name':'',
                'surname':'',
                'email':'',
                'username':'',
                'password':'',
                'confirm_password':''
            })

        }else{
            console.log("Password does not match!")
        }
    }
    return (
        <div className="formCard">
            <form onSubmit ={onSubmit}>
                <div className="pill"></div>
                <br/>
                <div style={row}>
                    <InputRow label ="Name">
                    <input
                            type="text"
                            name="name"
                            value={user['name']}
                            placeholder="Name"
                            onChange = {handleInputChange}
                    />
                
                    </InputRow>
                    <InputRow label ="Surname">
                    <input
                            type="text"
                            name="surname"
                            value={user['surname']}
                            placeholder="Surname"
                            onChange = {handleInputChange}
                    />
                
                    </InputRow>
                </div>
                <div style={row}>
                    <InputRow label ="Email">
                    <input
                            type="email"
                            name="email"
                            value={user['email']}
                            placeholder="Email"
                            onChange = {handleInputChange}
                    />
                
                    </InputRow>
                    <InputRow label ="Username">
                    <input
                            type="text"
                            name="username"
                            value={user['username']}
                            placeholder="Username"
                            onChange = {handleInputChange}
                    />
                
                    </InputRow>
                </div>
                <div style={row}>
                    <InputRow label ="Password">
                    <input
                            type="password"
                            name="password"
                            value={user['password']}
                            placeholder="Password"
                            onChange = {handleInputChange}
                    />
                
                    </InputRow>
                    <InputRow label ="Confirm Password">
                    <input
                            type="password"
                            name="confirm_password"
                            value={user['confirm_password']}
                            placeholder="Confirm Password"
                            onChange = {handleInputChange}
                    />
                
                    </InputRow>
                </div>
                <div style={button}>
                    <Button />

                </div>

            </form>
        </div>
    )
}

const row = {
    display:'flex',
    flexDirection: 'row',
    justifyContent: 'center'
}
const button ={
    display:'flex',
    justifyContent: 'flex-end',
    padding: '10px'

}

RegisterUser.propTypes = {
    addUser: PropTypes.func.isRequired
}
export default RegisterUser;