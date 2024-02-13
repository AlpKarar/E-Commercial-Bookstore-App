import React from "react";
import Header from "./Header";

const AboutPage = () => {
    return (
        <>
          <Header/>
          <div class="d-flex align-items-center vh-100">
            <div class="container w-30 h-15 text-center" style={{marginBottom: 200 + 'px'}}>
              <h1>About</h1>
              <div>Book List App is an application to store book list of lovely readers.</div>
              <div>You can also add your favorite books in own wishlist.</div>
            </div>
          </div>
        </>
    )
}

export default AboutPage;