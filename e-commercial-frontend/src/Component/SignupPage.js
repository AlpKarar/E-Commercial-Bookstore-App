import React from "react";
import Header from "./Header";
import axios from 'axios';

const SignupPage = () => {
    return (
        <>
          <Header/>
          <div class="d-flex align-items-center" style={{height: 'calc(' + 100 + 'vh - ' + 56 + 'px)'}}>
            <div class="container w-25 h-auto border p-10  text-center" style={{marginBottom: 200 + 'px'}}>
              <h1>Sign Up</h1>
              <div class="form-outline my-4">
                <input type="email" id="email" class="form-control" />
                <label class="form-label" for="form2Example1">Email address</label>
              </div>
              <div class="form-outline mb-4">
                <input type="text" id="username" class="form-control" />
                <label class="form-label" for="form2Example1">Username</label>
              </div>
              <div class="form-outline mb-4">
                <input type="password" id="password" class="form-control" />
                <label class="form-label" for="form2Example2">Password</label>
              </div>

              <button type="button" class="btn btn-primary btn-block mb-4">Sign up</button>
            </div>
          </div>
        </>
    )
}

export default SignupPage;