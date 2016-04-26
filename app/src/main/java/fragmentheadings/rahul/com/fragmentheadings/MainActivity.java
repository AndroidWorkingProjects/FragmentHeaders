package fragmentheadings.rahul.com.fragmentheadings;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int MAX_HEADERS = 10;
    int headingCount = 0;
    TextView[] headers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        getHeaders();

        addFragment("All Categories");
    }

    public void getHeaders(){
        headers = new TextView[MAX_HEADERS];

        headers[0] = (TextView)findViewById(R.id.level1);
        headers[1] = (TextView)findViewById(R.id.level2);
        headers[2] = (TextView)findViewById(R.id.level3);
        headers[3] = (TextView)findViewById(R.id.level4);
        headers[4] = (TextView)findViewById(R.id.level5);
        headers[5] = (TextView)findViewById(R.id.level6);
        headers[6] = (TextView)findViewById(R.id.level7);
        headers[7] = (TextView)findViewById(R.id.level8);
        headers[8] = (TextView)findViewById(R.id.level9);
        headers[9] = (TextView)findViewById(R.id.level10);

        for(int i = 0; i < MAX_HEADERS; i++){
            final int index = i;
            headers[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    headerClicked(index);
                }
            });
        }
    }

    public void headerClicked(int index){
        FragmentManager fm = getSupportFragmentManager();
        for(int i = index+1; i<MAX_HEADERS; i++) {
            headers[i].setVisibility(View.GONE);
        }

        int fragmentCount = fm.getBackStackEntryCount();
        for(int i = index+1; i < fragmentCount; i++){
            fm.popBackStackImmediate();
            headingCount--;
        }
        Log.i("Heading Count:",""+headingCount);
    }

    public void addFragment(String item){
        FragmentManager fragMan = getSupportFragmentManager();
        FragmentTransaction fragTransaction = fragMan.beginTransaction();

        Fragment myFrag = new FragmentOne(headingCount+1);
        fragTransaction.setCustomAnimations(R.anim.enter,R.anim.exit);
        fragTransaction.add(R.id.categories, myFrag, "fragment" + fragMan.getBackStackEntryCount() + 1);
        fragTransaction.addToBackStack("fragment" + fragMan.getBackStackEntryCount() + 1);
        fragTransaction.commit();
        fragMan.executePendingTransactions();

        headingCount = fragMan.getBackStackEntryCount();
        Log.i("Heading Count ", ""+headingCount);
        setHeading(item);
    }

    public void setHeading(String heading){
        //int visibleCount = (headingCount<2)?1:headingCount-1;
        for(int i = headingCount; i<MAX_HEADERS; i++){
            headers[i].setVisibility(View.GONE);
        }
        headers[headingCount-1].setText(heading + "  /");
        if(headingCount>1){
            headers[headingCount-1].setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onBackPressed(){

        headingCount = getSupportFragmentManager().getBackStackEntryCount();
        if(headingCount==1){
            finish();
        }
        else{
            getSupportFragmentManager().popBackStack();
            headingCount -= 1;
            removeHeaders();
            if(headingCount==1){
                headers[0].setText("All Categories");
            }
        }
    }

    public void removeHeaders(){
        //int visibleCount = (headingCount<2)?1:headingCount-1;

        for(int i = headingCount; i<MAX_HEADERS; i++){
            headers[i].setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
