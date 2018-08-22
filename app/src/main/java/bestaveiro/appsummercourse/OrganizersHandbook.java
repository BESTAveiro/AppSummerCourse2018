package bestaveiro.appsummercourse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;


public class OrganizersHandbook extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organizers_handbook);
//        vala
    }


    View myView;
    String className;



    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Log.d(getClass().getSimpleName(),"onCreate");
        className = getClass().getSimpleName();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        StaticMethods.removeTabLayout(getActivity());

        getActivity().setTitle("Organizers' Handbook");

        myView = inflater.inflate(R.layout.organizershandbook, container, false);

        Log.d(className,"onCreateView");


        String septemberDays[] = {"1 Friday", "2 Saturday", "3 Sunday" , "4 Monday" ,
                "5 Tuesday", "6 Wednesday", "7 Thursday ", "8 Friday", "9 Saturday"};



        GridView septemberGV = (GridView) myView.findViewById(R.id.gridViewCalendarSeptember);
        septemberGV.setAdapter(new OrganizersHandbookCalendarGridViewAdapter(getActivity(), septemberDays));

        septemberGV.setLongClickable(true);
        septemberGV.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View view,
                                           int position , long id) {
                // TODO Auto-generated method stub
                Toast.makeText(getActivity(), "Long Click", Toast.LENGTH_SHORT).show();
                            /*
                             *You Can use parameters like position,view or id to
                             *Customize your action
                             */
                return false;
            }
        });


        return myView;
    }



}
