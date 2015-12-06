package test.rmbk.com.imagesearch;

import com.google.gson.annotations.SerializedName;

public class ImageResult {

    @SerializedName("title")
    private String name;

    private String link;


    public String getName() {
        return name;
    }

    public String getUrl() {
        return link;
    }


}

