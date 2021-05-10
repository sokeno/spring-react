import React from "react";
import{ act, render, cleanup} from "@testing-library/react";
import Header from "../Header";
import renderer from "react-test-renderer";

afterEach(cleanup)

it("renders <Header/> without crashing", ()=>{
    const { container} = render(<Header/>)
    expect(container.textContent).toBe("User Registration")
})

it("renders Header with a prop", ()=>{
    const title = "My Header";
    const {container} = render(<Header title= {title}/>)
    expect(container.textContent).toBe('My Header');
})

it("matches snapshot",()=>{
    const tree = renderer.create(<Header/>).toJSON();
    expect(tree).toMatchSnapshot();
})