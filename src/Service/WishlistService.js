import axios from "axios";

class WishlistService {

    HOST_URL = "http://localhost:5006/api/wishlist";

    async getAllBooksInWishlist() {
        return axios.get(this.HOST_URL + "/all")
            .then(res => (res || {}).data || [])
            .catch(err => console.log(err))
    }

    async addToWishlist(book) {
        return axios.post(this.HOST_URL + "/add", {...book})
            .then(res => (res || {}).data || [])
            .catch(err => console.log(err));
    }

    async removeFromWishlist(id) {
        return axios.delete(this.HOST_URL + "/remove/" + id)
            .then(res => res || {})
            .catch(err => console.log(err));

    }
}

export default new WishlistService();