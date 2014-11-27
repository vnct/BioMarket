/**
 * 
 */
package com.itii.biomarket;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * @author user
 *
 */
public class Credits extends Activity {
	TextView textView1 = null;
    TextView textView2 = null;
    TextView textView3 = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);
        textView1 = (TextView) findViewById(R.id.eastereggtextView);
        textView2 = (TextView) findViewById(R.id.eastereggtextView2);
        textView3 = (TextView) findViewById(R.id.eastereggtextView3);
        textView1.setText("Developped by BIO MARKET TEAM");
        textView2.setText("ITII PACA - Promo 2012/2015");
        textView3.setText("https://github.com/vnct/BioMarket");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.easter_egg, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }
    public void onDestroy() {
        this.finish();
        super.onDestroy();
    }
}
