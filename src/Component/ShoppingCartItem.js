import React, { useState, useContext, useEffect } from "react";
import Context from "../ContextAPI/Context";
import WishlistService from '../Service/WishlistService';

const incrementQuantityByOne = (quantity, setQuantity) => {
    setQuantity(quantity => quantity + 1);
}

const decrementQuantityByOne = (quantity, setQuantity) => {
  if (quantity <= 1) {
    return 1
  }

  setQuantity(quantity => quantity - 1);
}

const calculateTotalItemPrice = (quantity, itemPrice) => {
    return (quantity * parseFloat(itemPrice.slice(1))).toFixed(2);
}

const calculateCartTotal = (setTotalInCart) => {
  setTotalInCart(prevState => !prevState);
}

const removeItemFromCart = (setBooksInCart, bookId) => {
  setBooksInCart(allBooksInCart => allBooksInCart.filter(bookInCart => {
    return bookInCart.bookId !== bookId;
  }))
}

const addItemToWishlist = (book, setBooksInCart) => {
  WishlistService.addToWishlist(book).then(res => {
    console.log(res);
    removeItemFromCart(setBooksInCart, book.bookId);
  });
}

const ShoppingCartItem = (props) => {
    const {bookInCart, rowIndex} = props;
    const {allBooksInCart, setBooksInCart, setTotalInCart} = useContext(Context);
    const [quantity, setQuantity] = useState(1);
    const cartItemId = rowIndex + 1;

    useEffect(() => {
        calculateCartTotal(setTotalInCart);
    }, [quantity])

    return (
        <div id={cartItemId} item-id={bookInCart.bookId} className="row card-item">
            <div className="col-lg-3 col-md-12 mb-4 mb-lg-0">
              <div className="bg-image hover-overlay hover-zoom ripple rounded" data-mdb-ripple-color="light">
                <img src={bookInCart.imageLink}
                  className="w-100" alt={bookInCart.title} />
                <a href="#!">
                  <div className="mask" style={{backgroundColor: "rgba(251, 251, 251, 0.2)"}}></div>
                </a>
              </div>
            </div>

            <div className="col-lg-5 col-md-6 mb-4 mb-lg-0">
              <p><strong>{bookInCart.title}</strong></p>
              <p>Author: {bookInCart.author}</p>
              <p>Price: {bookInCart.price}</p>
              <button type="button" className="btn btn-primary btn-sm me-1 mb-2" data-mdb-toggle="tooltip"
                title="Remove item" onClick={() => removeItemFromCart(setBooksInCart, bookInCart.bookId)}>
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="my-1" viewBox="0 0 16 16">
                  <path d="M11 1.5v1h3.5a.5.5 0 0 1 0 1h-.538l-.853 10.66A2 2 0 0 1 11.115 16h-6.23a2 2 0 0 1-1.994-1.84L2.038 3.5H1.5a.5.5 0 0 1 0-1H5v-1A1.5 1.5 0 0 1 6.5 0h3A1.5 1.5 0 0 1 11 1.5m-5 0v1h4v-1a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0-.5.5M4.5 5.029l.5 8.5a.5.5 0 1 0 .998-.06l-.5-8.5a.5.5 0 1 0-.998.06m6.53-.528a.5.5 0 0 0-.528.47l-.5 8.5a.5.5 0 0 0 .998.058l.5-8.5a.5.5 0 0 0-.47-.528M8 4.5a.5.5 0 0 0-.5.5v8.5a.5.5 0 0 0 1 0V5a.5.5 0 0 0-.5-.5"/>
                </svg>
              </button>
              <button type="button" className="btn btn-danger btn-sm mb-2" data-mdb-toggle="tooltip"
                title="Move to the wish list" onClick={() => addItemToWishlist({
                  bookId: bookInCart.bookId,
                  imageLink: bookInCart.imageLink,
                  title: bookInCart.title,
                  author: bookInCart.author,
                  price: parseFloat(bookInCart.price.slice(1))
                }, setBooksInCart)}>
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="my-1" viewBox="0 0 16 16">
                  <path fill-rule="evenodd" d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314"/>
                </svg>
              </button>
            </div>

            <div className="col-lg-4 col-md-6 mb-4 mb-lg-0">
              <div className="d-flex mb-4" style={{maxWidth: 300 + "px"}}>
                <button className="btn btn-primary px-3 me-2" style={{width: 2.5 + "rem", height: 2.5 + "rem"}}
                  onClick={() => decrementQuantityByOne(quantity, setQuantity)}>
                <i className="fas fa-minus">-</i>
                </button>

                <div className="form-outline">
                  <input id="quantity-input" min="0" name="quantity" type="number" value={quantity} className="form-control"/>
                  <label className="form-label" for="form1">Quantity</label>
                </div>

                <button className="btn btn-primary px-3 ms-2" style={{width: 2.5 + "rem", height: 2.5 + "rem"}}
                  onClick={() => incrementQuantityByOne(quantity, setQuantity)}><i>+</i></button>
              </div>
              <p className="item-total-price text-start text-md-center">
                <strong>${calculateTotalItemPrice(quantity, bookInCart.price)}</strong>
              </p>
            </div>
            {rowIndex < allBooksInCart.length - 1 ? <hr className="my-4"/> : <></>}
        </div>
    )
}

export default ShoppingCartItem;