import {React, useContext, useEffect, useState} from 'react';
import Context from '../ContextAPI/Context';
import BookService from '../Service/BookService';
import WishlistService from '../Service/WishlistService';

const deleteBook = (setDelete) => {
    setDelete(true);
};

const clickOnWishlist = (isInWishlist, setWishlist, e) => {
    const $book = e.target.closest('li.book');
    const bookId = $book.getAttribute('id');
    const imageLink = $book.querySelector('div.book-image img').getAttribute('src');
    const title = $book.querySelector('div.book-info div.title').innerText;
    const author = $book.querySelector('div.book-info div.author').innerText;

    console.log(bookId, imageLink, title, author);

    if (isInWishlist) {
        WishlistService.removeFromWishlist(bookId);
    } else {
        const bookToAddInWishlist = {
            bookId,
            imageLink,
            title,
            author
        };

        WishlistService.addToWishlist(bookToAddInWishlist).then(res => console.log(res));
    }

    setWishlist(!isInWishlist);
};

const adjustTitle = (title) => {
    if (title.length > 22) {
        return title.substr(22) + '...';
    }

    return title;
};

const checkIfBookIsInWishlist = (allWishlistBooks, id) => {
    return allWishlistBooks.some(book => book.bookId === id);
}

const Book = (props) => {
    const {bookNumInRow, itemMargin, properties} = props;
    let {id, bookId, imageLink, title, author, pageType} = properties;
    const {allBooks, setAllBooks, allWishlistBooks, setWishlistBooks} = useContext(Context);
    const [isDeleted, setDelete] = useState(false);
    id = pageType === "Wishlist-Page" ? bookId : id;
    const [isInWishlist, setWishlist] = useState(checkIfBookIsInWishlist(allWishlistBooks, id));

    useEffect(() => {
        if (isDeleted) {
            BookService.deleteBookById(id);
            setAllBooks(allBooks.filter((book) => {
                return book.id != id;
            }));
            setDelete(false);
        }

    }, [isDeleted])
    
    return (
        <li id={id} className="book col-sm-3 border" style={{
                margin: itemMargin + 'px',
                width: 'calc((100% - ' + bookNumInRow * 2 * itemMargin + 'px)/' + bookNumInRow +')'
                }}>
            <div className="p-3">
                <div class="d-flex justify-content-between">
                    <div>
                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" className="bi bi-heart" viewBox="0 0 16 16"
                            style={{display: isInWishlist ? "none" : "block", position: "relative", bottom: 10 + "px", cursor: "pointer"}}
                            onClick={(e) => {
                                clickOnWishlist(isInWishlist, setWishlist, e);
                            }}>
                            <path d="m8 2.748-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143q.09.083.176.171a3 3 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15"/>
                        </svg>
                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" className="bi bi-heart-fill" viewBox="0 0 16 16"
                            style={{display: isInWishlist ? "block" : "none", position: "relative", bottom: 10 + "px", cursor: "pointer"}}
                            onClick={(e) => {
                                clickOnWishlist(isInWishlist, setWishlist, e)
                            }}>
                            <path fill-rule="evenodd" d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314"/>
                        </svg>
                    </div>
                    <button type="button" class="btn-close" aria-label="Close"
                        style={{position: "relative", left: 10 + "px", bottom: 10 + "px"}}
                        onClick={() => deleteBook(setDelete)}></button>
                </div>
                <div class="book-image d-flex justify-content-center">
                    <img src={imageLink} width="120" height="120"/>
                </div>
                <div className="book-info mt-4">
                    <div className="title font-weight-bold text-center">{adjustTitle(title)}</div>
                    <div className="author text-muted text-center"><span className="font-italic">{author}</span></div>
                </div>
            </div>
        </li>
    )
}

export default Book;