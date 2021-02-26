package com.example.calculator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText txtNumber1, txtNumber2;
    TextView txtSalida;
    Button btnSuma, btnResta, btnMultiplicar, btnDividir;

    private static final String SUMA = "SUMA";
    private static final String RESTA = "RESTA";
    private static final String MULTIPLICACION = "MULTIPLICACION";
    private static final String DIVISION = "DIVISION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNumber1 = (EditText) findViewById(R.id.txtNumber1);
        txtNumber2 = (EditText) findViewById(R.id.txtNumber2);
        txtSalida = (TextView) findViewById(R.id.txtSalida);

        btnSuma = (Button) findViewById(R.id.btnSuma);
        btnResta = (Button) findViewById(R.id.btnResta);
        btnMultiplicar = (Button) findViewById(R.id.btnMultiplicar);
        btnDividir = (Button) findViewById(R.id.btnDividir);
    }

    /*
    Metodos suma, resta, multiplicacion y division
    son los invocados por los Button y estos
    llaman al metodo resultado quien es el que
    contiene la logica
     */
    public void suma(View v){
        resultado(SUMA);
    }

    public void resta(View v){
        resultado(RESTA);
    }

    public void multiplicar(View v){
        resultado(MULTIPLICACION);
    }

    public void dividir(View v){
        resultado(DIVISION);
    }

    /*
    Este metodo valida que los TextView no esten vacios y luego
    procede a hacer cada una de las operaciones
     */
    private void resultado(String operacion){
        String n1 = txtNumber1.getText().toString();
        String n2 = txtNumber2.getText().toString();

        if(!n1.isEmpty() && !n2.isEmpty()){
            int a = Integer.parseInt(txtNumber1.getText().toString());
            int b = Integer.parseInt(txtNumber2.getText().toString());
            int resultado = 0;

            switch (operacion){
                case SUMA:
                    resultado = a+b;
                    imprimirResultado(resultado);
                    break;
                case RESTA:
                    resultado = a-b;
                    imprimirResultado(resultado);
                    break;
                case MULTIPLICACION:
                    resultado = a*b;
                    imprimirResultado(resultado);
                    break;
                case DIVISION:
                    if(b == 0) {
                        mostrarDialogo("El segundo valor de la división no puede ser cero");
                    }else {
                        resultado = a/b;
                        imprimirResultado(resultado);
                    }
                    break;
            }
        } else {
            mostrarDialogo("Los valores no pueden ser vacios");
        }
    }

    /*
    Imprime resultado de cada operacion en un TextView
     */
    private void imprimirResultado(Object result){
        String out = txtSalida.getText().toString().split(":")[0];
        String res = String.valueOf(result);

        txtSalida.setText(String.format("%s: %s", out, res));
        txtNumber1.setText("");
        txtNumber2.setText("");
    }

    /*
    Dialogo que se muestra cuando los TextView en los que
    se ingresan los numeros estan vacios o cuando se
    divide por cero
     */
    private void mostrarDialogo(String result){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Cuidado !");
        alert.setMessage(result);

        alert.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                });

        AlertDialog alertDialog = alert.create();
        alertDialog.show();

        txtSalida.setText("El resultado es: ");
        txtNumber1.setText("");
        txtNumber2.setText("");
    }
}