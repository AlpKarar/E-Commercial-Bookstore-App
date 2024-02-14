import React from "react";
import Header from "./Header";

const ForgotPasswordPage = () => {
    return (
        <>
          <Header/> 
          <div className="d-flex align-items-center" style={{height: 'calc(' + 100 + 'vh - ' + 56 + 'px)'}}>
            <div className="container w-25 h-auto border p-10" style={{marginBottom: 200 + 'px'}}>
              <h1>Password Update</h1>  
              <div className="form-outline my-4">
                <input type="email" id="form2Example1" className="form-control" />
                <label className="form-label" for="form2Example1">Email address</label>
              </div>
              <div className="form-outline mb-4">
                <input type="password" id="form2Example3" className="form-control" />
                <label className="form-label" for="form2Example2">New Password</label>
              </div>
              <div className="form-outline mb-4">
                <input type="password" id="form2Example2" className="form-control" />
                <label className="form-label" for="form2Example2">New Password Again</label>
              </div>

              <button type="button" className="btn btn-primary btn-block mb-4">Update Password</button>
            </div>
          </div>
        </>
    )
}

export default ForgotPasswordPage;