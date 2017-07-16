package com.example.android.booklistingappudacity;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import java.util.List;


public class BookAdapter extends ArrayAdapter<Book> {

    private static final String LOG_TAG = BookAdapter.class.getName();

    private static Context mContext;

    //CONSTRUCTOR FOR THE NEW BOOKADAPTER
    public BookAdapter(Context context, List<Book> books) {
        super(context, 0, books);
        mContext = context;
    }
    //RETURN A LIST ITEM VIEW ABOUT THE BOOK AT THE CURRENT POSITION
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String imageLink = "";
        BookViewHolder bookHolder;
        double mBookPrice = 0;
        String mPriceCcAdded = "";
        String ratingStarImage = "";
        String mBookCurrCode = "";
        String mBookAuthor = "";

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.book_list_items, parent, false);

            bookHolder = new BookViewHolder(convertView);
            convertView.setTag(bookHolder);
        } else{
            bookHolder = (BookViewHolder) convertView.getTag();
        }
        //GETTING THE POSITION
        Book currentBook = getItem(position);

        //SETTING THE AUTHOR
        mBookAuthor = currentBook.getmAuthor();
        bookHolder.authorTextView.setText(mBookAuthor);

        //SETTING THE TITLE
        bookHolder.titleTextView.setText(currentBook.getmTitle());

        //SETTING THE TEXT FOR THE PRICE AND THE CURRENCY CODE
        mBookPrice = currentBook.getmRetailPrice();
        mBookCurrCode = currentBook.getmCurrecyCode();

        if (mBookPrice != 00){
            mPriceCcAdded = mBookCurrCode + mBookPrice;
        } else{
            mPriceCcAdded = "0.00";
        }

        //SETTING THE PRICE WITH THE CURRENCY CODE
        bookHolder.priceTextView.setText(mPriceCcAdded);

        //SETTING THE RATING
        ratingStarImage = getRatingImage(currentBook.getmRating());
        int imageResourceId = mContext.getResources().getIdentifier(ratingStarImage, "drawable", mContext.getPackageName());
        bookHolder.ratingImageView.setImageResource(imageResourceId);

        //SETTING THE THUMBNAIL IF IT'S AVAILABLE
        imageLink = currentBook.getmThumbnailLink();
        if (imageLink != null && imageLink.length() > 0){
            new imageAsyncTask(bookHolder.thumbnailImageView).execute(currentBook.getmThumbnailLink());
        } else {
            bookHolder.thumbnailImageView.setImageResource(R.drawable.no_preview);
        }

        return convertView;
    }
    //GETTING THE RATINGS RESOURCE ID DEPENDING ON THE RATING OF THE BOOK
    private String getRatingImage(double rating){
        String ratingResource;
        
        switch (String.valueOf(rating)){
            case "0.5":
                ratingResource = mContext.getString(R.string.rating_half);
                break;
            case "1.0":
                ratingResource = mContext.getString(R.string.rating_one);
                break;
            case "1.5":
                ratingResource = mContext.getString(R.string.rating_one_half);
                break;
            case "2.0":
                ratingResource = mContext.getString(R.string.rating_two);
                break;
            case "2.5":
                ratingResource = mContext.getString(R.string.rating_two_half);
                break;
            case "3.0":
                ratingResource = mContext.getString(R.string.rating_three);
                break;
            case "3.5":
                ratingResource = mContext.getString(R.string.rating_three_half);
                break;
            case "4.0":
                ratingResource = mContext.getString(R.string.rating_four);
                break;
            case "4.5":
                ratingResource = mContext.getString(R.string.rating_four_half);
                break;
            case "5.0":
                ratingResource = mContext.getString(R.string.rating_five);
                break;
            default:
                ratingResource = mContext.getString(R.string.rating_none);
                break;
        }
        return ratingResource;
    }
}
