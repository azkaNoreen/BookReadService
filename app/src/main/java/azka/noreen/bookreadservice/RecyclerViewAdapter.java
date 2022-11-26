package azka.noreen.bookreadservice;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<Book> bookArrayList =new ArrayList<Book>();
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
                Intent intent = new Intent(studentViewHolder.itemView.getContext(), MyService.class);
                intent.setAction("updateNotification");
                intent.putExtra("book", st);
                MyService.launchSecondService(studentViewHolder.itemView.getContext(), intent);
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
