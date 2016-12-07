package com.mac.training.asynctask1;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText eT;
    TextView tV, tVI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eT = (EditText) findViewById(R.id.editText) ;
        tV = (TextView) findViewById(R.id.textView);
        tVI =  (TextView) findViewById(R.id.txtInfo);


    }

    public void onDo(View view) {

        int tmp = Integer.parseInt( eT.getText().toString());

        new MyAT().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,tmp);
    }

    class MyAT extends AsyncTask<Integer, Integer, String>{


        @Override
        protected String doInBackground(Integer[] integers) {
            try {
                for (int i = 1; i <= integers[0]; i++) {
                    Thread.sleep(1000);
                    publishProgress((Integer) (i * 100)/ integers[0] );
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Log.d("Algo", integers[0] + "Beep");

            return String.valueOf(integers[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            tVI.setText(s + " Beep\n" + tVI.getText().toString());
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            tV.setText(String.valueOf(values[0]));

        }
    }

}
