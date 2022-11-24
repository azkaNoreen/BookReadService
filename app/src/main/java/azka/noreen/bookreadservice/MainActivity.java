package azka.noreen.bookreadservice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recycleView;
    ArrayList<Student> studentArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recycleView=findViewById(R.id.rv);
        studentArrayList= initStudentPrameterList();
        InitRecycleView();


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
//        rva.setMyInterface(new MyInterface() {
//            @Override
//            public void onStudentClick( Student student) {
//                Toast.makeText(StudentView.this, student.getPhone(), Toast.LENGTH_SHORT).show();
//            }
//        });
        recycleView.setAdapter(rva);
        recycleView.setLayoutManager(new LinearLayoutManager(this));

        //recycleView.setLayoutManager(new GridLayoutManager(this,3));

        rva.setData(studentArrayList);

    }
}