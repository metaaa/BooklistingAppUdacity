package com.example.android.booklistingappudacity;

import android.content.AsyncTaskLoader;
import android.content.Context;
import java.util.List;

//THIS WILL LOAD THE LIS OF BOOK USING THE API WITH AN ASYNCTASK
public class BookLoader extends AsyncTaskLoader<List<Book>> {

    private static final String LOG_TAG = BookLoader.class.getName();

    private String mUrl;
    //NEW OBJECT
    public BookLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
    //FETCH THE DATA IN THE BACKGROUND
    @Override
    public List<Book> loadInBackground() {
        if (mUrl == null){
            return null;
        }
        List<Book> books = QueryUtils.fetchBookData(mUrl, getContext());

        return books;
    }
}
