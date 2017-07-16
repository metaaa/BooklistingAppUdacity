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
    private String mLang;               //LANGUAGE OF THE BOOK
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
                String currencyCode, String printType, String lang, String thumbnailLink, String previewLink, String buyLink, int pageCount,
                double rating, double retailPrice, boolean isEpubAvailable, boolean isPdfAvailable){

        mTitle = title;
        mAuthor = author;
        mPublisher = publisher;
        mPublishDate = publishDate;
        mDescription = description;
        mCategory = category;
        mCurrecyCode = currencyCode;
        mPrintType = printType;
        mLang = lang;
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

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmAuthor() {
        return mAuthor;
    }

    public void setmAuthor(String mAuthor) {
        this.mAuthor = mAuthor;
    }

    public String getmPublisher() {
        return mPublisher;
    }

    public void setmPublisher(String mPublisher) {
        this.mPublisher = mPublisher;
    }

    public String getmPublishDate() {
        return mPublishDate;
    }

    public void setmPublishDate(String mPublishDate) {
        this.mPublishDate = mPublishDate;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmCategory() {
        return mCategory;
    }

    public void setmCategory(String mCategory) {
        this.mCategory = mCategory;
    }

    public String getmCurrecyCode() {
        return mCurrecyCode;
    }

    public void setmCurrecyCode(String mCurrecyCode) {
        this.mCurrecyCode = mCurrecyCode;
    }

    public String getmPrintType() {
        return mPrintType;
    }

    public void setmPrintType(String mPrintType) {
        this.mPrintType = mPrintType;
    }

    public String getmLang() {
        return mLang;
    }

    public void setmLang(String mLang) {
        this.mLang = mLang;
    }

    public String getmThumbnailLink() {
        return mThumbnailLink;
    }

    public void setmThumbnailLink(String mThumbnailLink) {
        this.mThumbnailLink = mThumbnailLink;
    }

    public String getmPreviewLink() {
        return mPreviewLink;
    }

    public void setmPreviewLink(String mPreviewLink) {
        this.mPreviewLink = mPreviewLink;
    }

    public String getmBuyLink() {
        return mBuyLink;
    }

    public void setmBuyLink(String mBuyLink) {
        this.mBuyLink = mBuyLink;
    }

    public int getmPageCount() {
        return mPageCount;
    }

    public void setmPageCount(int mPageCount) {
        this.mPageCount = mPageCount;
    }

    public double getmRating() {
        return mRating;
    }

    public void setmRating(double mRating) {
        this.mRating = mRating;
    }

    public double getmRetailPrice() {
        return mRetailPrice;
    }

    public void setmRetailPrice(double mRetailPrice) {
        this.mRetailPrice = mRetailPrice;
    }

    public boolean ismIsEpubAvailable() {
        return mIsEpubAvailable;
    }

    public void setmIsEpubAvailable(boolean mIsEpubAvailable) {
        this.mIsEpubAvailable = mIsEpubAvailable;
    }

    public boolean ismIsPdfAvailable() {
        return mIsPdfAvailable;
    }

    public void setmIsPdfAvailable(boolean mIsPdfAvailable) {
        this.mIsPdfAvailable = mIsPdfAvailable;
    }
}
