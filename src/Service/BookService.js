import axios from 'axios';

const Host_URL = 'http://localhost:5005/api/book';

class BookService {

    async getAllBooks() {
        return await axios.get('/getAll')
            .then(res => res)
            .catch(err => this.printError(err));
    }

    async getBookById(id) {
        return await axios.get('/get?bookId=' + id)
            .then(res => res)
            .catch(err => this.printError(err));
    }

    async createBook(newBook) {
        return await axios.get('/create', {newBook})
            .then(res => res)
            .catch(err => this.printError(err));
    }

    async updateBook(bookToUpdate) {
        return await axios.get('/update', {bookToUpdate})
            .then(res => res)
            .catch(err => this.printError(err));
    }

    async deleteBookById(id) {
        return await axios.get('/delete/' + id)
            .then(res => res)
            .catch(err => this.printError(err));
    }

    printError(err) {
        console.log("====ERROR====");
        console.log(err);
        return null;
    }
}

export default new BookService();