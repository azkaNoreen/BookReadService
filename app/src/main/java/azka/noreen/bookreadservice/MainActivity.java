package azka.noreen.bookreadservice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recycleView;
    ArrayList<Student> studentArrayList;
    Button start,stop;
    String name;
    public static String ACTION_Launch_Activity = "Launch_Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recycleView=findViewById(R.id.rv);
        start=findViewById(R.id.start);
        stop=findViewById(R.id.stop);

        studentArrayList= initStudentPrameterList();
        InitRecycleView();

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, MyService.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    MyService.launchSecondService(MainActivity.this);
//                    Intent intent2 = new Intent(MainActivity.this, MyService.class);
//                    intent2.setAction(ACTION_Launch_Activity);
//                   startService(intent2);
//                    startForegroundService(intent);
                }else{
                    startService(intent);
                }
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, MyService.class);
                stopService(intent);
            }
        });


    }
    private ArrayList<Student> initStudentPrameterList(){
        ArrayList<Student> studentArrayList2=new ArrayList<>();
        Student s1=new Student("Azka","0333-8976547");
        Student s2=new Student("Azka","0372-8976447");
        Student s3=new Student("Azka","0313-8966547");
        Student s4=new Student("Azka","0336-8646547");

        studentArrayList2.add(s1);
        studentArrayList2.add(s2);
        studentArrayList2.add(s3);
        studentArrayList2.add(s4);

        return studentArrayList2;
    }

    public void InitRecycleView(){
        RecyclerViewAdapter rva=new RecyclerViewAdapter();
        rva.setMyInterface(new MyInterface() {
            @Override
            public void onStudentClick( Student student) {
                name=student.getName();
                Toast.makeText(MainActivity.this, student.getPhone(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplication(), MyService.class);
                intent.setAction("getName");
                intent.putExtra("sname", student.getPhone());
                getApplication().startService(intent);
            }
        });
        recycleView.setAdapter(rva);
        recycleView.setLayoutManager(new LinearLayoutManager(this));

        //recycleView.setLayoutManager(new GridLayoutManager(this,3));

        rva.setData(studentArrayList);

    }
}