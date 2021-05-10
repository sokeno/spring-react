import React from 'react';
import UserInfo from '../user-info/UserInfo';
import PropTypes from "prop-types";

const Users = ({ users, removeUser }) => {
    return (
        <div>
            {users.map(user => (
                <UserInfo user={user} removeUser = {removeUser} key={user.id} />
            ))}
        </div>
    )
}
Users.propTypes = {
    users: PropTypes.array,
    removeUser: PropTypes.func.isRequired
}

export default Users;
