package com.teamtreehouse.funfacts;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class FunFactsActivity extends Activity {

    private static final String KEY_FACT_STRING ="KEY_FACT_STRING";
    private static final String SHARED_PREF_FILE = "com.teamtreehouse.funfacts.preferences";
    private static final String KEY_FACT_INT ="KEY_INT" ;
    private TextView mFactLabel;
    private Button mShowFactButton;
    private RelativeLayout mRelativeLayout;
    private FactBook mFactBook = new FactBook();
    private ColorWheel mColorWheel = new ColorWheel();
    private String mFact;
    private int mColor;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fun_facts);

        // Declare our View variables and assign them the Views from the layout file
        mFactLabel = (TextView) findViewById(R.id.factTextView);
        mShowFactButton = (Button) findViewById(R.id.showFactButton);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        mSharedPreferences=getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE);
        mEditor=mSharedPreferences.edit();
        mFact=mSharedPreferences.getString(KEY_FACT_STRING,"");
        mColor=mSharedPreferences.getInt(KEY_FACT_INT,0);
        updateUi();

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFact= mFactBook.getFact();
                mColor = mColorWheel.getColor();
                updateUi();
                mEditor.putInt(KEY_FACT_INT,mColor);
                mEditor.putString(KEY_FACT_STRING,mFact);
                mEditor.apply();
            }
        };
        mShowFactButton.setOnClickListener(listener);
    }

    private void updateUi() {

        mFactLabel.setText(mFact);
        mRelativeLayout.setBackgroundColor(mColor);
        mShowFactButton.setTextColor(mColor);
    }


}
