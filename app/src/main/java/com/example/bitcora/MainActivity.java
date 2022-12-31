package com.example.bitcora;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et=(EditText) findViewById(R.id.txt_bitacora);

        //Buscar el fichero
        //filelist = Devuelve un array con los ficheros almacenados por la aplicacion
        String archivos[] = fileList();

        //Siempre debe ser archivo txt
        if(ArchivoExiste(archivos,"bitacora.txt")){
            //InputStreamReader=Clase que permite abrir un archivo para leer
            //openFileInput()=Indica el archivo que queremo abrir
            try {
                InputStreamReader archivo=new InputStreamReader(openFileInput("bitacora.txt"));
                //BufferedReader= nos permite leer objetos de la clase InputStreamReader
                BufferedReader br=new BufferedReader(archivo);
                //Lee la primera linea
                String linea=br.readLine();
                String bitacoraCompleta="";

                //Lee linea por linea el archivo
                while(linea!=null){
                    bitacoraCompleta=bitacoraCompleta+linea+"\n";
                    linea=br.readLine();
                }

                //Cierra el archivo
                br.close();
                archivo.close();

                //Muestra el texto
                et.setText(bitacoraCompleta);
            } catch (IOException e){

            }


        }
    }

    private boolean ArchivoExiste(String archivos[], String nombreArchivo){
        //Cuando se trabaja con metodos booleanos no son necesarias las llaves
        for(int i=0;i<archivos.length;i++)
            if(nombreArchivo.equals(archivos[i]))
                return true;
        return false;
    }

    //Método para el boton guardar
    public void Guardar(View view){
        //OutputStreamWriter= Manda texto a un nuevo archivo que vamos a escribir
        try {
            //Siempre poner en un try catch para eliminar el error openfile
            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput("bitacora.txt", Activity.MODE_PRIVATE));
            //Obtenemos el texto y convertimos en String
            archivo.write(et.getText().toString());
            //Buf=Limpiamos el canal que se uso para transportar el texto
            //flush=Limpia
            archivo.flush();
            archivo.close();
        }catch (IOException e){

        }
        Toast.makeText(this,"Bitácora guardada correctamente",Toast.LENGTH_SHORT).show();
        finish();
    }
}