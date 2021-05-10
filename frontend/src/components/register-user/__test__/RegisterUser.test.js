import React from "react";
import{ act, render, cleanup, fireEvent } from "@testing-library/react";
import RegisterUser from "../RegisterUser";
import renderer from "react-test-renderer";

afterEach(cleanup)

it("renders without crashing", ()=> {
    const addUser = jest.fn();
   render(<RegisterUser addUser={addUser} />) 
})

it("updates on Name change",()=>{
    const addUser = jest.fn();
    const { getByPlaceholderText } = render(<RegisterUser addUser={addUser} />);
    const input = getByPlaceholderText('Name');
    fireEvent.change(input, {target: {value:'John'}})
    expect(input.value).toBe('John')

})

const setup =(placeholder, value) =>{
    const addUser = jest.fn();
    const { getByPlaceholderText } = render(<RegisterUser addUser={addUser} />);
    const input = getByPlaceholderText(placeholder);
    fireEvent.change(input, {target: {value:value}});
    return input.value;

}

it("Surname on change test", ()=>{
    const value = setup("Surname", "Doe");
    expect(value).toBe("Doe");
})

it("Email on change test", ()=>{
    const value = setup("Email", "admin@gmail.com");
    expect(value).toBe("admin@gmail.com");
})

it("Username on change test", ()=>{
    const value = setup("Username", "steve");
    expect(value).toBe("steve");
})

it("onSubmit with addUser callback prop", ()=>{
    const addUser = jest.fn();
    const {getByText} = render(<RegisterUser addUser ={addUser}/>)
    const button = getByText("Submit");
    button.dispatchEvent(new MouseEvent('click'))
    expect(addUser).toHaveBeenCalledTimes(1)
})

it("matches snapshot", ()=>{
    const addUser = jest.fn();
    const tree = renderer.create(<RegisterUser addUser={addUser}/>);
    expect(tree).toMatchSnapshot()
})