package com.reproductor.musica;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView imgPortada;
    private Button btnPlay, btnStop, btnLoop, btnBack, btnForward;
    MediaPlayer MP;
    int repetir = 2, posicion = 0;

    //vector para guardar oistas de audio solo 3 en este caso
    MediaPlayer vectormp [] = new MediaPlayer [3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgPortada = (ImageView)findViewById(R.id.imgPortada);
        btnPlay = (Button)findViewById(R.id.btnPlay);
        btnStop = (Button)findViewById(R.id.btnStop);
        btnLoop = (Button)findViewById(R.id.btnLoop);
        btnBack = (Button)findViewById(R.id.btnBack);
        btnForward = (Button)findViewById(R.id.btnForward);

        //asignar canciones a posiciones de vector
        vectormp [0] = MediaPlayer.create(MainActivity.this, R.raw.race);
        vectormp [1] = MediaPlayer.create(MainActivity.this, R.raw.sound);
        vectormp [2] = MediaPlayer.create(MainActivity.this, R.raw.tea);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (vectormp[posicion].isPlaying()) {
                    vectormp[posicion].pause();
                    btnPlay.setBackgroundResource(R.drawable.reproducir);
                    Toast.makeText(MainActivity.this, "Pausa", Toast.LENGTH_SHORT).show();
                } else {
                    vectormp[posicion].start();
                    btnPlay.setBackgroundResource(R.drawable.pausa);
                    Toast.makeText(MainActivity.this, "Reproduciendo", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (vectormp[posicion] != null){
                    vectormp[posicion].stop(); //stop vacia toda la info

                    vectormp [0] = MediaPlayer.create(MainActivity.this, R.raw.race);
                    vectormp [1] = MediaPlayer.create(MainActivity.this, R.raw.sound);
                    vectormp [2] = MediaPlayer.create(MainActivity.this, R.raw.tea);
                    posicion = 0;
                    btnPlay.setBackgroundResource(R.drawable.reproducir);
                    imgPortada.setImageResource(R.drawable.portada1);
                    Toast.makeText(MainActivity.this, "Detenido", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnLoop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (repetir == 1){
                    btnLoop.setBackgroundResource(R.drawable.no_repetir);
                    Toast.makeText(MainActivity.this, "No repetir", Toast.LENGTH_SHORT).show();
                    vectormp[posicion].setLooping(false);
                    repetir = 2;
                } else {
                    btnLoop.setBackgroundResource(R.drawable.repetir);
                    Toast.makeText(MainActivity.this, "Repetir", Toast.LENGTH_SHORT).show();
                    vectormp[posicion].setLooping(true);
                    repetir = 1;
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (posicion >= 1){

                    if (vectormp[posicion].isPlaying()){
                        vectormp[posicion].stop(); //stop borra toda la info
                        vectormp [0] = MediaPlayer.create(MainActivity.this, R.raw.race);
                        vectormp [1] = MediaPlayer.create(MainActivity.this, R.raw.sound);
                        vectormp [2] = MediaPlayer.create(MainActivity.this, R.raw.tea);
                        posicion--;
                        //cambiar portada
                        if (posicion == 0){
                            imgPortada.setImageResource(R.drawable.portada1);
                        } else if (posicion == 1) {
                            imgPortada.setImageResource(R.drawable.portada2);
                        } else if (posicion == 2) {
                            imgPortada.setImageResource(R.drawable.portada3);
                        }
                        vectormp[posicion].start();
                    } else {
                        posicion--;
                        //cambiar portada
                        if (posicion == 0){
                            imgPortada.setImageResource(R.drawable.portada1);
                        } else if (posicion == 1) {
                            imgPortada.setImageResource(R.drawable.portada2);
                        } else if (posicion == 2) {
                            imgPortada.setImageResource(R.drawable.portada3);
                        }
                    }

                } else {
                    Toast.makeText(MainActivity.this, "No hay mas canciones", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (posicion < vectormp.length -1){ //desboardamiento de memoria

                    //validacion para saber si esta tocando, detener e incrementar
                    if (vectormp[posicion].isPlaying()){
                        vectormp[posicion].stop();
                        posicion++;
                        vectormp[posicion].start();

                        //cambiar portada
                        if (posicion == 0){
                            imgPortada.setImageResource(R.drawable.portada1);
                        } else if (posicion == 1) {
                            imgPortada.setImageResource(R.drawable.portada2);
                        } else if (posicion == 2) {
                            imgPortada.setImageResource(R.drawable.portada3);
                        }

                    } else {
                        posicion++;

                        //cambiar portada
                        if (posicion == 0){
                            imgPortada.setImageResource(R.drawable.portada1);
                        } else if (posicion == 1) {
                            imgPortada.setImageResource(R.drawable.portada2);
                        } else if (posicion == 2) {
                            imgPortada.setImageResource(R.drawable.portada3);
                        }
                    }

                } else {
                    Toast.makeText(MainActivity.this, "No hay mas canciones", Toast.LENGTH_SHORT).show();
                }

            }
        });
        
    }
}