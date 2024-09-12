package com.example.myapplication;

import android.view.View;
import android.widget.Button;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class Historial extends AppCompatActivity {

    private ListView listaHistorial;
    private Button btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.historial);

        listaHistorial = findViewById(R.id.ListaHistorial);
        btnVolver = findViewById(R.id.btnVolver); // Inicializar el botón

        // Obtener los resultados del Intent
        Intent intent = getIntent();
        ArrayList<String> resultados = intent.getStringArrayListExtra("resultados");

        // Configurar el adaptador para el ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, resultados);
        listaHistorial.setAdapter(adapter);

        // Configurar el botón Volver
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crear un Intent para iniciar MainActivity
                Intent intent = new Intent(Historial.this, MainActivity.class);

                // Limpiar la pila de actividades, si deseas que MainActivity sea la única en la pila
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

                // Iniciar MainActivity
                startActivity(intent);

                // Cerrar la actividad actual (HistorialActivity)
                finish();
            }
        });
    }
}

