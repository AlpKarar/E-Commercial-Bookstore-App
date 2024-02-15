import axios from "axios";

class WishlistService {

    HOST_URL = "http://localhost:5005/api/wishlist";

    async addToWishlist(book) {
        return axios.post(this.HOST_URL + "/add", {...book})
            .then(res => res.data)
            .catch(err => console.log(err));
    }

    async removeFromWishlist(id) {
        return axios.delete(this.HOST_URL + "/remove/" + id)
            .then(res => res.data)
            .catch(err => console.log(err));

    }
}

export default new WishlistService();