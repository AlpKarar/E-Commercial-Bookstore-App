import {React, useState} from 'react';

const adjustTitle = (title) => {
    if (title.length > 22) {
        return title.substr(22) + '...';
    }

    return title
}

const Book = () => {
    return (
        <div class="p-3">
            <img src="https://i.dr.com.tr/cache/600x600-0/originals/0000000724380-1.jpg" width="120" height="120"/>
            <div class="mt-4">
                <div class="title font-weight-bold">{adjustTitle("Savaş Ve Barış")}</div>
                <div class="text-muted"><span class="font-italic">Tolstoy</span></div>
            </div>
        </div>
    )
}

export default Book;