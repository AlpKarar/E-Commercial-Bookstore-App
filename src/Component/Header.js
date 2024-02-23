import {React, useState, useContext} from 'react';
import { Link } from 'react-router-dom';
import Context from '../ContextAPI/Context';

const Header = () => {
    const {isAuth, setAuth, allBooksInCart} = useContext(Context);
    const [profileName, setProfileName] = useState("Alpoo");

    return (
        <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
          <div className="container-fluid">
            <Link className="navbar-brand" to="/">Book List App</Link>
            <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
              <span className="navbar-toggler-icon"></span>
            </button>
            <div className="collapse navbar-collapse" id="navbarSupportedContent">
              <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                <li className="nav-item">
                  <Link className="nav-link active" aria-current="page" to="/about">About</Link>
                </li>
              </ul>
              <ul className="navbar-nav navbar-right gap-3 me-2">
                <li className="nav-item position-relative">
                  {allBooksInCart.length > 0 ?
                    <div className="position-absolute bg-danger text-white rounded-circle d-flex justify-content-center align-items-center"
                      style={{width: 15 + "px", height: 15 + "px", fontSize: 12.5 + "px", bottom: 15 + "px", left: 8.5 + "px"}}>
                        <span>{allBooksInCart.length}</span>
                    </div> : 
                    <></>
                  }
                  <Link to="/cart" className="text-decoration-none">
                    <svg xmlns="http://www.w3.org/2000/svg" width="19" height="19" fill="#fff" class="bi bi-cart-fill" viewBox="0 0 16 16">
                      <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5M5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4m7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4m-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2m7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2"/>
                    </svg>
                  </Link>
                </li>
                {isAuth ?
                <>
                  <li className="nav-item">
                    <Link to="/wishlist" className="text-decoration-none">
                      <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="#fff" className="bi bi-heart-fill" viewBox="0 0 16 16">
                        <path fillRule="evenodd" d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314"/>
                      </svg>
                    </Link>
                  </li>
                   <li className="nav-item">
                       <span className="fs-6 text-white me-2">{profileName} </span>
                       <a href="#" className="text-decoration-none">
                           <svg xmlns="http://www.w3.org/2000/svg" width="27.5" height="27.5" fill="#C0C0C0" className="bi bi-person-circle" viewBox="0 0 16 16">
                            <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0"/>
                            <path fillRule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8m8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1"/>
                           </svg>
                       </a>
                   </li>
                </> :
                    <> 
                        <li className="nav-item"><Link to="/signup" className="text-decoration-none text-white">
                            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="#fff" className="bi bi-person" viewBox="0 0 16 16">
                            <path d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6m2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0m4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4m-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10s-3.516.68-4.168 1.332c-.678.678-.83 1.418-.832 1.664z" />
                            </svg> Sign Up</Link>
                        </li>
                        <li className="nav-item"><Link to="/login" className="text-decoration-none text-white">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="#fff" className="bi bi-arrow-up" viewBox="0 0 16 16">
                            <path fillRule="evenodd" d="M8 15a.5.5 0 0 0 .5-.5V2.707l3.146 3.147a.5.5 0 0 0 .708-.708l-4-4a.5.5 0 0 0-.708 0l-4 4a.5.5 0 1 0 .708.708L7.5 2.707V14.5a.5.5 0 0 0 .5.5" />
                            </svg>Login</Link>
                        </li>
                    </>
                }                
              </ul>
            </div>
          </div>
        </nav>
    )
}

export default Header;