package test.rmbk.com.imagesearch;

import android.content.SearchRecentSuggestionsProvider;

public class ImageSuggestionProvider extends SearchRecentSuggestionsProvider {
    public final static String AUTHORITY = "test.rmbk.com.imagesearch.ImageSuggestionProvider";
    public final static int MODE = DATABASE_MODE_QUERIES;

    public ImageSuggestionProvider() {
        setupSuggestions(AUTHORITY, MODE);
    }
}