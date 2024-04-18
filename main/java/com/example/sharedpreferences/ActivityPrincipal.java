package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ActivityPrincipal extends AppCompatActivity
{
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    TextView tvCorreo;
    Button btCerrarSesion, btFormulario;
    String correo;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        inicializarElementos();

        correo = preferences.getString("mail", "Correo Invalido");

        tvCorreo.setText(correo);

        btCerrarSesion.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editor.putBoolean("sesion",false);
                editor.apply();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        btFormulario.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), FormularioCompleto.class);
                startActivity(intent);
            }
        });
    }

    private void inicializarElementos()
    {
        tvCorreo = (TextView) findViewById(R.id.textView3);
        btCerrarSesion = (Button) findViewById(R.id.btnCerrarSesion);
        btFormulario = (Button) findViewById(R.id.btFormulario);

        preferences = getSharedPreferences("sesiones",Context.MODE_PRIVATE);
        editor = preferences.edit();
    }
}