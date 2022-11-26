package azka.noreen.bookreadservice;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<Book> bookArrayList =new ArrayList<Book>();
    MyInterface myInterface;

    public void setMyInterface(MyInterface myInterface){
        this.myInterface=myInterface;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.student_item,parent,false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Book st= bookArrayList.get(position);
        StudentViewHolder studentViewHolder= (StudentViewHolder) holder;

        studentViewHolder.bookName.setText(st.getName());
        studentViewHolder.bookAuthor.setText(st.getAuthor());
        studentViewHolder.BookDescription.setText(st.getDescription());

        studentViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(view.getContext(), st.getName(), Toast.LENGTH_SHORT).show();
                myInterface.onStudentClick(st);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookArrayList.size();
    }
    public void setData(ArrayList<Book> bookArrayList){
        this.bookArrayList = bookArrayList;
        notifyDataSetChanged();
    }
//to find views of single list xml file
    public static class StudentViewHolder extends RecyclerView.ViewHolder{

        TextView bookName,bookAuthor,BookDescription;
        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);

            bookName=itemView.findViewById(R.id.name);
            bookAuthor=itemView.findViewById(R.id.message);
            BookDescription=itemView.findViewById(R.id.time);
        }
    }

}
