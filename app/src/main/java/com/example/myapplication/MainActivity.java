package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {
    private Button btnHistorial, btnSumar, btnRestar, btnMultiplicar, btnDividir, btn9, btn8, btn7, btn6, btn5, btn4, btn3, btn2, btn1, btn0, btnLimpiar, btnBorrar;
    private TextView txtResultado, txtNumero;

    private double numero1 = 0, numero2 = 0;
    private String operador = "";
    private ArrayList<Double> resultados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicialización de componentes
        txtNumero = findViewById(R.id.txtNumero);
        txtResultado = findViewById(R.id.txtResultado);
        btnHistorial = findViewById(R.id.btnHistorial);
        btnSumar = findViewById(R.id.btnSumar);
        btnRestar = findViewById(R.id.btnRestar);
        btnMultiplicar = findViewById(R.id.btnMultiplicar);
        btnDividir = findViewById(R.id.btnDividir);
        btnLimpiar = findViewById(R.id.btnLimpiar);
        btnBorrar = findViewById(R.id.btnBorrar);
        btn9 = findViewById(R.id.btn9);
        btn8 = findViewById(R.id.btn8);
        btn7 = findViewById(R.id.btn7);
        btn6 = findViewById(R.id.btn6);
        btn5 = findViewById(R.id.btn5);
        btn4 = findViewById(R.id.btn4);
        btn3 = findViewById(R.id.btn3);
        btn2 = findViewById(R.id.btn2);
        btn1 = findViewById(R.id.btn1);
        btn0 = findViewById(R.id.btn0);

        // Escucha para los botones numéricos
        setNumberButtonListeners();

        // Botones de operaciones aritméticas
        btnSumar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOperador("+");
            }
        });

        btnRestar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOperador("-");
            }
        });

        btnMultiplicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOperador("*");
            }
        });

        btnDividir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOperador("/");
            }
        });

        // Botón para limpiar la entrada
        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtNumero.setText("");
                txtResultado.setText("");
                numero1 = 0;
                numero2 = 0;
                operador = "";
            }
        });

        // Botón para borrar el último número ingresado
        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = txtNumero.getText().toString();
                if (!text.isEmpty()) {
                    txtNumero.setText(text.substring(0, text.length() - 1));
                }
            }
        });

        // Botón para mostrar el historial
        btnHistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Historial.class);
                ArrayList<String> resultadosStrings = new ArrayList<>();
                for (Double resultado : resultados) {
                    resultadosStrings.add(resultado.toString());
                }
                intent.putStringArrayListExtra("resultados", resultadosStrings);
                startActivity(intent);
            }
        });
    }

    // Método para asignar la operación
    private void setOperador(String op) {
        if (!txtNumero.getText().toString().isEmpty()) {
            numero1 = Double.parseDouble(txtNumero.getText().toString());
            operador = op;
            txtNumero.setText("");
        }
    }

    // Método para realizar la operación cuando se presiona un número después de la operación
    private void realizarOperacion() {
        if (!txtNumero.getText().toString().isEmpty() && !operador.isEmpty()) {
            numero2 = Double.parseDouble(txtNumero.getText().toString());

            double resultado = 0;
            switch (operador) {
                case "+":
                    resultado = numero1 + numero2;
                    break;
                case "-":
                    resultado = numero1 - numero2;
                    break;
                case "*":
                    resultado = numero1 * numero2;
                    break;
                case "/":
                    if (numero2 != 0) {
                        resultado = numero1 / numero2;
                    } else {
                        txtResultado.setText("Error: División por 0");
                        return;
                    }
                    break;
            }
            resultados.add(resultado); // Guardar el resultado en la lista
            txtResultado.setText(String.valueOf(resultado));
            txtNumero.setText("");  // Limpiar el campo de entrada
            operador = "";         // Reiniciar el operador
        }
    }

    // Método para manejar los botones numéricos
    private void setNumberButtonListeners() {
        View.OnClickListener numberListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                txtNumero.append(button.getText().toString());

                // Realizar la operación si ya se seleccionó un operador y un segundo número
                if (!operador.isEmpty()) {
                    realizarOperacion();
                }
            }
        };

        btn0.setOnClickListener(numberListener);
        btn1.setOnClickListener(numberListener);
        btn2.setOnClickListener(numberListener);
        btn3.setOnClickListener(numberListener);
        btn4.setOnClickListener(numberListener);
        btn5.setOnClickListener(numberListener);
        btn6.setOnClickListener(numberListener);
        btn7.setOnClickListener(numberListener);
        btn8.setOnClickListener(numberListener);
        btn9.setOnClickListener(numberListener);
    }

}