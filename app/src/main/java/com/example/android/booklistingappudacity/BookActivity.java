package com.example.android.booklistingappudacity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class BookActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String LOG_TAG = BookActivity.class.getName();

    Context mContext = this;

    private String mBookTitle;
    private String mBookAuthor;

    //COMPONENTS OF THE UI
    private EditText mBookTitleEditText;
    private EditText mBookAuthorEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        //INTIALIZING THE COMPONENTS OF THE UI
        mBookAuthorEditText = (EditText) findViewById(R.id.book_author_search);
        mBookTitleEditText = (EditText) findViewById(R.id.book_title_search);
        Button mSearchButton = (Button) findViewById(R.id.book_search_button);

        //SETTING AN ONCLICKLISTENER ON THE SEARCH BUTTON
        mSearchButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.book_search_button:
                showSearchResults();
                break;
        }
    }
    //IF THE ENTERED INPUT IS VALID THIS METHOD WILL LAUNCH THE BOOKLISTACTIVITY
    public void showSearchResults(){
        if (validatingTheInput()){
            Intent intent = new Intent(mContext, BookListActivity.class);

            intent.putExtra("bookAuthor", mBookAuthor.replaceAll(" ", "+"));
            intent.putExtra("bookTitle", mBookTitle.replaceAll(" ", "+"));
            startActivity(intent);
        }
    }

    public boolean validatingTheInput(){
        //USE THE TRIM METHOD TO REMOVE WHITESPACES FROM THE STRING
        mBookAuthor = mBookAuthorEditText.getText().toString().trim();
        mBookTitle = mBookTitleEditText.getText().toString().trim();

        //CHECKING IF THE BOOK AUTHOR IS ENTERED
        if (Utils.checkEmptyString(mBookAuthor)){
            if (!Utils.checkValidString(mBookAuthor)){
                mBookAuthorEditText.setError(getString(R.string.book_author_error_message));
                return false;
            }
        }

        if (!Utils.checkEmptyString(mBookTitle)){
            mBookTitleEditText.setError(getString(R.string.missing_book_title_error));
            return false;
        }
        return true;
    }
}
