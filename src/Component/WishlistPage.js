import React, { useState, useEffect, useContext } from "react";
import Header from "./Header";
import Book from "./Book";
import Utils from "../Utils/Utils";
import Context from "../ContextAPI/Context";
import WishlistService from "../Service/WishlistService";

let bookNumInRow = 4;
let itemMargin = 10;
let bookNum;
let rowNum;
let rowIds;
let imageIndexes;

const WishlistPage = () => {    
    const {allWishlistBooks, setWishlistBooks} = useContext(Context);
    const [isFirstRender, setFirstRender] = useState(true);

    bookNum = allWishlistBooks.length;
    rowNum = Utils.calculateRowCount(bookNum, bookNumInRow);
    rowIds = Utils.generateRowIds(rowNum);
    imageIndexes = Utils.generateImageIndexes(bookNum);

    useEffect(() => {
        if (isFirstRender) {
            WishlistService.getAllBooksInWishlist().then(res => {
                const newBooks = (res || []).filter(newBook => {
                    return !allWishlistBooks.some(book => newBook.bookId === book.bookId);
                });

                setWishlistBooks(allWishlistBooks => [...allWishlistBooks, ...newBooks]);
            });

            setFirstRender(false);
        }
    }, [allWishlistBooks])

    return (
        <>
            <Header/>
            <div className="mt-5">
                <div className="d-flex justify-content-center mb-4">
                    <h1>Wishlist</h1>
                </div>
                { allWishlistBooks.length > 0 ?
                    <div className="container border">
                        {
                            rowIds.map(rowId => {
                                return (
                                    <ul id={rowId} className="d-flex list-unstyled">
                                        {
                                            allWishlistBooks.map((wishlistBook, index) => {
                                                const start = (rowId - 1) * bookNumInRow;

                                                if (start <= index && index < start + bookNumInRow) {
                                                    const {bookId, imageLink, title, author} = wishlistBook;
                                                    const properties = {
                                                        bookId,
                                                        imageLink,
                                                        title,
                                                        author,
                                                        pageType: "Wishlist-Page"
                                                    };

                                                    return (
                                                        <Book bookNumInRow={bookNumInRow} itemMargin={itemMargin} properties={properties}/>                                                        
                                                    )
                                                }
                                            })
                                        }
                                    </ul>
                                )
                            })
                        }
                    </div> :
                <></> }
            </div>
        </>
    )
}

export default WishlistPage;