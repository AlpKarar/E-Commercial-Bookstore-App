import React from "react";
import Header from "./Header";

const SignupPage = () => {
    return (
        <>
          <Header/>
          <div className="d-flex align-items-center" style={{height: 'calc(' + 100 + 'vh - ' + 56 + 'px)'}}>
            <div className="container w-25 h-auto border p-10  text-center" style={{marginBottom: 200 + 'px'}}>
              <h1>Sign Up</h1>
              <div className="form-outline my-4">
                <input type="email" id="form2Example1" className="form-control" />
                <label className="form-label" for="form2Example1">Email address</label>
              </div>
              <div className="form-outline mb-4">
                <input type="text" id="form2Example3" className="form-control" />
                <label className="form-label" for="form2Example1">Username</label>
              </div>
              <div className="form-outline mb-4">
                <input type="password" id="form2Example2" className="form-control" />
                <label className="form-label" for="form2Example2">Password</label>
              </div>

              <button type="button" className="btn btn-primary btn-block mb-4">Sign up</button>
            </div>
          </div>
        </>
    )
}

export default SignupPage;