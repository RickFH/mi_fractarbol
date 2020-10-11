package com.example.mi_fractarbol;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import static android.graphics.Color.*;

public class MainActivity extends AppCompatActivity {
    SeekBar seekBar;
    ConstraintLayout layout;
    Vista vista;

    float cont = 500;
    float len = 500;
    double grados = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
        //esto seria el background
        getWindow ().setBackgroundDrawable ( new ColorDrawable ( BLUE ) );

        seekBar = (SeekBar) findViewById ( R.id.slider );
        layout = (ConstraintLayout) findViewById ( R.id.forma );
        vista = new Vista ( this );
        seekBar.setProgress ( 0 );
        seekBar.setMax ( 4000 );
        seekBar.setOnSeekBarChangeListener (
                new SeekBar.OnSeekBarChangeListener () {
                    @Override
                    public void onProgressChanged(SeekBar seekBar,
                                                  int progress, boolean fromUser) {
                        layout.removeAllViews ();
                        grados = progress / 10;
                        layout.addView ( vista, 0 );
                    }

                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }
                } );
        layout.addView ( vista, 0 );
    }

    class Vista extends View {
        public Vista(Context context) {
            super ( context );
        }

        public void onDraw(Canvas canvas) {
            //estos vendria siendo para poder usar el height que usa en el video
            int ancho = canvas.getWidth ();
            int largo = canvas.getHeight ();
            //esto seria como el setup que el usa para crear el canvas
            Paint paint = new Paint ();
            //esto seria el stroke pero aqui se tiene que poner separado el estilo y el colo
            paint.setStyle ( Paint.Style.STROKE );
            paint.setStrokeWidth ( 10 );
            paint.setColor ( GREEN );
            //aqui tienes que poner el canvas para cada instruccion de draw o dibujar
            canvas.translate ( (float) (ancho / 2.1), largo );
            Ramas ( 300, canvas, paint );
        }

        public void Ramas(float len, Canvas canvas, Paint paint) {
            canvas.drawLine ( 0, 0, 0, -len, paint );
            canvas.translate ( 0, -len );
            if (len > 4) {
                canvas.save ();
                canvas.rotate ( (float) grados );
                Ramas ( (float) (len * 0.67), canvas, paint );
                canvas.restore ();
                canvas.save ();
                canvas.rotate ( (float) -grados );
                Ramas ( (float) (len * 0.67), canvas, paint );
                canvas.restore ();
            }
        }
    }
}