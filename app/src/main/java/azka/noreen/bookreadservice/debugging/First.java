package azka.noreen.bookreadservice.debugging;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import azka.noreen.bookreadservice.Book;
import azka.noreen.bookreadservice.MyService;
import azka.noreen.bookreadservice.R;

public class First extends AppCompatActivity {
 Book book;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        book=new Book(12,"Aracbic","Sir","This is dee");
        Intent intent = new Intent(getApplication(), Second.class);
        intent.putExtra("book", book);
        startActivity(intent);

    }
}