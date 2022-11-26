package azka.noreen.bookreadservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Details extends AppCompatActivity {
TextView bookName,bookAuthor,bookDescription,bookID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        bookName=findViewById(R.id.bn);
        bookAuthor=findViewById(R.id.ba);
        bookDescription=findViewById(R.id.bd);
        bookID=findViewById(R.id.bid);

        Book book = (Book) getIntent().getSerializableExtra("bookDetails");
        bookName.setText("Book ID: "+book.getBookId());
        bookID.setText("Book Name: "+book.getName());
        bookAuthor.setText("Book Author: "+book.getAuthor());
        bookDescription.setText("Book Description: "+book.getDescription());


    }
}