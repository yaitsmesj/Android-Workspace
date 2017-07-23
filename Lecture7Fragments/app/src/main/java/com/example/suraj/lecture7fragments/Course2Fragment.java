package com.example.suraj.lecture7fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Course2Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Course2Fragment extends Fragment {

    public static final String TAG = "Fragment 2";
    public static final String ARG_NAME = "name";
    public static final String ARG_TEACHER = "teacher";
    public static final String ARG_LANG = "language";
    public static final String ARG_ID = "id";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String name = "Sample Course";
    private String teacher="Sample Teacher";
    private String language="Sample Language";
    private int cId = 0;

    TextView tvName,tvTeacher,tvLanguage;

    public Course2Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment Course2Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Course2Fragment newInstance(Course course,int id) {
        Course2Fragment fragment = new Course2Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_NAME,course.getName());
        args.putString(ARG_TEACHER, course.getTeacher());
        args.putString(ARG_LANG, course.getLanguage());
        args.putInt(ARG_ID,id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Log.d(TAG, "onCreate: ");
            name = getArguments().getString(ARG_NAME);
            teacher = getArguments().getString(ARG_TEACHER);
            language = getArguments().getString(ARG_LANG);
            cId = getArguments().getInt(ARG_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_course, container, false);
        tvName = (TextView)rootView.findViewById(R.id.tvCourseName);
        tvTeacher = (TextView)rootView.findViewById(R.id.tvCourseTeacher);
        tvLanguage = (TextView) rootView.findViewById(R.id.tvCourseLang);
        tvName.setText(name);
        tvTeacher.setText(teacher);
        tvLanguage.setText(language);
        return rootView;
    }
}
