import React, { useContext, useEffect, useState } from "react";
import Context from "../ContextAPI/Context";
import Book from "./Book";
import Header from "./Header";
import Utils from "../Utils/Utils";
import { Link } from "react-router-dom";
import BookService from "../Service/BookService";
import WishlistService from "../Service/WishlistService";

let bookNumInRow = 4;
let itemMargin = 10;
let bookNum;
let rowNum;
let rowIds;
let imageIndexes;

const BookPage = () => {
    const {allBooks, setAllBooks, allWishlistBooks, setWishlistBooks} = useContext(Context);

    const [isFirstRender, setFirstRender] = useState(true);

    bookNum = allBooks.length;
    rowNum = Utils.calculateRowCount(bookNum, bookNumInRow);
    rowIds = Utils.generateRowIds(rowNum);
    imageIndexes = Utils.generateImageIndexes(bookNum);

    useEffect(() => {
        if (isFirstRender) {
            BookService.getAllBooks().then(res => {
                let newBooks = ((res || {}).data || []);

                newBooks = newBooks.filter(newBook => {
                    return !allBooks.some(book => newBook.bookId === book.bookId);
                });

                setAllBooks(allBooks => [...allBooks, ...newBooks]);
            });

            WishlistService.getAllBooksInWishlist().then(res => {
                const newWishlistBooks = (res || []).filter(newWishlist => {
                    return !allWishlistBooks.some(wishlistBook => newWishlist.bookId === wishlistBook.bookId);
                });

                setWishlistBooks(allWishlistBooks => [...allWishlistBooks, ...newWishlistBooks]);
            })

            setFirstRender(false);
        }
    }, [allBooks])

    return (
        <> 
            <Header/>
            <div className="mt-5">
                <div className="d-flex justify-content-end">
                    <Link to="/books/new" type="button" className="btn btn-primary btn-block mb-4"
                        style={{position: 'relative', right: 75 + 'px'}}
                        onClick={() => setFirstRender(true)}>Add New Book</Link>
                </div>
                {
                    allBooks.length > 0 ?
                        <div className="container border">
                            { 
                                rowIds.map((rowId) => {
                                    return(
                                        <ul id={rowId} className="d-flex list-unstyled">
                                            {
                                                allBooks.map((book, bookIndex) => {
                                                    const start = (rowId - 1) * bookNumInRow;
                                                    if (start <= bookIndex && bookIndex < start + bookNumInRow) {
                                                        const {id, imageLink, title, author} = book;
                                                        const properties = {
                                                            id,
                                                            imageLink,
                                                            title,
                                                            author,
                                                            pageType: "Book-Page"
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
                        <></>
                }
            </div>
        </>
    )
}

export default BookPage;