import { useState } from 'react';
import Context from './Context';

const ContextProvider = (props) => {
    const [isAuth, setAuth] = useState(true);
    const [allBooks, setAllBooks] = useState([]);
    const [allWishlistBooks, setWishlistBooks] = useState([]);
    const [allBooksInCart, setBooksInCart] = useState([
      {
        bookId: 1,
        imageLink: "http://whyareyouwatchingmeout.cox",
        title: "Savaş Ve Barış",
        author: "Tolstoy",
        price: "$17.99"
      },
      {
        bookId: 2,
        imageLink: "http://whyareyouwatchingmeout.cox",
        title: "Koromozov Kardeşler",
        author: "Dostoyevski",
        price: "$11.99"
      }
    ]);
    const [totalInCart, setTotalInCart] = useState(false);
  
    const states = {
        isAuth: isAuth,
        setAuth: setAuth,
        allBooks: allBooks,
        setAllBooks: setAllBooks,
        allWishlistBooks: allWishlistBooks,
        setWishlistBooks: setWishlistBooks,
        allBooksInCart: allBooksInCart,
        setBooksInCart: setBooksInCart,
        totalInCart: totalInCart,
        setTotalInCart: setTotalInCart
    };

    return (
      <Context.Provider value={states}>
        {props.children}
      </Context.Provider >
    )
  }

  export default ContextProvider;