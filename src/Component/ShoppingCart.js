import React, { useState, useContext, useEffect } from "react";
import Context from "../ContextAPI/Context";
import ShoppingCartItem from "./ShoppingCartItem";
import Header from './Header';

const calculateCartTotal = () => {
  const $cartItems = document.querySelectorAll('.card-body > .row');
  const total = Array.from($cartItems).reduce((total, cartItem) => {
    return total + parseFloat(cartItem.querySelector('.item-total-price strong').innerText.slice(1));
  }, 0);
  const shippingPrice = 5;

  document.querySelector(".products-total-price span").innerText= "$" + total.toFixed(2);
  document.querySelector(".shipping-total-price span").innerText= "$" + shippingPrice.toFixed(2);
  document.querySelector(".total-amount strong").innerText = "$" + (total + shippingPrice).toFixed(2);
}

const ShoppingCart = () => {
    const {allBooksInCart, setBooksInCart, totalInCart} = useContext(Context);
    const todayDate = new Date().getDate();
    let firstDayForDelivery = new Date();
    let lastDayForDelivery = new Date();

    firstDayForDelivery.setDate(todayDate + 3);
    lastDayForDelivery.setDate(todayDate + 7);

    useEffect(() => {
      calculateCartTotal();
    }, [allBooksInCart, totalInCart])
  
    return (
        <>
          <Header/>
          <section className="h-100 gradient-custom">
            <div className="container py-5">
              <div className="row d-flex justify-content-center my-4">
                <div className="col-md-8">
                  <div className="card mb-4">
                    <div className="card-header py-3">
                      <h5 className="mb-0">Cart - {allBooksInCart.length + " item" + (allBooksInCart.length > 1 ? "s" : "") }</h5>
                    </div>
                    <div className="card-body">
                      {allBooksInCart.map((bookInCart, rowIndex) => {
                        return (
                          <ShoppingCartItem bookInCart={bookInCart} rowIndex={rowIndex}/>
                        )
                      })}
                      
                    </div>
                  </div>
                  <div className="card mb-4">
                    <div className="card-body">
                      <p><strong>Expected shipping delivery</strong></p>
                      <p className="mb-0">{firstDayForDelivery.toLocaleDateString('tr-TR')} - {lastDayForDelivery.toLocaleDateString('tr-TR')}</p>
                    </div>
                  </div>
                  <div className="card mb-4 mb-lg-0">
                    <div className="card-body">
                      <p><strong>We accept</strong></p>
                      <img className="me-2" width="45px"
                        src="https://mdbcdn.b-cdn.net/wp-content/plugins/woocommerce-gateway-stripe/assets/images/visa.svg"
                        alt="Visa" />
                      <img className="me-2" width="45px"
                        src="https://mdbcdn.b-cdn.net/wp-content/plugins/woocommerce-gateway-stripe/assets/images/amex.svg"
                        alt="American Express" />
                      <img className="me-2" width="45px"
                        src="https://mdbcdn.b-cdn.net/wp-content/plugins/woocommerce-gateway-stripe/assets/images/mastercard.svg"
                        alt="Mastercard" />
                      <img className="me-2" width="45px"
                        src="https://mdbcdn.b-cdn.net/wp-content/plugins/woocommerce/includes/gateways/paypal/assets/images/paypal.webp"
                        alt="PayPal acceptance mark" />
                    </div>
                  </div>
                </div>
                <div className="col-md-4">
                  <div className="card mb-4">
                    <div className="card-header py-3">
                      <h5 className="mb-0">Summary</h5>
                    </div>
                    <div className="card-body">
                      <ul className="list-group list-group-flush">
                        <li
                          className="products-total-price list-group-item d-flex justify-content-between align-items-center border-0 px-0 pb-0">
                          Products
                          <span>$53.98</span>
                        </li>
                        <li className="shipping-total-price list-group-item d-flex justify-content-between align-items-center px-0">
                          Shipping
                          <span>Gratis</span>
                        </li>
                        <li
                          className="list-group-item d-flex justify-content-between align-items-center border-0 px-0 mb-3">
                          <div>
                            <strong>Total amount</strong>
                          </div>
                          <span className="total-amount"><strong></strong></span>
                        </li>
                      </ul>
                    
                      <button type="button" className="btn btn-primary btn-lg btn-block">
                        Go to checkout
                      </button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </section>
        </>
    )
}

export default ShoppingCart;