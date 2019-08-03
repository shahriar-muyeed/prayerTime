package com.example.prayer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class timingFragment extends Fragment{
    public static String f="";

    public static TextView date;
    public static TextView fazar;
    public static TextView juhur;
    public static TextView asar;
    public static TextView mugrib;
    public static TextView esha;

    public static Button fazarButton;
    public static Button juhurButton;
    public static Button asarButton;
    public static Button mugribButton;
    public static Button eshaButton;

    public timingFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.time_frag,container,false);

        fazar=(TextView)view.findViewById(R.id.fazarTime);
        juhur=(TextView)view.findViewById(R.id.juhurTime);
        asar=(TextView)view.findViewById(R.id.asarTime);
        mugrib=(TextView)view.findViewById(R.id.mugribTime);
        esha=(TextView)view.findViewById(R.id.eshaTime);
        date=(TextView)view.findViewById(R.id.dateNum);

        dailyTime process1= new dailyTime();
        process1.execute();

        Log.d("bal", String.valueOf(dailyTime.fajar));


        final int[] fazarHour=new int[2];
        int[] juhurHour=new int[2];
        int[] asarHour=new int[2];
        int[] mugribHour=new int[2];
        int[] eshaHour=new int[2];


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                    fazarButton=(Button)view.findViewById(R.id.fazarButton);
                    fazarButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent(AlarmClock.ACTION_SET_ALARM);
                            i.putExtra(AlarmClock.EXTRA_HOUR,getTime(fazar)[0]);
                            i.putExtra(AlarmClock.EXTRA_MINUTES,getTime(fazar)[1]);
                            startActivity(i);
                        }
                    });
                    juhurButton=(Button)view.findViewById(R.id.juhurbutton);
                    juhurButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent(AlarmClock.ACTION_SET_ALARM);
                            i.putExtra(AlarmClock.EXTRA_HOUR,getTime(juhur)[0]);
                            i.putExtra(AlarmClock.EXTRA_MINUTES,getTime(juhur)[1]);
                            startActivity(i);
                        }
                    });
                    asarButton=(Button)view.findViewById(R.id.asarButton);
                    asarButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent(AlarmClock.ACTION_SET_ALARM);
                            i.putExtra(AlarmClock.EXTRA_HOUR,getTime(asar)[0]+12);
                            i.putExtra(AlarmClock.EXTRA_MINUTES,getTime(asar)[1]);
                            startActivity(i);
                        }
                    });
                    mugribButton=(Button)view.findViewById(R.id.mugribButton);
                    mugribButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent(AlarmClock.ACTION_SET_ALARM);
                            i.putExtra(AlarmClock.EXTRA_HOUR,getTime(mugrib)[0]+12);
                            i.putExtra(AlarmClock.EXTRA_MINUTES,getTime(mugrib)[1]);
                            startActivity(i);
                        }
                    });
                    eshaButton=(Button)view.findViewById(R.id.eshaButton);
                    eshaButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent(AlarmClock.ACTION_SET_ALARM);
                            i.putExtra(AlarmClock.EXTRA_HOUR,getTime(esha)[0]+12);
                            i.putExtra(AlarmClock.EXTRA_MINUTES,getTime(esha)[1]);
                            startActivity(i);
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
    private int[] getTime(TextView textView){
        String[] separated = textView.getText().toString().split(" ");
        String[] separated1 = separated[0].split(":");

        int[] arr = new int[2];
        arr[0] = Integer.parseInt( separated1[0]);
        arr[1] = Integer.parseInt( separated1[1]);
        return arr;
    }
}
