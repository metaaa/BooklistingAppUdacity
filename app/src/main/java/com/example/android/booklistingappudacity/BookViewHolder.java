package com.example.android.booklistingappudacity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

//THIS CLASS IDENTIFIES THE VIEW ITEMS
public class BookViewHolder {

    TextView titleTextView;
    TextView authorTextView;
    TextView priceTextView;
    ImageView ratingImageView;
    ImageView thumbnailImageView;

    public BookViewHolder(View view){

        titleTextView = (TextView) view.findViewById(R.id.book_title);
        authorTextView = (TextView) view.findViewById(R.id.book_author);
        priceTextView = (TextView) view.findViewById(R.id.book_price);
        ratingImageView = (ImageView) view.findViewById(R.id.book_rating);
        thumbnailImageView = (ImageView) view.findViewById(R.id.book_thumbnail);
    }
}
