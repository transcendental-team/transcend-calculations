package comchaowangcanada.httpsgithub.calculator;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;


import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

/**
 * Created by Transcendental Team on 25/03/2016.
 * Author: Chao Wang
 * This class create the fragment frame for favorite expression list
 */

public class FavoriteFragment extends Fragment {

    private  CustomAdapter favoriteAdapter;

    public FavoriteFragment() {
        // Required empty public constructor
    }

    /**
     * Create the favorite list view
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return return true if succeeded
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_favorite, container, false);
        favoriteAdapter = new CustomAdapter(getActivity(), StaticParameterCenter.favoriteList);
        SwipeMenuListView favoriteListview
                            = (SwipeMenuListView) rootView.findViewById(R.id.favorite_listView);
        favoriteListview.setAdapter(favoriteAdapter);

        SwipeMenuCreator creator = new SwipeMenuCreator() {
            /**
             * create the swipe menu
             * @param menu
             */
            @Override
            public void create(SwipeMenu menu) {

                SwipeMenuItem item1 = new SwipeMenuItem(
                        getActivity().getApplicationContext());
                item1.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                item1.setWidth(dp2px(90));
                item1.setIcon(R.drawable.ic_action_discard);
                menu.addMenuItem(item1);
                }
            };

        favoriteListview.setMenuCreator(creator);


        favoriteListview.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            /**
             * Menu icon click, if click delete, will delete the current item from list
             * @param position
             * @param menu
             * @param index
             * @return return true if succeed
             */
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                String item = StaticParameterCenter.favoriteList.get(position);
                switch (index) {
                    case 0:
                        // delete current item
                        StaticParameterCenter.favoriteList.remove(position);
                        favoriteAdapter.notifyDataSetChanged();
                        break;
                }
                return false;
            }
        });


        favoriteListview.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    /**
                     * if click the expression, will bring the expression to calculator
                     * @param parent
                     * @param view
                     * @param position
                     * @param id
                     */
                    @Override
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
                        fragment.setChkFavorite(true);
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

}