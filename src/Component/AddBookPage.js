import React from "react";
import Header from "./Header";
import BookService from "../Service/BookService";

const addBook = () => {
    const $imageLink = document.getElementById('imageLink');
    const $title = document.getElementById('title');
    const $author = document.getElementById('author');

    const addBookRequest = {
        imageLink: $imageLink.value,
        title: $title.value,
        author: $author.value
    };

    BookService.createBook(addBookRequest);
}

const AddBookPage = () => {
    return (
        <>
          <Header/>
          <div className="d-flex align-items-center" style={{height: 'calc(' + 100 + 'vh - ' + 56 + 'px)'}}>
            <div className="container w-25 h-auto border p-10  text-center" style={{marginBottom: 200 + 'px'}}>
              <h1>Add New Book</h1>
              <div className="form-outline my-4">
                <input type="text" id="imageLink" className="form-control" />
                <label className="form-label" htmlFor="imageLink">Image Link</label>
              </div>
              <div className="form-outline mb-4">
                <input type="text" id="title" className="form-control" />
                <label className="form-label" htmlFor="title">Title</label>
              </div>
              <div className="form-outline mb-4">
                <input type="text" id="author" className="form-control" />
                <label className="form-label" htmlFor="author">Author</label>
              </div>

              <button type="button" className="btn btn-primary btn-block mb-4" onClick={addBook}>Add Book</button>
            </div>
          </div>
        </>
    )
}

export default AddBookPage;