package com.example.android.booklistingappudacity;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;



public class QueryUtils {

    private static final String LOG_TAG = QueryUtils.class.getName();
    private static Context mContext;

    //EMPTY CONSTRUCTOR
    public QueryUtils() {
    }
    //THIS WILL FETCH THE DATA FROM THE API
    public static List<Book> fetchBookData (String requestUrl, Context context){

        mContext = context;
        URL url = createUrl(requestUrl);
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException exception){
            Log.e(LOG_TAG, mContext.getString(R.string.error_http_request), exception);
        }
        List<Book> books = extractFeatureFromJson(jsonResponse);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException exception){
            exception.printStackTrace();
        }
        return books;
    }
    //METHOD TO CREATE THE URL
    private static URL createUrl(String stringUrl){
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException exception){
            Log.e(LOG_TAG, mContext.getString(R.string.error_create_url), exception);
        }
        return url;
    }
    //THIS METHOD SETS UP THE HTTP CONNECTION
    private static String makeHttpRequest(URL url) throws IOException{
        String jsonResponse = "";

        if (url == null){
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        //TRY TO CONNECT
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200){
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, mContext.getString(R.string.error_response_code) + urlConnection.getResponseCode());
            }
        } catch (IOException exception){
            Log.e(LOG_TAG, mContext.getString(R.string.error_json_connection), exception);
        } finally {
            if (urlConnection != null){
                urlConnection.disconnect();
            }
            if (inputStream != null){
                inputStream.close();
            }
        }
        return jsonResponse;
    }
    //CONVERT THE INPUTSTREAM TO A STRING WHICH HAS THE WHOLE QUERY RESULT
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }
    //RETURN A LIST OF BOOK OBJECTS FROM JSON PARSING
    private static List<Book> extractFeatureFromJson(String bookJSON){
        //CHECKING THE JSON CONTENT
        if (TextUtils.isEmpty(bookJSON)){
            return null;
        }
        //EMPTY ARRAYLIST TO STORE THE BOOKS IN
        List<Book> books = new ArrayList<>();
        //VARIABLES AND NODES TO PARSE THROUGH THE JSON AND TO GET THE NEEDED VALUES
        try {
            JSONObject baseJsonResponse;

            JSONArray bookArray;

            JSONObject currentBook = null;

            JSONObject volumeInfo = null;
            String title = "";

            JSONArray authorsArray;
            String authorsList = "";
            String publisher = "";
            String publishedDate = "";
            String description = "";
            int pageCount = 0;
            String printType = "";

            JSONArray categoriesArray;
            String category = "";
            double averageRating = 0.0;

            JSONObject imageLinks;
            String thumbnailLink = "";
            String previewLink = "";
            String language = "";

            JSONObject saleInfo;
            JSONObject retailPrice;
            double price = 0.0;
            String currencyCode = "";
            String buyLink = "";

            JSONObject accessInfo;
            JSONObject epub;
            boolean isEpubAvailable = false;

            JSONObject pdf;
            boolean isPdfAvailable = false;

            baseJsonResponse = new JSONObject(bookJSON);

            if (baseJsonResponse.has("items")){
                bookArray = baseJsonResponse.getJSONArray("items");

                //GETTING THE TITLE OF THE BOOK
            for (int i = 0; i < bookArray.length(); i++){
                    currentBook = bookArray.getJSONObject(i);
                    volumeInfo = currentBook.getJSONObject("volumeInfo");
                    title = volumeInfo.getString("title");

                //GETTING THE AUTHOR OF THE BOOK
                if (volumeInfo.has("authors")){
                    authorsArray = volumeInfo.getJSONArray("authors");
                    authorsList = authorsArray.getString(0);
                } else {
                    authorsList = "";
                }
                //GETTING THE PUBLISHER
                if (volumeInfo.has("publisher")){
                    publisher = volumeInfo.getString("publisher");
                } else {
                    publisher = "";
                }
                //GETTING THE PUBLISHED DATE
                if (volumeInfo.has("publishedDate")){
                    publishedDate = volumeInfo.getString("publishedDate");
                } else {
                    publishedDate = "";
                }
                //GETTING THE DESCRIPTION
                if (volumeInfo.has("description")){
                    description = volumeInfo.getString("description");
                } else {
                    description = "";
                }
                //GETTING THE PAGE COUNT
                if (volumeInfo.has("pageCount")){
                    pageCount = volumeInfo.getInt("pageCount");
                } else {
                    pageCount = 0;
                }
                //GETTING THE PRINT TYPE
                if (volumeInfo.has("printType")){
                    printType = volumeInfo.getString("printType");
                } else{
                    printType = "";
                }
                //GETTING THE VALUE FOR CATEGORIES
                if (volumeInfo.has("categories")){
                    categoriesArray = volumeInfo.getJSONArray("categories");
                    category = categoriesArray.getString(0);
                } else {
                    category = "";
                }
                //GETTING THE RATING
                if (volumeInfo.has("averageRating")){
                    averageRating = volumeInfo.getDouble("averageRating");
                } else {
                    averageRating = 0.0;
                }
                //GETTING THE THUMBNAIL LINK
                if (volumeInfo.has("imageLinks")){
                    imageLinks = volumeInfo.getJSONObject("imageLinks");
                    if (imageLinks.has("thumbnail")){
                        thumbnailLink = imageLinks.getString("thumbnail");
                    } else{
                        thumbnailLink = "";
                    }
                }
                //GETTING THE LANGUAGE
                if (volumeInfo.has("language")){
                    language = volumeInfo.getString("language");
                } else {
                    language = "";
                }
                //GETTING THE PREVIEW LINK
                if (volumeInfo.has("previewLink")){
                    previewLink = volumeInfo.getString("previewLink");
                } else{
                    previewLink = "";
                }
                //GETTING THE PRICE, THE CURRENCY CODE AND THE BUYING LINK
                if (currentBook.has("saleInfo")){
                    saleInfo = currentBook.getJSONObject("saleInfo");
                    if (saleInfo.has("retailPrice")){
                        retailPrice = saleInfo.getJSONObject("retailPrice");
                        if (retailPrice.has("amount")){
                            price = retailPrice.getDouble("amount");
                        } else {
                            price = 0.0;
                        }
                        if (retailPrice.has("currencyCode")){
                            currencyCode = retailPrice.getString("currencyCode");
                        } else {
                            currencyCode = "";
                        }
                    }
                    if (saleInfo.has("buyLink")){
                        buyLink = saleInfo.getString("buyLink");
                    } else {
                        buyLink = "";
                    }
                }
                //GETTING THE EPUB AND PDF AVAILABILITY
                if (currentBook.has("accessInfo")){
                    accessInfo = currentBook.getJSONObject("accessInfo");
                    if (accessInfo.has("epub")){
                        epub = accessInfo.getJSONObject("epub");
                        if (epub.has("isAvailable")){
                            isEpubAvailable = epub.getBoolean("isAvailable");
                        } else{
                            isEpubAvailable = false;
                        }
                    }
                    if (accessInfo.has("pdf")){
                        pdf = accessInfo.getJSONObject("pdf");
                        if (pdf.has("isAvailable")){
                            isPdfAvailable = pdf.getBoolean("isAvailable");
                        } else {
                            isPdfAvailable = false;
                        }
                    }
                }

                Book book = new Book(title, authorsList, publisher, publishedDate, description, category, currencyCode, printType, language, thumbnailLink, previewLink, buyLink, pageCount, averageRating, price, isEpubAvailable, isPdfAvailable);
                //ADD THE NEW OBJECT TO THE LIST OF BOOKS
                books.add(book);
                }
            }
        }
        catch (JSONException exception){
            Log.e(LOG_TAG, mContext.getString(R.string.error_json_connection), exception);
        }
        return books;
    }
}
