import React from "react";
import Header from "./Header";
import Book from "./Book";
import Utils from "../Utils/Utils";

const WishlistPage = () => {
    const bookNum = 3;
    const bookNumInRow = 4;
    const itemMargin = 10;
    const rowNum = Utils.calculateRowCount(bookNum, bookNumInRow);
    const rowIds = Utils.generateRowIds(rowNum);
    const imageIndex = Utils.generateImageIndexes(bookNumInRow);

    return (
        <>
            <Header/>
            <div className="mt-5">
                <div className="d-flex justify-content-center mb-4">
                    <h1>Wishlist</h1>
                </div>
                <div className="container border">
                    { rowIds.map((item, index) => {
                        return (
                            <ul id={item} className="row list-unstyled">
                                {
                                    imageIndex.map((id, idx) => {
                                        const liIdx = index * bookNumInRow + id;
                                        if (liIdx <= bookNum) {
                                            return (
                                                <li id={liIdx} className="col-sm-3 border" style={{
                                                    margin: itemMargin + 'px',
                                                    width: 'calc((100% - ' + bookNumInRow * 2 * itemMargin + 'px)/' + bookNumInRow +')'
                                                    }}>
                                                    <Book/>
                                                </li>
                                            )
                                        }
                                    })
                                }
                            </ul>
                        )
                    })}
                </div>
            </div>
        </>
    )
}

export default WishlistPage;