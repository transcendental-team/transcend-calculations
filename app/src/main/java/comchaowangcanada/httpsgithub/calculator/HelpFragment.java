package comchaowangcanada.httpsgithub.calculator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * Created by Transcendental Team on 25/03/2016.
 * Author: Chao Wang
 * This class create the fragment frame for help information list
 */

public class HelpFragment extends Fragment {

    public HelpFragment() {
        // Required empty public constructor
    }

    /**
     * create the listview window
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return return true if succeed
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_help, container, false);
        // create a adapter to save the string array of information
        ArrayAdapter<String> helpAdapter = new ArrayAdapter(getActivity(),
                android.R.layout.simple_list_item_1,  StaticParameterCenter.helpInformation);
        ListView helpListView = (ListView) rootView.findViewById(R.id.help_ListView);

        helpListView.setAdapter(helpAdapter);
        // Inflate the layout for this fragment
        return rootView;
    }



}
