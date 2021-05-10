import React from 'react';
import AccountCircleIcon from "@material-ui/icons/AccountCircle";
import IconButton from "@material-ui/core/IconButton";
import CreateIcon from "@material-ui/icons/Create";
import DeleteIcon from "@material-ui/icons/Delete";
import PropTypes from "prop-types";

const UserInfo = ({ user, removeUser }) => {
    const { id, name, surname, email, username } = user;

    const infoStyle = {
        backgroundColor: (id % 2) == 0 ? '#c8d4f7cc' : '',
        padding:'10px',
        borderBottom: '1px #cc dotted',
        display:'flex',
        alignItems:'center',
        justifyContent:'space-between',
        justifyItems:'flex-start',
        marginLeft:'20px',
        marginRight:'20px'
    }

    const deleteUser = (id) => {
        removeUser(id);
    }

    return (
        <div style={infoStyle}>
            <div style = {accountStyle}>
                <AccountCircleIcon style = {{color: '#138a04', margin:"0px 10px 0px 0px"}} />
                <p>{name}</p>
                &nbsp;
                <p>{surname}</p>
            </div>
            <p>{email}</p>
            <p>{username}</p>
            <div style ={buttons}>
                <IconButton style= {{color: '#888990'}}>
                    <CreateIcon />
                </IconButton>
                <IconButton aria-label="delete-button" color ="secondary" onClick ={(e) => deleteUser(id)}>
                    <DeleteIcon />
                </IconButton>
            </div>
            
        </div>
    )
}

const accountStyle = {
    display:'flex',
    justification:'space-between'
}
const buttons = {
    display: 'flex'
}

UserInfo.propTypes = {
    user: PropTypes.object,
    removeUser: PropTypes.func.isRequired
}
export default UserInfo;