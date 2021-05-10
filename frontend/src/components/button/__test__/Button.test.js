import React from "react";
import{ act, render, cleanup, fireEvent } from "@testing-library/react";
import Button from "../Button";
import renderer from "react-test-renderer";

afterEach(cleanup)

it("renders without crashing", ()=>{
   const { container }=  render(<Button/>)
   expect (container.textContent).toBe("Submit")
})

it("matches snapshot", ()=>{
    const tree =  renderer.create(<Button/>);
    expect(tree).toMatchSnapshot();
})