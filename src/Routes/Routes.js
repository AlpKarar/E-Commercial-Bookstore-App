import { Route, Routes } from "react-router-dom";

import Home from '../Component/Home';
import AboutPage from "../Component/AboutPage";
import BookPage from "../Component/BookPage";
import AddBookPage from "../Component/AddBookPage";
import ShoppingCart from "../Component/ShoppingCart";
import SignupPage from "../Component/SignupPage";
import LoginPage from "../Component/LoginPage";
import ForgotPasswordPage from "../Component/ForgotPasswordPage";
import WishlistPage from "../Component/WishlistPage";
import Error404Page from "../Component/Error404Page";

const BookAppRoutes = () => {
    return (
        <Routes>
            <Route path="/" element={<Home/>} />
            <Route path="/about" element={<AboutPage/>} />
            <Route path="/books" element={<BookPage/>} />
            <Route path="/books/new" element={<AddBookPage/>} />
            <Route path="/cart" element={<ShoppingCart/>} />
            <Route path="/signup" element={<SignupPage/>} />
            <Route path="/login" element={<LoginPage/>} />
            <Route path="/updatePassword" element={<ForgotPasswordPage/>} />
            <Route path="/wishlist" element={<WishlistPage/>} />
            <Route path="*" element={<Error404Page/>} />
        </Routes>
    )
};

export default BookAppRoutes;