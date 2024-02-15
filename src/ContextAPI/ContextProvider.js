import { useState } from 'react';
import Context from './Context';

const ContextProvider = (props) => {
    const [isAuth, setAuth] = useState(true);
    const [isFirstRenderOfBookPage, setFirstRenderOfBookPage] = useState(true);
    const [allBooks, setAllBooks] = useState([]);
    const [isInWishlist, setWishlist] = useState(false);

  
    const states = {
        isAuth: isAuth,
        setAuth: setAuth,
        isFirstRenderOfBookPage: isFirstRenderOfBookPage,
        setFirstRenderOfBookPage: setFirstRenderOfBookPage,
        allBooks: allBooks,
        setAllBooks: setAllBooks,
        isInWishlist,
        setWishlist
    };

    return (
      <Context.Provider value={states}>
        {props.children}
      </Context.Provider >
    )
  }

  export default ContextProvider;