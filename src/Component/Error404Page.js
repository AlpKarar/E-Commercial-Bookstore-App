import React from "react";
import Header from "./Header";

const Error404Page = () => {
    return (
        <>
            <Header/>
            <div class="d-flex align-items-center vh-100">
                <div class="container w-30 h-15" style={{marginBottom: 200 + 'px'}}>
                    <h1>404 Page</h1>
                    <br/>
                    <div>Page Not Found...</div>
                </div>
            </div>
        </>
    )
}

export default Error404Page;