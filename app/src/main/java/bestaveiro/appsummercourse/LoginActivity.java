package bestaveiro.appsummercourse;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    private String TAG="LoginActivity";
    final private HashMap<String,User> list_users=new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Resources res=getResources();
        Drawable drawable= res.getDrawable(R.drawable.logo);
        ImageView logo= (ImageView) findViewById(R.id.logo_login);
        logo.setImageDrawable(drawable);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference().child("User");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    Log.d(TAG,postSnapshot.child("name").getValue(String.class));
                    list_users.put(postSnapshot.child("name").getValue(String.class),new User(postSnapshot.child("name").getValue(String.class),postSnapshot.child("password").getValue(String.class),postSnapshot.child("participant").getValue(int.class)));
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        final EditText usrname= (EditText) findViewById(R.id.edit_text_login);
        usrname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
        final EditText pswd= (EditText) findViewById(R.id.edit_text_password);
        pswd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
        final Button login_button=findViewById(R.id.button_login);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"User :"+usrname.getText().toString());
                Log.d(TAG,"User :"+pswd.getText().toString());
                Log.d(TAG,"Psw length: "+pswd.getText().length());
                if(usrname.length()==0){
                    Log.d(TAG,"User must fill Username Edit Text.");
                    Toast.makeText(getBaseContext(),"Username empty.",Toast.LENGTH_LONG).show();
                }
                else if(pswd.getText().length()==0){
                    Log.d(TAG,"User must fill Password Edit Text.");
                    Toast.makeText(getBaseContext(),"Insert Password.",Toast.LENGTH_LONG).show();
                }
                String u=usrname.getText().toString();
                String p=pswd.getText().toString();

                if(!list_users.containsKey(u)){
                    Log.d(TAG,"User must fill Password Edit Text.");
                    Toast.makeText(getBaseContext(),"Wrong password.",Toast.LENGTH_LONG).show();
                }
                else if(!list_users.get(u).getPassword().equals(p)){
                    Log.d(TAG,"Incorrect Password.");
                    Toast.makeText(getBaseContext(),"Incorrect Password.",Toast.LENGTH_LONG).show();
                }
                else{
                    Log.d(TAG,"Login Successful.");
                    Toast.makeText(getBaseContext(),"Login Successful.",Toast.LENGTH_LONG).show();
                    User myUsr=list_users.get(u);

                    Intent menuIntent = new Intent(LoginActivity.this , MainActivity.class);
                    menuIntent.putExtra("User", (Serializable) myUsr);
                    startActivity(menuIntent);
                }
            }
        });
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getBaseContext(),"There is no way back",Toast.LENGTH_LONG).show();
        /*if (!shouldAllowBack()) {
            doSomething();
        } else {
            super.onBackPressed();
        }*/
    }
}
