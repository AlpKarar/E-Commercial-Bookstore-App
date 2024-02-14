import axios from 'axios';

const Host_URL = 'http://localhost:5005/api/book';

class BookService {

    async getAllBooks() {
        return await axios.get(Host_URL + '/getAll')
            .then(res => res)
            .catch(err => this.printError(err));
    }

    async getBookById(id) {
        return await axios.get(Host_URL + '/get?bookId=' + id)
            .then(res => res)
            .catch(err => this.printError(err));
    }

    async createBook(newBook) {
        return await axios.get(Host_URL + '/create', {newBook})
            .then(res => res)
            .catch(err => this.printError(err));
    }

    async updateBook(bookToUpdate) {
        return await axios.get(Host_URL + '/update', {bookToUpdate})
            .then(res => res)
            .catch(err => this.printError(err));
    }

    async deleteBookById(id) {
        return await axios.get(Host_URL + '/delete/' + id)
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