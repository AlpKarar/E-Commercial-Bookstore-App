import React from "react";
import Header from "./Header";

const ForgotPasswordPage = () => {
    return (
        <>
          <Header/> 
          <div class="d-flex align-items-center" style={{height: 'calc(' + 100 + 'vh - ' + 56 + 'px)'}}>
            <div class="container w-25 h-auto border p-10" style={{marginBottom: 200 + 'px'}}>
              <h1>Password Update</h1>  
              <div class="form-outline my-4">
                <input type="email" id="form2Example1" class="form-control" />
                <label class="form-label" for="form2Example1">Email address</label>
              </div>
              <div class="form-outline mb-4">
                <input type="password" id="form2Example3" class="form-control" />
                <label class="form-label" for="form2Example2">New Password</label>
              </div>
              <div class="form-outline mb-4">
                <input type="password" id="form2Example2" class="form-control" />
                <label class="form-label" for="form2Example2">New Password Again</label>
              </div>

              <button type="button" class="btn btn-primary btn-block mb-4">Update Password</button>
            </div>
          </div>
        </>
    )
}

export default ForgotPasswordPage;