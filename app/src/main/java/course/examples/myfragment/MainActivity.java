package course.examples.myfragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import course.examples.myfragment.TitleActivity.ListSelectionListener;


public class MainActivity extends Activity implements ListSelectionListener {

    public static String[] mKeyTerms;
    public static String[] mDefinitions;

    private final DefinitionActivity defActivity= new DefinitionActivity();
    private FragmentManager mFragmentManager;
    private FrameLayout mTermLayout, mDefinitionLayout;

    private static final int MATCH_PARENT = LinearLayout.LayoutParams.MATCH_PARENT;
    private static final String TAG = "MainActivity";

    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mKeyTerms = getResources().getStringArray(R.array.KeyTerms);
        mDefinitions = getResources().getStringArray(R.array.Definitions);

        setContentView(R.layout.activity_main);

        mTermLayout = (FrameLayout) findViewById(R.id.keyTerms);
        mDefinitionLayout = (FrameLayout) findViewById(R.id.definitions);

        // Get a reference to the FragmentManager
        mFragmentManager = getFragmentManager();

        // Start a new FragmentTransaction
        FragmentTransaction fragmentTransaction = mFragmentManager
                .beginTransaction();

        // Add the TitleFragment to the layout
        fragmentTransaction.add(R.id.keyTerms,
                new TitleActivity());

        // Commit the FragmentTransaction
        fragmentTransaction.commit();

        // Add a OnBackStackChangedListener to reset the layout when the back stack changes
        mFragmentManager
                .addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
                    public void onBackStackChanged() {
                        setLayout();
                    }
                });


        /*spinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.VocabOptions, R.layout.dropdown_menu);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Toast.makeText(
                        parent.getContext(),
                        parent.getItemAtPosition(pos).toString(),
                        Toast.LENGTH_LONG).show();
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        }); */
    }

    public void setLayout(){

        // Determine whether the QuoteFragment has been added
        if (!defActivity.isAdded()) {

            // Make the TitleFragment occupy the entire layout
            mTermLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    MATCH_PARENT, MATCH_PARENT));
            mDefinitionLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                    MATCH_PARENT));
        } else {

            // Make the TitleLayout take 1/3 of the layout's width
            mTermLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                    MATCH_PARENT, 1f));

            // Make the QuoteLayout take 2/3's of the layout's width
            mDefinitionLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                    MATCH_PARENT, 2f));
        }

    }

    public void onListSelection(int index){
        //add fragment
        if(!defActivity.isAdded()){

            //need new Fragment Transaction
            FragmentTransaction ft = mFragmentManager.beginTransaction();

            //add Definitions fragment to layout
            ft.add(R.id.definitions, new DefinitionActivity());

            //add to backstack
            ft.addToBackStack(null);

            //commit the transaction
            ft.commit();

            //force android to execute transaction
            mFragmentManager.executePendingTransactions();
        }

        if (defActivity.getShownIndex() != index) {

            // Tell the QuoteFragment to show the quote string at position index
            defActivity.showQuoteAtIndex(index);

        }
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onDestroy()");
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onPause()");
        super.onPause();
    }

    @Override
    protected void onRestart() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onRestart()");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onResume()");
        super.onResume();
    }

    @Override
    protected void onStart() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onStart()");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onStop()");
        super.onStop();
    }
}

