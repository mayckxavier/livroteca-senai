package br.org.livropedia.livropedia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText  editText = (EditText) findViewById(R.id.editText);

        editText.setError("Mensagem de erro");


    }
}
