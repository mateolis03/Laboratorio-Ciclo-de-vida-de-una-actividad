package unipiloto.edu.co.laboratorio_ciclo_de_vida_de_una_actividad;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private int seconds=0;
    private int lap=0;
    private boolean runnig;
    private int totalTime=0;
    private boolean wasRunning;
    private TextView lapView1;
    private TextView lapView2;
    private TextView lapView3;
    private TextView lapView4;
    private TextView lapView5;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState != null){
            seconds = savedInstanceState.getInt("seconds");
            runnig =savedInstanceState.getBoolean("runnig");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
            lap =savedInstanceState.getInt("lap");
        }
        runTimer();
    }
    public void onSavedInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("seconds",seconds);
        savedInstanceState.putBoolean("runnig",runnig);
        savedInstanceState.putBoolean("wasRunning",wasRunning);
        savedInstanceState.putInt("lap",lap);

    }
    public void  onResume() {
        super.onResume();
        if(wasRunning){
            runnig = true;
        }
    }
    public void onClickLap(View view) {
        lap++;
        totalTime += seconds;
        seconds =0;

    }
    public void  onPause() {
        super.onPause();
        wasRunning =runnig;
        runnig = false;
    }
    public void onClickStop(View view) {
        runnig = false;
    }

    public void onClickRest(View view) {
        runnig = false;
        seconds = 0;
        totalTime = 0;
        lap = 0;
        lapView1.setText("");
        lapView2.setText("");
        lapView3.setText("");
        lapView4.setText("");
        lapView5.setText("");

    }

    public void onClickStart(View view) {
        runnig = true;
    }

    private void runTimer(){
        final TextView  timeView = findViewById(R.id.time_view);
        lapView1 = findViewById(R.id.lap1_view);
        lapView2 = findViewById(R.id.lap2_view);
        lapView3 = findViewById(R.id.lap3_view);
        lapView4 = findViewById(R.id.lap4_view);
        lapView5 = findViewById(R.id.lap5_view);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @SuppressLint("SetTextI18n")
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600 )/60;
                int secs = seconds % 60;
                String time = String.format(Locale.getDefault(), "%d:%02d:%02d",hours,minutes,secs);
                timeView.setText(time);
                int totalHours = totalTime /3600;
                int totalMinutes = (totalTime % 3600 )/60;
                int totalSecs = totalTime % 60;
                String total = String.format(Locale.getDefault(), "%d:%02d:%02d",totalHours,totalMinutes,totalSecs);
                if (lap ==1) {
                    lapView1.setText("lap " + lap + " :" + total +"\n");
                }
                else if (lap ==2) {
                    lapView2.setText("lap " + lap + " :" + total +"\n");
                }
                else if (lap ==3) {
                    lapView3.setText("lap " + lap + " :" + total +"\n");
                }
                else if (lap ==4) {
                    lapView4.setText("lap " + lap + " :" + total +"\n");
                }
                else if (lap ==5) {
                    lapView5.setText("lap " + lap + " :" + total +"\n");
                }
                if(runnig){
                    seconds++;
                }
                handler.postDelayed(this, 100);
            }
        });
    }


}