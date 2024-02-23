import React, { useState, useContext, useEffect } from "react";
import Context from "../ContextAPI/Context";

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

const ShoppingCartItem = (props) => {
    const {bookInCart, rowIndex} = props;
    const {allBooksInCart, setTotalInCart} = useContext(Context);
    const [quantity, setQuantity] = useState(1);

    useEffect(() => {
        calculateCartTotal(setTotalInCart);
    }, [quantity])

    return (
        <div className="row card-item">
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
                title="Remove item">
                <i className="fas fa-trash"></i>
              </button>
              <button type="button" className="btn btn-danger btn-sm mb-2" data-mdb-toggle="tooltip"
                title="Move to the wish list">
                <i className="fas fa-heart"></i>
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