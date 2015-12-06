package test.rmbk.com.imagesearch;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResult {

    @SerializedName("queries")
    private Queries queries;

    private List<ImageResult> items;

    public List<ImageResult> getItems() {
        return items;
    }

    public void setItems(List<ImageResult> items) {
        this.items = items;
    }

    public int getNextIndex(){
        return queries.getNextIndex();
    }

    class Queries{
        @SerializedName("nextPage")
        private List<Request> next;

        public int getNextIndex() {
            if(next != null && next.size() > 0)
                return next.get(0).getStartIndex();
            else
                return 1;
        }

        public class Request {
            int startIndex;

            public int getStartIndex() {
                return startIndex;
            }
        }
    }
}


