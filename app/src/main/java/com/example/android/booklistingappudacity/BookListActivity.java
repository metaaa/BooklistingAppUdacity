package com.example.android.booklistingappudacity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class BookListActivity extends AppCompatActivity implements LoaderCallbacks<List<Book>> {

    public static final String LOG_TAG = BookListActivity.class.getName();

    //THE BASE API URL FOR THE QUERY - THIS WILL BE EXTENDED
    private static final String BOOKS_REQUEST_URL = "https://www.googleapis.com/books/v1/volumes?q=";
    //VALUE FOR THE BOOKLOADER ID
    private  static final int BOOK_LOADER_ID = 1;

    Context mContext = this;

    private String mSearchedAuthor;
    private String mSearchedTitle;

    private BookAdapter mAdapter;

    private TextView mEmptyStateTextView;

    public static List<Book> mBookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        //GETTING THE EXTRAS FROM THE INTENT
        if (getIntent().getExtras() != null){
            Bundle bundle = getIntent().getExtras();
            mSearchedAuthor = bundle.getString("bookAuthor");
            mSearchedTitle = bundle.getString("bookTitle");
        }
        //REFERENCE FOR THE LIST
        ListView bookListView = (ListView) findViewById(R.id.book_list);
        //SETTING UP THE EMPTY VIEW
        mEmptyStateTextView = (TextView) findViewById(R.id.empty_text_view);
        bookListView.setEmptyView(mEmptyStateTextView);
        //CREATING A NEW ADAPTER WHICH GETS A LIST OF BOOKS
        mBookList = new ArrayList<Book>();
        mAdapter = new BookAdapter(this, mBookList);

        bookListView.setAdapter(mAdapter);
        //THIS CONNECTIVITY MANAGER CHECKS THE STATE OF NETWORK CONNECTION
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        //GETS INFO ABOUT THE NETWORK CONNECTION AND IF THERE IS, THE APP WILL FETCH DATA
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()){
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(BOOK_LOADER_ID, null, this);
        } else {
            View progressIndicator = findViewById(R.id.progress_indicator);
            mEmptyStateTextView.setError(getString(R.string.error_no_internet));
        }
        //SETTING AN ONITEMCLICKLISTENER FOR THE ITEMS OF THE LIST
        bookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext, BookDetailsActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });
    }

    @Override
    public Loader<List<Book>> onCreateLoader(int id, Bundle bundle) {
        //USING SHARED PREFERENCES TO STORE THE CHOOSEN OPTIONS
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String orderBy = sharedPrefs.getString(
                getString(R.string.settings_order_by_key),
                getString(R.string.settings_order_by_default)
        );

        String maxResults = sharedPrefs.getString(
                getString(R.string.settings_maxresults_key),
                getString(R.string.settings_maxresults_default)
        );

        Uri baseUri = Uri.parse(prepareSearchQuery());
        Uri.Builder uriBuilder = baseUri.buildUpon();

        //APPEND THE PARAMETERS OBTAINED FROM THE OPTIONS
        uriBuilder.appendQueryParameter("maxResults", maxResults);
        uriBuilder.appendQueryParameter("orderBy", orderBy);

        return new BookLoader(mContext, uriBuilder.toString());
    }



    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> books) {

        //HIDE THE PROGRESSBAR IF THE DATA IS AVAILABLE
        View progressIndicator = findViewById(R.id.progress_indicator);
        progressIndicator.setVisibility(View.GONE);
        //SETTING UP THE EMPTY STATE TEXT IN CASE THERE WAS NO DATA
        mEmptyStateTextView.setText(R.string.error_no_matches);
        //CLEARING THE PREVIOUS DATA
        mAdapter.clear();
        //IF THERE IS ANY BOOK ADD THEM TO THE ADAPTER
        if (books != null && !books.isEmpty()){
            mAdapter.addAll(books);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        mAdapter.clear();
    }
    //THIS METHOD CREATES THE FINAL QUERY LINK
    public String prepareSearchQuery() {
        //ADDING THE TITLE
        StringBuilder stringBuilder = new StringBuilder(BOOKS_REQUEST_URL);
        stringBuilder.append("+intitle:").append(mSearchedTitle);

        //ADDING THE AUTHOR
        if ((mSearchedAuthor != null) && (mSearchedAuthor.length() != 0)) {
            stringBuilder.append("+inauthor:").append(mSearchedAuthor);
        }
        return(stringBuilder.toString());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            settingsIntent.putExtra("title", mSearchedTitle);
            settingsIntent.putExtra("author", mSearchedAuthor);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
