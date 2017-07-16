package com.example.android.booklistingappudacity;

//THIS CLASS WILL CONTAIN INFORMATIONS A SINGLE BOOK

public class Book {

    private String mTitle;              //TITLE OF THE BOOK
    private String mAuthor;             //AUTHOR OF THE BOOK
    private String mPublisher;          //PUBLISHER OF THE BOOK
    private String mPublishDate;        //PUBLISH DATE OF THE BOOK
    private String mDescription;        //DESCRIPTION OF THE BOOK
    private String mCategory;           //CATEGORY OF THE BOOK
    private String mCurrecyCode;        //CURRENCY CODE
    private String mPrintType;          //PRINT TYPE OF THE BOOK
    private String mThumbnailLink;      //THUMBNAIL LINK OF THE BOOK'S COVER
    private String mPreviewLink;        //EXTERNAL LINK TO THE BOOK
    private String mBuyLink;            //EXTERNAL LINK TO THE PAGE WHERE THE BOOK CAN BE PURCHASED

    private int mPageCount;             //PAGE COUNT OF THE BOOK

    private double mRating;             //RATING OF THE BOOK
    private double mRetailPrice;        //RETAIL PRICE OF THE BOOK

    private boolean mIsEpubAvailable;   //AVAILABILITY OF THE BOOK IN EPUB
    private boolean mIsPdfAvailable;    //AVAILABILITY OF THE BOOK IN PDF

    //DEFAULT CONSTRUCTOR FOR THE OBJECT
    public Book(String title, String author, String publisher, String publishDate, String description, String category,
                String currencyCode, String printType, String thumbnailLink, String previewLink, String buyLink, int pageCount,
                double rating, double retailPrice, boolean isEpubAvailable, boolean isPdfAvailable){

        mTitle = title;
        mAuthor = author;
        mPublisher = publisher;
        mPublishDate = publishDate;
        mDescription = description;
        mCategory = category;
        mCurrecyCode = currencyCode;
        mPrintType = printType;
        mThumbnailLink = thumbnailLink;
        mPreviewLink = previewLink;
        mBuyLink = buyLink;
        mPageCount = pageCount;
        mRating = rating;
        mRetailPrice = retailPrice;
        mIsEpubAvailable = isEpubAvailable;
        mIsPdfAvailable = isPdfAvailable;
    }

    //GETTERS AND SETTOERS OF THE VARIABLES

    public String getmTitle() {
        return mTitle;
    }

    public String getmAuthor() {
        return mAuthor;
    }

    public String getmPublisher() {
        return mPublisher;
    }

    public String getmPublishDate() {
        return mPublishDate;
    }

    public String getmDescription() {
        return mDescription;
    }

    public String getmCategory() {
        return mCategory;
    }

    public String getmCurrecyCode() {
        return mCurrecyCode;
    }

    public String getmPrintType() {
        return mPrintType;
    }

    public String getmThumbnailLink() {
        return mThumbnailLink;
    }

    public String getmPreviewLink() {
        return mPreviewLink;
    }

    public String getmBuyLink() {
        return mBuyLink;
    }

    public int getmPageCount() {
        return mPageCount;
    }

    public double getmRating() {
        return mRating;
    }

    public double getmRetailPrice() {
        return mRetailPrice;
    }

    public boolean ismIsEpubAvailable() {
        return mIsEpubAvailable;
    }

    public boolean ismIsPdfAvailable() {
        return mIsPdfAvailable;
    }

}
