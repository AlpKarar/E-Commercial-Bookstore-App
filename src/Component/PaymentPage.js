import React, { useState } from "react";

const PaymentPage = () => {
    const [isMasterCard, setMasterCard] = useState(true);

    return (
      <section className="d-flex align-items-center vh-100">
        <div className="container">
          <div className="card px-3">
            <div className="row">
              <div className="col-lg-7 card-body border-end">
                <h4 className="mb-2">Checkout</h4>
                <p className="mb-0">All plans include 40+ advanced tools and features to boost your product. <br/>
                  Choose the best plan to fit your needs.</p>
                <div className="row py-4 my-2 d-flex justify-content-center gap-5">
                  <button className={"btn col-md-4 mb-md-0 mb-2" + (isMasterCard ? "" : " btn-outline-success")}
                    onClick={() => setMasterCard(false)}>
                    <div className="form-check custom-option custom-option-basic">
                      <label className="form-check-label custom-option-content form-check-input-payment d-flex gap-3 align-items-center" for="customRadioVisa">
                        <span className="custom-option-body">
                          <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/0/04/Visa.svg/2560px-Visa.svg.png" alt="visa-card" width="58" data-app-light-img="icons/payments/visa-light.png" data-app-dark-img="icons/payments/visa-dark.png"/>
                          <span className="ms-3">Visa Card</span>
                        </span>
                      </label>
                    </div>
                  </button>
                  <button className={"btn col-md-4 mb-md-0 mb-2" + (isMasterCard ? " btn-outline-success" : "")}
                    onClick={() => setMasterCard(true)}>
                    <div className="form-check custom-option custom-option-basic">
                      <label className="form-check-label custom-option-content form-check-input-payment d-flex gap-3 align-items-center" for="customRadioPaypal">
                        <span className="custom-option-body">
                          <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/2/2a/Mastercard-logo.svg/1280px-Mastercard-logo.svg.png" alt="paypal" width="58" data-app-light-img="icons/payments/paypal-light.png" data-app-dark-img="icons/payments/paypal-dark.png"/>
                          <span className="ms-3">Master Card</span>
                        </span>
                      </label>
                    </div>
                  </button>
                </div>
                <h4 className="mt-2 mb-4">Billing Details</h4>
                <form>
                  <div className="row g-3">
                    <div className="col-md-6">
                      <label className="form-label" for="billings-email">Email Address</label>
                      <input type="email" id="billings-email" className="form-control" placeholder="john.doe@gmail.com"/>
                    </div>
                    <div className="col-md-6">
                      <label className="form-label" for="billings-password">Password</label>
                      <input type="password" id="billings-password" className="form-control" placeholder="Password"/>
                    </div>
                    <div className="col-md-6">
                      <label className="form-label" for="billings-country">Country</label>
                      <select id="billings-country" className="form-select" data-allow-clear="true">
                        <option value="">Select</option>
                        <option value="Australia">Australia</option>
                        <option value="Brazil">Brazil</option>
                        <option value="Canada">Canada</option>
                        <option value="China">China</option>
                        <option value="France">France</option>
                        <option value="Germany">Germany</option>
                        <option value="India">India</option>
                        <option value="Turkey">Turkey</option>
                        <option value="Ukraine">Ukraine</option>
                        <option value="United Arab Emirates">United Arab Emirates</option>
                        <option value="United Kingdom">United Kingdom</option>
                        <option value="United States">United States</option>
                      </select>
                    </div>
                    <div className="col-md-6">
                      <label className="form-label" for="billings-zip">Billing Zip / Postal Code</label>
                      <input type="text" id="billings-zip" className="form-control billings-zip-code" placeholder="Zip / Postal Code"/>
                    </div>
                  </div>
                </form>
                <div id="form-credit-card">
                  <h4 className="mt-4 pt-2">Credit Card Info</h4>
                  <form>
                    <div className="row g-3">
                      <div className="col-12">
                        <label className="form-label" for="billings-card-num">Card number</label>
                        <div className="input-group input-group-merge">
                          <input type="text" id="billings-card-num" className="form-control billing-card-mask" placeholder="7465 8374 5837 5067" aria-describedby="paymentCard"/>
                          <span className="input-group-text cursor-pointer p-1" id="paymentCard"><span className="card-type"></span></span>
      
                        </div>
                      </div>
                      <div className="col-md-6">
                        <label className="form-label" for="billings-card-name">Name</label>
                        <input type="text" id="billings-card-name" className="form-control" placeholder="John Doe"/>
                      </div>
                      <div className="col-md-3">
                        <label className="form-label" for="billings-card-date">EXP. Date</label>
                        <input type="text" id="billings-card-date" className="form-control billing-expiry-date-mask" placeholder="MM/YY"/>
                      </div>
                      <div className="col-md-3">
                        <label className="form-label" for="billings-card-cvv">CVV</label>
                        <input type="text" id="billings-card-cvv" className="form-control billing-cvv-mask" maxlength="3" placeholder="965"/>
                      </div>
                    </div>
                  </form>
                </div>
              </div>
              <div className="col-lg-5 card-body">
                <h4 className="mb-2">Order Summary</h4>
                <p className="pb-2 mb-0">It can help you manage and service orders before,<br/> during and after fulfilment.</p>
                <div className="bg-lighter p-4 rounded mt-4">
                  <p>A simple start for everyone</p>
                  <div className="d-flex align-items-center">
                    <h1 className="text-heading display-3">$59.99</h1>
                    <sub>/month</sub>
                  </div>
                  <div className="d-grid">
                    <button type="button" data-bs-target="#pricingModal" data-bs-toggle="modal" className="btn btn-label-primary">Change Plan</button>
                  </div>
                </div>
                <div>
                  <div className="d-flex justify-content-between align-items-center mt-3">
                    <p className="mb-0">Subtotal</p>
                    <h6 className="mb-0">$85.99</h6>
                  </div>
                  <div className="d-flex justify-content-between align-items-center mt-3">
                    <p className="mb-0">Shipping</p>
                    <h6 className="mb-0">$4.99</h6>
                  </div>
                  <hr/>
                  <div className="d-flex justify-content-between align-items-center mt-3 pb-1">
                    <p className="mb-0">Total</p>
                    <h6 className="mb-0">$90.98</h6>
                  </div>
                  <div className="d-grid mt-3">
                    <button className="btn btn-success">
                      <span className="me-2">Proceed with Payment</span>
                      <i className="bx bx-right-arrow-alt scaleX-n1-rtl"></i>
                    </button>
                  </div>
      
                  <p className="mt-4 pt-2">By continuing, you accept to our Terms of Services and Privacy Policy. Please note that payments are non-refundable.</p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
    )
}

export default PaymentPage;