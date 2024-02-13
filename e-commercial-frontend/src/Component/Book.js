import {React, useState} from 'react';

const adjustTitle = (title) => {
    if (title.length > 22) {
        return title.substr(22) + '...';
    }

    return title
}

const Book = () => {
    return (
        <div class="d-flex justify-content-center p-3">
            <div>
                <img src="https://i.dr.com.tr/cache/600x600-0/originals/0000000724380-1.jpg" width="120" height="120"/>
                <div class="mt-4">
                    <div class="title font-weight-bold text-center">{adjustTitle("Savaş Ve Barış")}</div>
                    <div class="text-muted text-center"><span class="font-italic">Tolstoy</span></div>
                </div>
            </div>
        </div>
    )
}

export default Book;