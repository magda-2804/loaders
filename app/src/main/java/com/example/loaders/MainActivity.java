package com.example.loaders;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //  ProgressDialog progreso;
    ProgressDialog barProgressDialog;
    Button load;
    Handler updateBarHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ProgressDialog progreso = new ProgressDialog(this);
//        progreso.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        progreso.setIcon(R.drawable.ic_launcher_background);
//        progreso.setTitle("Barras de Progreso");
//        progreso.show();

        load = (Button) findViewById(R.id.load);
        updateBarHandler=new Handler();


        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                barraDeProgreso(view);
            }
        });
    }

    public void barraDeProgreso(View v){
        barProgressDialog = new ProgressDialog(MainActivity.this);
        barProgressDialog.setTitle("Procesando");
        barProgressDialog.setMessage("Por favor espere ...");
        barProgressDialog.setProgressStyle(barProgressDialog.STYLE_HORIZONTAL);
        barProgressDialog.setProgress(0);
        barProgressDialog.setMax(10);
        barProgressDialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <barProgressDialog.getMax() ; i++) {
                    trabajo();

                    updateBarHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            barProgressDialog.incrementProgressBy(1);
                        }
                    });
                }
                barProgressDialog.dismiss();
            }

        }).start();

    }

    public void trabajo(){
        try {
            Thread.sleep((int) Math.floor(Math.random()*(900)+100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

