package com.example.android.booklistingappudacity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BookDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String LOG_TAG = BookActivity.class.getName();

    final Context mContext = this;

    // COMPONENTS OF THE UI
    private TextView mTitleTextView;
    private TextView mAuthorTextView;
    private TextView mPublishDateTextView;
    private TextView mPublisherTextView;
    private TextView mCategoryTextView;
    private TextView mPrintTypeTextView;
    private TextView mRatingTextView;
    private TextView mPriceTextView;
    private TextView mDescriptionTextView;
    private TextView mPageCountTextView;
    private ImageView mThumbnailImageView;
    private ImageView mEpubImageView;
    private ImageView mPdfImageView;
    private Button mBuyButton;
    private Button mPreviewButton;

    private Book mSelectedBook;

    private String mBuyingLink;
    private String mPublishedDate;
    private String mPreviewLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        int position;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detailed_info);
        //GETTING THE EXTRAS FOR THE INTENT
        if (getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            position = bundle.getInt("position");

            //GETTING THE ON CLICKED BOOK
            mSelectedBook = BookListActivity.mBookList.get(position);
        }

        //INITIALIZING THE COMPONENTS OF THE UI
        mTitleTextView = (TextView) findViewById(R.id.detailed_book_title);
        mAuthorTextView = (TextView) findViewById(R.id.detailed_book_author);
        mPublishDateTextView = (TextView) findViewById(R.id.detailed_book_release_date);
        mPublisherTextView = (TextView) findViewById(R.id.detailed_book_publisher);
        mCategoryTextView = (TextView) findViewById(R.id.detailed_book_release_category);
        mPrintTypeTextView = (TextView) findViewById(R.id.detailed_book_print_type);
        mRatingTextView = (TextView) findViewById(R.id.detailed_book_rating);
        mPriceTextView = (TextView) findViewById(R.id.detailed_book_price);
        mDescriptionTextView = (TextView) findViewById(R.id.detailed_book_description);
        mPageCountTextView = (TextView) findViewById(R.id.detailed_book_page_count) ;

        mThumbnailImageView = (ImageView) findViewById(R.id.detailed_book_thumbnail);
        mEpubImageView = (ImageView) findViewById(R.id.epub_image_view);
        mPdfImageView = (ImageView) findViewById(R.id.pdf_image_view);

        mBuyButton = (Button) findViewById(R.id.buy_button);
        mPreviewButton = (Button) findViewById(R.id.preview_button);
        //SETTING ONCLICKLISTENERS ON THE AVAILABLE BUTTONS
        mBuyButton.setOnClickListener(this);
        mPreviewButton.setOnClickListener(this);

        //STARTING THE METHOD WHICH WILL DISPLAY THE PROPER DATA AT THE PROPER PLACE OF THE UI
        displayBookDetails();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buy_button:
                openBuyLink();
                break;
            case R.id.preview_button:
                openPreviewLink();
                break;
        }
    }
    //METHOD TO GET THE THUMBNAIL OF THE BOOK - USING AN ASYNCTASK
    public void displayImage() {
        new imageAsyncTask(mThumbnailImageView).execute(mSelectedBook.getmThumbnailLink());
    }
    //METHOD TO GET THE TITLE OF THE BOOK
    public void displayTitle(){
        mTitleTextView.setText(mSelectedBook.getmTitle());
    }
    //METHOD TO GET THE AUTHOR OF THE BOOK
    public void displayAuthor(){
        String author = mSelectedBook.getmAuthor();
        if (author != null && author.length() > 0){
            mAuthorTextView.setText(author);
        } else {
            mAuthorTextView.setText("");
        }
    }
    //GETTING AND FORMATTING THE PUBLISH DATE
    public void formatDate() {
        String date = mSelectedBook.getmPublishDate();
        String dateNew = "";
        mPublishedDate = "";
        //CHECKING IF THE DATE IS IN YYYY OR YYYY-MM OR YYYY-MM-DD FORMAT
        if (date != null && date.length() != 0) {
            if (date.length() == 4 || date.length() == 7 || date.length() == 10 ) {
                dateNew = date;
            } else if (date.length() > 10) {
                dateNew = date.substring(0, 10);
            }

            //NEW DATE FORMAT
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat newFormat = new SimpleDateFormat("MMM yyyy");
            try {
                Date dt = inputFormat.parse(dateNew);
                mPublishedDate = (getString(R.string.label_published) + newFormat.format(dt));
            }
            catch(ParseException exception) {
                Log.e(LOG_TAG, mContext.getString(R.string.error_date_parsing), exception);
            }
        }
        mPublishDateTextView.setText(mPublishedDate);
    }
    //DISPLAY THE PUBLISHER
    public void displayPublisher(){
        String publisher = mSelectedBook.getmPublisher();
        if (publisher != null && publisher.length() > 0){
            mPublisherTextView.setText(getString(R.string.label_publisher) + publisher);
        } else {
            mPublisherTextView.setText(getString(R.string.label_publisher) + getString(R.string.info_unavailable));
        }
    }
    //DISPLAY THE CATEGORY OF THE BOOK
    public void displayCategory(){
        String category = mSelectedBook.getmCategory();
        if (category != null && category.length() > 0){
            mCategoryTextView.setText(getString(R.string.label_category) + category);
        } else {
            mCategoryTextView.setText(getString(R.string.label_category) + getString(R.string.info_unavailable));
        }
    }
    //DISPLAY THE PRINT TYPE OF THE BOOK
    public void displayPrintType(){
        String printType = mSelectedBook.getmPrintType();
        if (printType != null && printType.length() > 0){
            mPrintTypeTextView.setText(getString(R.string.label_print_type) + printType);
        } else {
            mPrintTypeTextView.setText(getString(R.string.label_print_type) + getString(R.string.info_unavailable));
        }
    }
    //DISPLAY THE RATING OF THE BOOK
    public void displayRating() {
        double rating = mSelectedBook.getmRating();

        if (rating != 0.00) {
            mRatingTextView.setText(getString(R.string.label_rating) + rating);
        } else {
            mRatingTextView.setText(getString(R.string.label_rating) + getString(R.string.info_unavailable));
        }
    }
    //DISPLAY THE PRICE OF THE BOOK
    public void displayPrice() {
        double price = mSelectedBook.getmRetailPrice();
        String retailPrice;
        if (price != 0.00) {
            retailPrice = getString(R.string.detailed_price_label) + mSelectedBook.getmCurrecyCode() + " " + price;
        } else {
            retailPrice = (getString(R.string.detailed_price_label) + getString(R.string.info_unavailable));
        }

        mPriceTextView.setText(retailPrice);
    }
    //DISPLAY THE DESCRIPTION ABOUT THE BOOK
    public void displayDescription(){
        mDescriptionTextView.setText(mSelectedBook.getmDescription());
    }
    //DISPLAY THE PAGE COUNT OF THE BOOK
    public void displayPageCount(){
        int pageCount = mSelectedBook.getmPageCount();
        String output = "";
        if (pageCount > 0){
            output = getString(R.string.label_pp) + pageCount;
            mPageCountTextView.setText(output);
        } else {
            mPageCountTextView.setText("Pp: NA");
        }
    }
    //DISPLAYS THE PROPER IMAGE DEPENDING ON THE AVAILABILITY OF THE EPUB
    public void displayEpub() {
        boolean epub = mSelectedBook.ismIsEpubAvailable();
        if (epub){
            mEpubImageView.setImageResource(R.drawable.ic_check_box_black_24dp);
        } else {
            mEpubImageView.setImageResource(R.drawable.ic_check_box_outline_blank_black_24dp);
        }
    }
    //DISPLAYS THE PROPER IMAGE DEPENDING ON THE AVAILABILITY OF THE PDF
    public void displayPdf() {
        boolean pdf = mSelectedBook.ismIsPdfAvailable();
        if (pdf){
            mPdfImageView.setImageResource(R.drawable.ic_check_box_black_24dp);
        } else{
            mPdfImageView.setImageResource(R.drawable.ic_check_box_outline_blank_black_24dp);
        }
    }
    //GETTING THE BUY LINK
    public void setBuyLink() {
        mBuyingLink = mSelectedBook.getmBuyLink();

        if (mBuyingLink != null && mBuyingLink.length() == 0) {
            mBuyButton.setVisibility(View.GONE);
        }
    }
    //GETTING THE PREVIEW LINK
    public void setPreviewLink() {
        mPreviewLink = mSelectedBook.getmPreviewLink();

        if (mPreviewLink != null && mPreviewLink.length() == 0) {
            mPreviewButton.setVisibility(View.GONE);
        }
    }
    //METHOD TO OPEN THE BUY LINK
    public void openBuyLink() {
        Uri bookBuyURL = Uri.parse(mBuyingLink);
        Intent webIntent = new Intent(Intent.ACTION_VIEW, bookBuyURL);
        startActivity(webIntent);
    }
    //METHOD TO OPEN THE PREVIEW LINK
    public void openPreviewLink() {
        Uri bookPreviewURL = Uri.parse(mPreviewLink);
        Intent webIntent = new Intent(Intent.ACTION_VIEW, bookPreviewURL);
        startActivity(webIntent);
    }
    //CALLING ALL THE METHODS TO DISPLAY ALL THE DATA NEEDED
    public void displayBookDetails() {
        displayImage();
        displayTitle();
        displayAuthor();
        formatDate();
        displayPublisher();
        displayCategory();
        displayPrintType();
        displayRating();
        displayPrice();
        displayDescription();
        displayPageCount();
        displayEpub();
        displayPdf();
        setBuyLink();
        setPreviewLink();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
