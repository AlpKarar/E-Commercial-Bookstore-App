import React, { useEffect, useState } from "react";
import Book from "./Book";
import Header from "./Header";
import Utils from "../Utils/Utils";
import { Link } from "react-router-dom";
import BookService from "../Service/BookService";

const BookPage = () => {
    const [isFirstRender, setFirstRender] = useState(true);
    const [allBooks, setAllBooks] = useState([]);

    const bookNum = allBooks.length;
    const bookNumInRow = 4;
    const itemMargin = 10;
    const rowNum = Utils.calculateRowCount(bookNum, bookNumInRow);
    const rowIds = Utils.generateRowIds(rowNum);
    const imageIndexes = Utils.generateImageIndexes(bookNum);

    useEffect(() => {
        if (isFirstRender) {
            BookService.getAllBooks().then(res => {
                setAllBooks(res.data); 
             });

             setFirstRender(false);
        }
    }, [allBooks])

    return (
        <> 
            <Header/>
            <div className="mt-5">
                <div className="d-flex justify-content-end">
                    <Link to="/books/new" type="button" className="btn btn-primary btn-block mb-4" style={{position: 'relative', right: 75 + 'px'}}
                        onClick={() => setFirstRender(true)}>Add New Book</Link>
                </div>
                {
                    allBooks.length > 0 ?
                        <div className="container border">
                            { rowIds.map((item, index) => {
                                return (
                                    <ul id={item} className="row list-unstyled">
                                        {
                                            imageIndexes.map((id) => {
                                                const liIdx = index * bookNumInRow + id;
                                                if (liIdx <= bookNum) {
                                                    return (
                                                        <li id={liIdx} className="col-sm-3 border" style={{
                                                            margin: itemMargin + 'px',
                                                            width: 'calc((100% - ' + bookNumInRow * 2 * itemMargin + 'px)/' + bookNumInRow +')'
                                                            }}>
                                                            <Book book={allBooks[liIdx - 1]}/>
                                                        </li>
                                                    )
                                                }
                                            })
                                        }
                                    </ul>
                                )
                            })}
                        </div> :
                        <></>
                }
            </div>
        </>
    )
}

export default BookPage;