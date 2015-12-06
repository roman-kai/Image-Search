package test.rmbk.com.imagesearch;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class GoogleImageLoader {

    SearchResult currentSearch;
    List<ImageResult> images;
    String query;

    ImageAdapter adapter;

    public GoogleImageLoader(ImageAdapter adapter){
        this.adapter = adapter;
        images = new ArrayList<>();
    }

    public void loadItems(String query) {
        try {
            String newQuery = URLEncoder.encode(query, "utf-8");
            if (!newQuery.equalsIgnoreCase(this.query)) {
                // new query
                this.query = newQuery;
                currentSearch = null;
                images.clear();
                adapter.setImages(images);
                new RequestImagesTask().execute();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void updateImages(){
        if(currentSearch == null || currentSearch.getItems() == null)
            return;

        images.addAll(currentSearch.getItems());
        adapter.setImages(images);
    }

    public void loadMore(){
        if(query != null)
            new RequestImagesTask().execute();
    }

    public void removeItem(int position){
        images.remove(position);
        adapter.setImages(images);
    }

    class RequestImagesTask extends AsyncTask<Void, Integer, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            String key= "AIzaSyAGyWZnhaCdaPZ_YItXvCm0RZa9tbbQL7M";
            String cx = "006227774635939620012:n_itghgx1km";
            int startIndex;
            if(currentSearch == null)
                startIndex = 1;
            else
                startIndex = currentSearch.getNextIndex();

            URL url = null;
            try {
                url = new URL(
                        "https://www.googleapis.com/customsearch/v1?key=" + key + "&cx=" + cx +
                                "&start=" + startIndex + "&q=" + query +
                                "&imgSize=medium&searchType=image&enableImageSearch=true&alt=json");

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept", "application/json");
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        (conn.getInputStream())));

                currentSearch = new Gson().fromJson(br, SearchResult.class);

                conn.disconnect();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void params) {
            updateImages();
            return;
        }
    }
}
