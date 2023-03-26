package com.example.android2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {
  //declaracao de variavel
  private final Timer timer = new Timer();
  TimerTask timerTask;

  //carrega a tela
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //esta Instanciamdo o objeto
        timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        gotoMainActivity();
                    }
                });
            }
        };
        //esse codigo serve para dar o delay entre a primeira pra segunda tela do app
        timer.schedule(timerTask, 3000);

    }
    //metodo para carregar a tela 2
    private void gotoMainActivity(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
