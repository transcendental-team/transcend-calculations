package comchaowangcanada.httpsgithub.calculator;

/**
 * Created by Chao on 26/03/2016.
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

//    public CustomAdapter(Context context, List<String> resource) {
//        super(context, R.layout.item_list_app, resource);
//    }


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
        ImageView iv_icon;
        public ViewHolder(View view) {
            //iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            view.setTag(this);
        }
    }
}




    //    This is the getView for arrayAdapter
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        LayoutInflater itemlistInflater = LayoutInflater.from(getContext());
//        View  customView = itemlistInflater.inflate(R.layout.item_list_app, parent, false);
//
//        String singleItem = getItem(position);
//        TextView itemText = (TextView) customView.findViewById(R.id.view_name);
//        ImageView itemImage = (ImageView) customView.findViewById(R.id.imageView);
//
//        itemText.setText(singleItem);
//        //itemImage.setImageResource(R.drawable.calculator);
//
//        return customView;
//    }
//}
