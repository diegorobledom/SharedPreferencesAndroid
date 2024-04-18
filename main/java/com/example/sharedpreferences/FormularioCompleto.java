package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class FormularioCompleto extends AppCompatActivity
{
    RadioButton rbMasculino, rbFemenino;
    Button btGuardar;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    EditText etNombre, etApellido, etCorreo;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_completo);

        inicializarElementos();

        btGuardar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(rbMasculino.isChecked())
                {
                    Toast.makeText(getApplicationContext(), "Masculino", Toast.LENGTH_SHORT).show();
                } else if (rbFemenino.isChecked())
                {
                    Toast.makeText(getApplicationContext(), "Femenino", Toast.LENGTH_SHORT).show();
                } else
                {
                    Toast.makeText(getApplicationContext(), "Ningún elemento Seleccionado", Toast.LENGTH_SHORT).show();
                }

                guardarSesion();
            }
        });
    }

    private void inicializarElementos()
    {
        preferences = this.getSharedPreferences("sesiones", Context.MODE_PRIVATE);
        editor = preferences.edit();

        rbMasculino = (RadioButton) findViewById(R.id.rbMasculino);
        rbFemenino = (RadioButton) findViewById(R.id.rbFemenino);
        btGuardar = (Button) findViewById(R.id.btGuardar);
        etNombre = (EditText) findViewById(R.id.editTextText);
        etApellido = (EditText) findViewById(R.id.editTextText2);
        etCorreo = (EditText) findViewById(R.id.editTextTextEmailAddress2);
    }

    private void guardarSesion()
    {
        editor.putString("nombre",etNombre.getText().toString());
        editor.putString("apellido",etApellido.getText().toString());
        editor.putString("mail", etCorreo.getText().toString());
        editor.putBoolean("genero",rbFemenino.isChecked());
        editor.apply();
    }
}