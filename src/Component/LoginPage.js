import React from "react";
import { Link } from "react-router-dom";
import Header from "./Header";

const LoginPage = () => {
    return (
        <>
          <Header/>
          <div class="d-flex align-items-center" style={{height: 'calc(' + 100 + 'vh - ' + 56 + 'px)'}}>
            <div class="container w-25 h-auto border p-10 text-center" style={{marginBottom: 100 + 'px'}}>
              <h1>Log In</h1>  
              <div class="form-outline my-4">
                <input type="email" id="form2Example1" class="form-control" />
                <label class="form-label" for="form2Example1">Email address</label>
              </div>
              <div class="form-outline mb-4">
                <input type="password" id="form2Example2" class="form-control" />
                <label class="form-label" for="form2Example2">Password</label>
              </div>

              <div class="row mb-4">
                <div class="col d-flex justify-content-center">
                  <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="" id="form2Example31"/>
                    <label class="form-check-label" for="form2Example31"> Remember me </label>
                  </div>
                </div>

                <div class="col">
                  <Link to="/updatePassword">Forgot password?</Link>
                </div>
              </div>


              <button type="button" class="btn btn-primary btn-block mb-4">Sign in</button>

              <div class="text-center">
                <p>Not a member? <Link to="/signup">Register</Link></p>
              </div>
            </div>
          </div>
          
        </>
    )
}

export default LoginPage;