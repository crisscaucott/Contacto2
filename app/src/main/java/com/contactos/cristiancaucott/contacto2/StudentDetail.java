package com.contactos.cristiancaucott.contacto2;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class StudentDetail extends ActionBarActivity implements android.view.View.OnClickListener{

    Button btnSave, btnDelete, btnClose;
    EditText editTextName, editTextEmail, editTextAge;
    private int contacto_id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnClose = (Button) findViewById(R.id.btnClose);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextAge = (EditText) findViewById(R.id.editTextAge);

        btnSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnClose.setOnClickListener(this);


        contacto_id = 0;
        Intent intent = getIntent();
        contacto_id = intent.getIntExtra("contacto_id", 0);
        ContactRepo repo = new ContactRepo(this);
        Contacto c = new Contacto();
        c = repo.getContactById(contacto_id);

        editTextAge.setText(String.valueOf(c.age));
        editTextName.setText(c.name);
        editTextEmail.setText(c.email);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.student_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view) {
        if (view == findViewById(R.id.btnSave)){
            ContactRepo repo = new ContactRepo(this);
            Contacto c = new Contacto();
            c.age = Integer.parseInt(editTextAge.getText().toString());
            c.email = editTextEmail.getText().toString();
            c.name = editTextName.getText().toString();
            c.contacto_id = contacto_id;

            if (contacto_id == 0){
                contacto_id = repo.insert(c);

                Toast.makeText(this,"Nuevo contacto insertado",Toast.LENGTH_SHORT).show();
            }else{

                repo.update(c);
                Toast.makeText(this,"Contacto actualizado",Toast.LENGTH_SHORT).show();
            }
        }else if (view== findViewById(R.id.btnDelete)){
            ContactRepo repo = new ContactRepo(this);
            repo.delete(contacto_id);
            Toast.makeText(this, "Contacto borrado", Toast.LENGTH_SHORT).show();
            finish();
        }else if (view== findViewById(R.id.btnClose)){
            finish();
        }


    }

}