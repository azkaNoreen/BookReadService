package azka.noreen.bookreadservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Details extends AppCompatActivity {
TextView is;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        is=findViewById(R.id.is);
        Intent intent=getIntent();
        is.setText(intent.getStringExtra("sn"));

    }
}