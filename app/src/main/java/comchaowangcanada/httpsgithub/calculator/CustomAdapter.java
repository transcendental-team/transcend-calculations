package comchaowangcanada.httpsgithub.calculator;

/**
 * Created by Transcendental Team on 26/03/2016.
 * Author: Chao Wang
 * This is a a dapter class can map string list to list view application in Android
 */

import android.app.Activity;
import android.content.Context;
import android .view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

public class CustomAdapter extends BaseAdapter{
    private Activity mContext;
    private List<String> mList;
    private LayoutInflater mLayoutInflater = null;


    public CustomAdapter(Activity context, List<String> list) {
        mContext = context;
        mList = list;
        mLayoutInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mList.size();
    }
    @Override
    public Object getItem(int pos) {
        return mList.get(pos);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * get list view where contains the text or image.
     * @param position
     * @param convertView
     * @param parent
     * @return View
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater li = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.item_list_app, null);
            viewHolder = new ViewHolder(v);
            v.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) v.getTag();
        }
        String item = (String)getItem(position);
        viewHolder.tv_name.setText(item);
        return v;
    }

    class ViewHolder {
        TextView tv_name;
        // ImageView iv_icon;
        public ViewHolder(View view) {
            // iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            view.setTag(this);
        }
    }
}





