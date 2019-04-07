package com.example.memoramatradiciones;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements Runnable{

    private TextView lblPuntaje;
    private TextView lblFallas;

    private int puntaje; //para aumentar el puntaje
    private int fallas;

    private ImageView imageView;
    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView imageView4;
    private ImageView imageView5;
    private ImageView imageView6;
    private ImageView imageView7;
    private ImageView imageView8;

    private Button button;

    private int []valores={1,2,3,4,1,2,3,4}; //son valores para idetificar con las cartas
    private ImageView imgBotones[];
    private int valorSeleccionado=-1; //variable para saber el ultimo valor escogido
    private int valorBorrar=0; //es para grabar que valor tiene que girar de nuevo cunado se equivoque


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        puntaje=0;
        fallas=0;
        setContentView(R.layout.activity_main);
        iniciarCartas();
        imgBotones=new ImageView[]{imageView,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8};
        desordenarCartas();
        button=(Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                puntaje = 0;
                fallas = 0;
                valorSeleccionado = -1;
                // int indice=1;
                Bitmap bpm = BitmapFactory.decodeResource(getResources(), R.drawable.atras);
                for (ImageView img : imgBotones) {
                    img.setImageBitmap(bpm);
                }
                lblPuntaje.setText(puntaje + "");
                lblFallas.setText(fallas + "");
                desordenarCartas();

            }
        });

        agregarEventosCartas();

        lblPuntaje= (TextView) findViewById(R.id.puntaje);
        lblFallas= (TextView) findViewById(R.id.fallas);
        //lblPuntaje.setText(0);

    }

    private Handler puente = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bitmap bpm = BitmapFactory.decodeResource(getResources(), R.drawable.atras);
            imgBotones[valorSeleccionado].setImageBitmap(bpm);
            valorSeleccionado = -1;
            imgBotones[valorBorrar].setImageBitmap(bpm);
        }
    };

    private void desordenarCartas()
    {
        Random rnd=new Random();
        int aux;
        int indiceAux;

        for(int i=0;i<valores.length;i++)
        {
            aux=valores[i]; //respaldo el valor del indice
            indiceAux=rnd.nextInt(6); //nuevo indice para cambiar el valor

            valores[i]=valores[indiceAux];
            valores[indiceAux]=aux;

        }

    }

    @SuppressLint("WrongConstant")
    private void controlador(int opcion, ImageView img)
    {
        Bitmap bpm=null;
        opcion--;
        switch (valores[opcion])
        {
            case 1: bpm= BitmapFactory.decodeResource(getResources(),R.drawable.esqueletos);break;
            case 2: bpm= BitmapFactory.decodeResource(getResources(),R.drawable.bandera);break;
            case 3: bpm= BitmapFactory.decodeResource(getResources(),R.drawable.muertitos);break;
            case 4: bpm= BitmapFactory.decodeResource(getResources(),R.drawable.posaditas);break;
        }

        if(valorSeleccionado==-1)
        {
            valorSeleccionado=opcion;
            img.setImageBitmap(bpm);
        }
        else
        {
            if(valores[valorSeleccionado]==valores[opcion])
            {
                puntaje++;
                lblPuntaje.setText(puntaje+"");

                //noinspection ResourceType,ResourceType,ResourceType,ResourceType
                Toast.makeText(this,"!Excelente!",100).show();
                img.setImageBitmap(bpm);
                valorSeleccionado=-1;
            }
            else
            {
                fallas++;
                lblFallas.setText(fallas + "");
                valorBorrar=opcion;
                img.setImageBitmap(bpm);
                Thread hilo=new Thread(this);
                hilo.start();


            }
        }


    }

    private void iniciarCartas()
    {
        imageView= (ImageView) findViewById(R.id.iBtn);
        imageView2= (ImageView) findViewById(R.id.iBtn2);
        imageView3= (ImageView) findViewById(R.id.iBtn3);
        imageView4= (ImageView) findViewById(R.id.iBtn4);
        imageView5= (ImageView) findViewById(R.id.iBtn5);
        imageView6= (ImageView) findViewById(R.id.iBtn6);
        imageView7= (ImageView) findViewById(R.id.iBtn7);
        imageView8= (ImageView) findViewById(R.id.iBtn8);
    }

    private void agregarEventosCartas()
    {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador(1,imageView);
            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador(2, imageView2);
            }
        });

        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador(3, imageView3);
            }
        });

        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador(4, imageView4);
            }
        });

        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador(5, imageView5);
            }
        });


        imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador(6, imageView6);
            }
        });



        imageView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador(7, imageView7);
            }
        });



        imageView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador(8, imageView8);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void run() {
        SystemClock.sleep(1000);
        // Bitmap bpm= BitmapFactory.decodeResource(getResources(),R.drawable.carta0);
        // imgBotones[valorSeleccionado].setImageBitmap(bpm);
        //  valorSeleccionado=-1;
        //  imgBotones[valorBorrar].setImageBitmap(bpm);
        Message msg = new Message();
        msg.obj = 12;
        puente.sendMessage(msg);

    }
}
