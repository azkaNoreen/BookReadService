package azka.noreen.bookreadservice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recycleView;
    ArrayList<Book> bookArrayList;
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

        bookArrayList = initStudentPrameterList();
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
    private ArrayList<Book> initStudentPrameterList(){
        ArrayList<Book> bookArrayList2 =new ArrayList<>();
        Book s1=new Book(1,"English","Azka","For learning english");
        Book s2=new Book(2,"Urdu","Azka","For learning urdu");
        Book s3=new Book(3,"Math","Azka","For learning mathematics");
        Book s4=new Book(4,"Science","Azka","For learning sceince");


        bookArrayList2.add(s1);
        bookArrayList2.add(s2);
        bookArrayList2.add(s3);
        bookArrayList2.add(s4);

        return bookArrayList2;
    }

    public void InitRecycleView(){
        RecyclerViewAdapter rva=new RecyclerViewAdapter();
        rva.setMyInterface(new MyInterface() {
            @Override
            public void onStudentClick( Book book) {
                Intent intent = new Intent(getApplication(), MyService.class);
                intent.setAction("updateNotification");
                intent.putExtra("book", book);
                getApplication().startService(intent);
            }
        });
        recycleView.setAdapter(rva);
        recycleView.setLayoutManager(new LinearLayoutManager(this));

        //recycleView.setLayoutManager(new GridLayoutManager(this,3));

        rva.setData(bookArrayList);

    }
}