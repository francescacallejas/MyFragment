package course.examples.myfragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static course.examples.myfragment.TitleActivity.keyTerms;

/**
 * Created by Fran Callejas on 6/1/2017.
 */

public class DefinitionActivity extends Fragment {

    public static final String TAG = "Definitions";

    private TextView mDefView = null;
    private int mCurrIdx = -1;
    private int mDefArrLen;

    int getShownIndex() {
        return mCurrIdx;
    }

    // Show the Quote string at position newIndex
    void showQuoteAtIndex(int newIndex) {
        if (newIndex < 0 || newIndex >= mDefArrLen)
            return;
        mCurrIdx = newIndex;
        mDefView.setText(MainActivity.mDefinitions[mCurrIdx]);
    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        Log.i(TAG, getClass().getSimpleName() + ":entered onCreateView()");
        return inflater.inflate(R.layout.activity_definition, container, false);
    }

    public void onAttach(Activity activity){
        super.onAttach(activity);
    }

    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG, getClass().getSimpleName() + ":entered onActivityCreated()");

        mDefView = (TextView) getActivity().findViewById(R.id.VocabOptions);
        mDefArrLen = MainActivity.mDefinitions.length;
    }

    @Override
    public void onStart() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onStart()");
        super.onStart();
    }


}