package comchaowangcanada.httpsgithub.navigationdrawerpractice;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;



import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment {

    private  CustomAdapter favoriteAdapter;

    public FavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_favorite, container, false);

        //favoriteAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, favoriteStack);
        favoriteAdapter = new CustomAdapter(getActivity(), CalculatorGlossary.favoriteList);

        SwipeMenuListView favoriteListview = (SwipeMenuListView) rootView.findViewById(R.id.favorite_listView);
        favoriteListview.setAdapter(favoriteAdapter);

        SwipeMenuCreator creator = new SwipeMenuCreator() {

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
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                String item = CalculatorGlossary.favoriteList.get(position);
                switch (index) {
                    case 0:
                        // delete
//					delete(item);
                        CalculatorGlossary.favoriteList.remove(position);
                        favoriteAdapter.notifyDataSetChanged();
                        break;
                }
                return false;
            }
        });


        favoriteListview.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
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
                        fragment.setExpressionTextSize(CalculatorGlossary.MediumTextSize);
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