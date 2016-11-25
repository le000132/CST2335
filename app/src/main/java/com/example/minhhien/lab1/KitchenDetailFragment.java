package com.example.minhhien.lab1;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.minhhien.lab1.dummy.DummyContent;

/**
 * A fragment representing a single Kitchen detail screen.
 * This fragment is either contained in a {@link KitchenListActivity}
 * in two-pane mode (on tablets) or a {@link KitchenDetailActivity}
 * on handsets.
 */
public class KitchenDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    String mItem;
    Button start;
    Button end;
    Button reset;
    EditText timer;
    TextView microTitle;
    TextView fridgeTitle;
    TextView lightsTitle;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public KitchenDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Test this out to see if it works.

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = getArguments().getString(ARG_ITEM_ID);


            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);

                if (appBarLayout !=null) {
                    if (mItem.equals("Microwave")) {
                        appBarLayout.setTitle("Microwave Controls");

                    } else if (mItem.equals("Fridge")) {
                        appBarLayout.setTitle("Fridge Controls");
                    } else if (mItem.equals("Lights")) {
                        appBarLayout.setTitle("Lights Controls");
                    }
                    //else if(!(mItem == "Microwave" || mItem == "Fridge" || mItem == "Lights")) {
                    //  appBarLayout.setTitle("Other items");
                    //}
                }

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.kitchen_detail, container, false);


        if(mItem == "Microwave") {
            View microwaveView = inflater.inflate(R.layout.microwavelayout, container, false);
            microTitle = (TextView) microwaveView.findViewById(R.id.microwaveTitle);
            timer = (EditText) microwaveView.findViewById(R.id.timer);
            start = (Button) microwaveView.findViewById(R.id.startTime);
            end = (Button) microwaveView.findViewById(R.id.endTime);
            reset = (Button) microwaveView.findViewById(R.id.resetTime);

            return microwaveView;

        }

        else if(mItem == "Fridge") {
            View fridgeView = inflater.inflate(R.layout.fridgelayout, container, false);

            return fridgeView;

        }

        else if(mItem == "Lights") {
            View lightsView = inflater.inflate(R.layout.lightslayout, container, false);

            return lightsView;
        }

        else {
            return null;
        }
        // Show the dummy content as text in a TextView.
       // if (mItem != null) {
         //   ((TextView) rootView.findViewById(R.id.kitchen_detail)).setText(mItem);
        //}
       // return rootView;
    }
}
