class Utils {

    calculateRowCount(bookCount, bookCountInRow) {
        return bookCount % bookCountInRow === 0 ?
            Math.floor(bookCount / bookCountInRow) :
            Math.floor(bookCount / bookCountInRow) + 1;
    }

    generateRowIds(rowCount) {
        return this.createRangeArray(rowCount);
    }

    generateImageIndexes(imageIndexCount) {
       return  this.createRangeArray(imageIndexCount);
    }

    createRangeArray(count) {
        return Array.from({length: count}, (_, i) => i + 1);
    }
}

export default new Utils();