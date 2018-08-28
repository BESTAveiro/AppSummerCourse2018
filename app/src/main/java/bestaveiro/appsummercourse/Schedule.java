package bestaveiro.appsummercourse;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.chrisbanes.photoview.PhotoView;

public class Schedule extends AppCompatActivity {

    FloatingActionButton fab;
    String TAG="Schedule";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        Intent intent= getIntent();
        Bundle bd=intent.getExtras();
        User getUser= (User) bd.get("User");

        PhotoView photoView = (PhotoView) findViewById(R.id.photo_view);
        Log.d(TAG,"PARTICIPANT: "+getUser.getParticipant());
        if(getUser.getParticipant()==1)photoView.setImageResource(R.drawable.schedule);
        else photoView.setImageResource(R.drawable.schedule_org);

    }
}
