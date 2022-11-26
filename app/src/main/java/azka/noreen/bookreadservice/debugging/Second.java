package azka.noreen.bookreadservice.debugging;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import azka.noreen.bookreadservice.Book;
import azka.noreen.bookreadservice.R;

public class Second extends AppCompatActivity {
TextView bookname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        bookname=findViewById(R.id.book);

        Book book = (Book) getIntent().getSerializableExtra("bookx");
        bookname.setText("Book Name: "+book.getName()+"Book Id: "+book.getBookId()+"Book Description: "+book.getDescription());
    }
}