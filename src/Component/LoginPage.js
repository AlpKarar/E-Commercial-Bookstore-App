import React from "react";
import { Link } from "react-router-dom";
import Header from "./Header";

const LoginPage = () => {
    return (
        <>
          <Header/>
          <div className="d-flex align-items-center" style={{height: 'calc(' + 100 + 'vh - ' + 56 + 'px)'}}>
            <div className="container w-25 h-auto border p-10 text-center" style={{marginBottom: 100 + 'px'}}>
              <h1>Log In</h1>  
              <div className="form-outline my-4">
                <input type="email" id="form2Example1" className="form-control" />
                <label className="form-label" htmlFor="form2Example1">Email address</label>
              </div>
              <div className="form-outline mb-4">
                <input type="password" id="form2Example2" className="form-control" />
                <label className="form-label" htmlFor="form2Example2">Password</label>
              </div>

              <div className="row mb-4">
                <div className="col d-flex justify-content-center">
                  <div className="form-check">
                    <input className="form-check-input" type="checkbox" value="" id="form2Example31"/>
                    <label className="form-check-label" htmlFor="form2Example31"> Remember me </label>
                  </div>
                </div>

                <div className="col">
                  <Link to="/updatePassword">Forgot password?</Link>
                </div>
              </div>


              <button type="button" className="btn btn-primary btn-block mb-4">Sign in</button>

              <div className="text-center">
                <p>Not a member? <Link to="/signup">Register</Link></p>
              </div>
            </div>
          </div>
          
        </>
    )
}

export default LoginPage;