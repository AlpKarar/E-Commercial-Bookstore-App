import {React, useContext} from 'react';
import { Link } from 'react-router-dom';
import Header from './Header';
import Context from '../ContextAPI/Context';
import ShoppingCart from './ShoppingCart';

const Home = () => {
    const {isAuth, setAuth} = useContext(Context);
    
    return (
        <>
            <Header/>
            <div className="d-flex align-items-center" style={{height: 'calc(' + 100 + 'vh - ' + 56 + 'px)'}}>
                <div className="container">
                    <div className="jumbotron text-center">
                        <h1>Home Page</h1>
                        <br/>
                        <p>Bootstrap is the most popular HTML, CSS, and JS framework for developing
                            responsive, mobile-first projects on the web.
                        </p>
                        <br/>
                        {
                            isAuth ?
                            <Link to="/books" className="btn btn-primary btn-block mb-4 text-decoration-none">Show All Books</Link> : <></>
                        }
                    </div>
                </div>
            </div>
        </>
    )
}

export default Home;