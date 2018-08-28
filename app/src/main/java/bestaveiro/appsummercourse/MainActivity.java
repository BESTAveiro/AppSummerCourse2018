package bestaveiro.appsummercourse;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public String TAG="MainActivity";
    public User myUsr;
    NavigationView navigationView;

    Button button_g,button_h,button_r,button_s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hideItem();


        Intent intent=getIntent();
        myUsr = (User) intent.getSerializableExtra("User");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
       /* fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        ImageView main_logo=(ImageView) findViewById(R.id.main_logo);
        main_logo.setImageResource(R.drawable.logo);
        if(myUsr.getParticipant()==1) main_logo.setVisibility(View.VISIBLE);
        else{

            main_logo.setVisibility(View.GONE);
            button_g= (Button) findViewById(R.id.button_gryffindor);
            button_g.setVisibility(View.VISIBLE);
            button_g.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newAct = new Intent(MainActivity.this, ShowPdf.class);
                    newAct.putExtra("i", (Serializable) 0);
                    startActivity(newAct);
                }
            });

            button_s= (Button) findViewById(R.id.button_slytherin);
            button_s.setVisibility(View.VISIBLE);
            button_s.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newAct = new Intent(MainActivity.this, ShowPdf.class);
                    newAct.putExtra("i", (Serializable) 1);
                    startActivity(newAct);
                }
            });

            button_h= (Button) findViewById(R.id.button_huffelpuff);
            button_h.setVisibility(View.VISIBLE);
            button_h.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newAct = new Intent(MainActivity.this, ShowPdf.class);
                    newAct.putExtra("i", (Serializable) 2);
                    startActivity(newAct);
                }
            });

            button_r= (Button) findViewById(R.id.button_ravenclaw);
            button_r.setVisibility(View.VISIBLE);
            button_r.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newAct = new Intent(MainActivity.this, ShowPdf.class);
                    newAct.putExtra("i", (Serializable) 3);
                    startActivity(newAct);
                }
            });
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //this.invalidateOptionsMenu();
    }

    private void hideItem()
    {
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu nav_Menu = navigationView.getMenu();
        //nav_Menu.findItem(R.id.nav_schedule).setVisible(true);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.main, menu);

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
            //return true;
            //TODO: LogOut
            Intent loginIntent = new Intent(this , LoginActivity.class);
            startActivity(loginIntent);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        /*if (id == R.id.nav_camera) {
            Intent newAct = new Intent(this, OrganizersHandbook.class);
            newAct.putExtra("User", (Serializable) myUsr);
            startActivity(newAct);

        }*/
        if (id == R.id.nav_slideshow) {
            Intent newAct = new Intent(this, Contacts.class);
            newAct.putExtra("User", (Serializable) myUsr);
            startActivity(newAct);
        } else if (id == R.id.nav_manage) {
            Intent newAct = new Intent(this, MapsActivity.class);
            startActivity(newAct);

        } else if(id == R.id.nav_gallery){
            Intent newAct = new Intent(this, main_drinking_and_get2know_games.class);
            startActivity(newAct);
        } else if(id == R.id.nav_schedule){
            Intent newAct = new Intent(this, Schedule.class);
            //Intent newAct= new Intent(this, ShowPdf.class);
            newAct.putExtra("User", (Serializable) myUsr);
            startActivity(newAct);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
