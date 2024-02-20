import { useState } from 'react';
import Context from './Context';

const ContextProvider = (props) => {
    const [isAuth, setAuth] = useState(true);
    const [allBooks, setAllBooks] = useState([]);
    const [allWishlistBooks, setWishlistBooks] = useState([]);

  
    const states = {
        isAuth: isAuth,
        setAuth: setAuth,
        allBooks: allBooks,
        setAllBooks: setAllBooks,
        allWishlistBooks: allWishlistBooks,
        setWishlistBooks: setWishlistBooks
    };

    return (
      <Context.Provider value={states}>
        {props.children}
      </Context.Provider >
    )
  }

  export default ContextProvider;