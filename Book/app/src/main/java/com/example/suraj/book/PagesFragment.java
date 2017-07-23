package com.example.suraj.book;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PagesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PagesFragment extends Fragment {

    interface starClickListener{
        void addStar();
    }

    private starClickListener listener;

    private void setStarClickListener(starClickListener listener){
        this.listener = listener;
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "data";
    private static final String ARG_PARAM2 = "cid";
    // TODO: Rename and change types of parameters
    private String data;
    private int cid;


    public PagesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment PagesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PagesFragment newInstance(String data,int cid, starClickListener listener) {
        PagesFragment fragment = new PagesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, data);
        args.putInt(ARG_PARAM2,cid);
        fragment.setArguments(args);
        fragment.setStarClickListener(listener);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            data = getArguments().getString(ARG_PARAM1);
            cid = getArguments().getInt(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_pages, container, false);

        TextView textView = (TextView) rootView.findViewById(R.id.tvText);
        textView.setText(data);
        final Button btnStar = (Button)rootView.findViewById(R.id.btnStar);
        btnStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnStar.getText().toString().equals("Add Star"))
                btnStar.setText("Starred");
                else
                    btnStar.setText("Add Star");
                listener.addStar();
            }
        });
        return rootView;
    }

}
