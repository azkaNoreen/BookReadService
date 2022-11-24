package azka.noreen.bookreadservice;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<Student> studentArrayList=new ArrayList<Student>();
//    MyInterface myInterface;
//
//    public void setMyInterface(MyInterface myInterface){
//        this.myInterface=myInterface;
//    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.student_item,parent,false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Student st=studentArrayList.get(position);
        StudentViewHolder studentViewHolder= (StudentViewHolder) holder;

        studentViewHolder.studentTextName.setText(st.getName());
        studentViewHolder.studentPhone.setText(st.getPhone());
        studentViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(view.getContext(), st.getName(), Toast.LENGTH_SHORT).show();
//                myInterface.onStudentClick(st);
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentArrayList.size();
    }
    public void setData(ArrayList<Student> StudentArrayList){
        this.studentArrayList=StudentArrayList;
        notifyDataSetChanged();
    }
//to find views of single list xml file
    public static class StudentViewHolder extends RecyclerView.ViewHolder{

        TextView studentTextName;
        TextView studentPhone;
        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);

            studentTextName=itemView.findViewById(R.id.studentName);
            studentPhone=itemView.findViewById(R.id.phone);

        }
    }

}
