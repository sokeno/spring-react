import React from "react";
import{ act, render, cleanup, fireEvent } from "@testing-library/react";
import Users from "../Users";
import renderer from "react-test-renderer";

afterEach(cleanup)

it("renders without crashing", () => {
    const removeUser = jest.fn();

    const users = [
        {   id:1,
            name:"Peter",
            surname:'Parker',
            username:'Pete',
            email:'pete@gmail.com'
        },
        {   id:2,
            name:"John",
            surname:'John',
            username:'John',
            email:'john@gmail.com'
        },
        {   id:3,
            name:"Mary",
            surname:'Mary',
            username:'Mary',
            email:'mary@gmail.com'
        }
    ];

    //Simulate async call
    global.fetch = jest.fn().mockImplementation(() =>
        Promise.resolve({
            json: () => Promise.resolve(users)
        }));

    const { container } = render(<Users users={users} removeUser={removeUser} />)
    expect (container.textContent).toContain(users[0].email);
    expect (container.textContent).toContain(users[0].name);
    expect (container.textContent).toContain(users[2].surname);
    expect (container.textContent).toContain(users[1].username);
})

it("matches snapshot", ()=>{
    const removeUser = jest.fn();
    const users =[]
    const tree = renderer.create(<Users users={users} removeUser={removeUser}/>).toJSON();
    expect(tree).toMatchSnapshot();

})