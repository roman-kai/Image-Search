package test.rmbk.com.imagesearch;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    List<ImageResult> images;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public void setImages(List<ImageResult> images){
        this.images = images;
        notifyDataSetChanged();
    }

    public int getCount() {
        if(images != null)
            return images.size();
        else
            return 0;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView imageView;
        TextView textView;
        View layout;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout = inflater.inflate(R.layout.gridview_item, parent, false);
            imageView = (ImageView)layout.findViewById(R.id.imageView);
            textView = (TextView) layout.findViewById(R.id.textView);
        } else {
            layout = convertView;
            imageView = (ImageView)convertView.findViewById(R.id.imageView);
            textView = (TextView) convertView.findViewById(R.id.textView);
        }

        ImageLoader.getInstance().displayImage(images.get(position).getUrl(), imageView);
        textView.setText(images.get(position).getName());

        return layout;
    }

}