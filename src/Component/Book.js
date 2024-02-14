import {React, useState} from 'react';

const adjustTitle = (title) => {
    if (title.length > 22) {
        return title.substr(22) + '...';
    }

    return title
}

const Book = (props) => {
    return (
        <div className="p-3">
            <div class="d-flex justify-content-center">
                <img src="https://i.dr.com.tr/cache/600x600-0/originals/0000000724380-1.jpg" width="120" height="120"/>
            </div>
            <div className="mt-4">
                <div className="title font-weight-bold text-center">{adjustTitle("Savaş Ve Barış")}</div>
                <div className="text-muted text-center"><span className="font-italic">Tolstoy</span></div>
            </div>
        </div>
    )
}

export default Book;