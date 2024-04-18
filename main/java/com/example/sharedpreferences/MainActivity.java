package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    Button btnIniciarSesion;
    CheckBox checkGuardarSesion;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    EditText etCorreo, etPassword;
    String password, correo;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarElementos();

        if(revisarSesion())
        {
            Intent intent = new Intent(this,ActivityPrincipal.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Iniciar Sesión", Toast.LENGTH_SHORT).show();
        }

        btnIniciarSesion.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                password = etPassword.getText().toString();
                correo = etCorreo.getText().toString();

                if(correo.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Escriba su Correo", Toast.LENGTH_SHORT).show();
                    etCorreo.requestFocus();
                } else if(password.equals("password"))
                {
                    guardarSesion(checkGuardarSesion.isChecked());

                    Intent intent = new Intent(getApplicationContext(), ActivityPrincipal.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "La contraseña no coincide", Toast.LENGTH_SHORT).show();
                    etPassword.requestFocus();
                }
            }
        });
    }

    private void inicializarElementos()
    {
        preferences = this.getSharedPreferences("sesiones",Context.MODE_PRIVATE);
        editor = preferences.edit();

        btnIniciarSesion = (Button) findViewById(R.id.button);
        checkGuardarSesion = (CheckBox) findViewById(R.id.checkBox);
        etCorreo = (EditText) findViewById(R.id.editTextTextEmailAddress);
        etPassword = (EditText) findViewById(R.id.editTextTextPassword);
    }

    private boolean revisarSesion()
    {
        boolean sesionIniciada = this.preferences.getBoolean("sesion", false);

        return sesionIniciada;
    }

    private void guardarSesion(boolean checked)
    {
        editor.putBoolean("sesion",checked);
        editor.putString("mail", etCorreo.getText().toString());
        editor.apply();
    }
}