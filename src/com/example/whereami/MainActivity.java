package com.example.whereami;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

import com.google.android.glass.app.Card;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		Card card = new Card(this.getApplicationContext());
        card.setText("Getting your location...");
        View cardView = card.toView();
        setContentView(cardView);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
