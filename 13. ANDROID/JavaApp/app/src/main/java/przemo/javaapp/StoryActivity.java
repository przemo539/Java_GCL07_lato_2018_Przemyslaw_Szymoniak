package przemo.javaapp;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class StoryActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {
    private int UserId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UserId = Integer.parseInt(getIntent().getStringExtra("UserId"));
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_start_activity) {
            //Intent intent = new Intent(StoryActivity.this, MainActivity.class);
           // intent.putExtra("UserId", UserId+"");
            //startActivity(intent);
        } else if (id == R.id.nav_story_of_activities) {

            //actual activity

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
