import React from "react";
import{ act, render, cleanup, fireEvent } from "@testing-library/react";
import UserInfo from "../UserInfo";
import renderer from "react-test-renderer";

afterEach(cleanup)

it("render without crashing", async ()=>{
    const removeUser = jest.fn();
    const fakeUser = {
        id:1,
        name:'John',
        surname:'Doe',
        email:'john@gmail.com',
        username:'johny'

    }
    //Simulate async call
    global.fetch = jest.fn().mockImplementation(() =>
        Promise.resolve({
            json: () => Promise.resolve(fakeUser)
        }));

    let utils;
    await act( async () => {
        utils = render(<UserInfo removeUser={removeUser} user={fakeUser}/>)
    })

    const {container} = utils;

    expect(container.textContent).toContain(fakeUser.name);
    expect(container.textContent).toContain(fakeUser.surname);
    expect(container.textContent).toContain(fakeUser.email);
    expect(container.textContent).toContain(fakeUser.username);

    
})

it("onclick deleteUser fire",()=>{
    const removeUser = jest.fn();
    const fakeUser = {
        id:1,
        name:'John',
        surname:'Doe',
        email:'john@gmail.com',
        username:'johny'

    }

    const {getByLabelText} = render(<UserInfo user={fakeUser} removeUser={removeUser}/>)
    const deleteButton = getByLabelText('delete-button')

    act(()=>{
        deleteButton.dispatchEvent(new MouseEvent('click', {bubbles:true}))
    })
    expect(removeUser).toHaveBeenCalledTimes(1)

})