package comchaowangcanada.httpsgithub.calculator;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;


/**
 * Created by Transcendental Team on 25/03/2016.
 * Author: Chao Wang
 * This class create the fragment frame for history expression list
 */


public class HistoryFragment extends Fragment {

    private  CustomAdapter historyAdapter;

    public HistoryFragment() {
        // Required empty public constructor
    }

    /**
     * create the display of list view
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return return true if succeeded
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_history, container, false);
        historyAdapter = new CustomAdapter(getActivity(), StaticParameterCenter.historyList);
        SwipeMenuListView historyListview = (SwipeMenuListView) rootView.findViewById(R.id.history_listView);
        historyListview.setAdapter(historyAdapter);

        // create the swipe menu,
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem item1 = new SwipeMenuItem(
                        getActivity().getApplicationContext());
                item1.setBackground(new ColorDrawable(Color.rgb(0xE5, 0xE0,
                        0x3F)));
                item1.setWidth(dp2px(90));
                item1.setIcon(R.drawable.ic_action_important);
                menu.addMenuItem(item1);

                SwipeMenuItem item2 = new SwipeMenuItem(
                        getActivity().getApplicationContext());
                item2.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                item2.setWidth(dp2px(90));
                item2.setIcon(R.drawable.ic_action_discard);
                menu.addMenuItem(item2);
            }
        };

        historyListview.setMenuCreator(creator);

        // create the click event for add to favorite and delete
        historyListview.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                String item = StaticParameterCenter.historyList.get(position);
                switch (index) {
                    case 0:
                        // if already in the favorite list, display a message
                        if (isDuplicateInFavorite(item)) {
                            Toast.makeText(getActivity().getApplicationContext(),
                                    "Already exist in Favorite List",
                                    Toast.LENGTH_SHORT).show();
                        }
                        // add to favorite list
                        else {
                            StaticParameterCenter.favoriteList.add
                                    (StaticParameterCenter.historyList.get(position).toString());
                            Toast.makeText(getActivity().getApplicationContext(),
                                    "Added to Favorite List",
                                    Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 1:
                    // delete(item);
                        StaticParameterCenter.historyList.remove(position);
                        historyAdapter.notifyDataSetChanged();
                        break;
                }
                return false;
            }
        });



        historyListview.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    /**
                     * if click the expression, will bring the expression to calculator
                     */
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String str = String.valueOf(parent.getItemAtPosition(position));
                        str = str.replaceAll("\\s+","");

                        CalculatorFragment fragment = new CalculatorFragment();
                        android.support.v4.app.FragmentTransaction fragmentTransaction =
                                getActivity().getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container, fragment);
                        fragmentTransaction.commit();
                        getFragmentManager().executePendingTransactions();

                        fragment.setExpressionText(str.substring(0, str.indexOf('=')));
                        fragment.setResultText(str.substring(str.indexOf('=') + 1, str.length()));
                        fragment.setExpressionTextSize(StaticParameterCenter.MediumTextSize);
                    }
                }
        );
        // Inflate the layout for this fragment
        return rootView;
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }


    public boolean isDuplicateInFavorite(String str){
        for( String element : StaticParameterCenter.favoriteList){
            if (str.equals(element))
                return true;
        }

        return false;

    }
}
