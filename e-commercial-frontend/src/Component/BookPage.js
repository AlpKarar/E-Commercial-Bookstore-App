import React from "react";
import Book from "./Book";
import Header from "./Header";
import Utils from "../Utils/Utils";

const arrayRange = (start, stop, step) =>
    Array.from(
    { length: (stop - start) / step + 1 },
    (value, index) => start + index * step
    );

const BookPage = () => {
    const bookNum = 7;
    const bookNumInRow = 4;
    const itemMargin = 10;
    const rowNum = Utils.calculateRowCount(bookNum, bookNumInRow);
    const rowIds = Utils.generateRowIds(rowNum);
    const imageIndex = Utils.generateImageIndexes(bookNumInRow);

    return (
        <> 
            <Header/>
            <div class="mt-5">
                <div class="container border">
                    { rowIds.map((item, index) => {
                        return (
                            <ul id={item} class="row list-unstyled">
                                {
                                    imageIndex.map((id, idx) => {
                                        const liIdx = index * bookNumInRow + id;
                                        if (liIdx <= bookNum) {
                                            return (
                                                <li id={liIdx} class="col-sm-3 border" style={{
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

export default BookPage;