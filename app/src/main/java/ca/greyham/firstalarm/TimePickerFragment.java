package ca.greyham.firstalarm;


import android.app.AlarmManager;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.support.v4.app.Fragment;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    private static final int MAX_EXTRA_ALARMS = 5;
    private static final int MIN_EXTRA_ALARMS = 0;
    private static final int MAX_TIME_ADDITION = 5;
    private static final int MIN_TIME_ADDITION = 1;

    private int numAlarms;
    private ArrayList<Integer> alarmIDs = new ArrayList<Integer>();
    private ArrayList<Intent> intentArrayList = new ArrayList<Intent>();

    public TimePickerFragment() {
        // Required empty public constructor
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), this, hour, minute, false);
    }

    public void setAlarmNumber(int num) {
        this.numAlarms = num;
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String textString = "Hour: " + String.valueOf(hourOfDay) + ", Minute: " + String.valueOf(minute);
        Toast.makeText(getActivity(), textString, Toast.LENGTH_LONG).show();

        int alarmAddition = ThreadLocalRandom.current().nextInt(MIN_EXTRA_ALARMS, MAX_EXTRA_ALARMS);
        numAlarms += alarmAddition;
        for(int i = 0; i < numAlarms; i++) {
            int timeAddition = ThreadLocalRandom.current().nextInt(MIN_TIME_ADDITION, MAX_TIME_ADDITION);
            minute += timeAddition;
            if (minute > 60) {
                hourOfDay++;
                minute = minute%60;
            }
            setNewAlarm(hourOfDay, minute);
        }
    }

    private void setNewAlarm(int alarmTimeMilli) {

        AlarmManager alarmManager = (AlarmManager) this.getActivity().getSystemService(Context.ALARM_SERVICE);

        // Create an ID, and save it away.
        int uniqueId = (int) System.currentTimeMillis();
        alarmIDs.add(uniqueId);

        // Create an intent to create an alarm
        Intent alarmIntent = new Intent();
        alarmIntent.setAction(AlarmClock.ACTION_SET_ALARM);
        alarmIntent.putExtra("EXTRA_HOUR", hour);
        alarmIntent.putExtra("EXTRA_MINUTES", min);
        alarmIntent.putExtra("EXTRA_SKIP_UI", true);

        Intent intent = new Intent(context, OnAlarmReceiver.class);
        // Loop counter `i` is used as a `requestCode`
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, i, intent, 0);
        // Single alarms in 1, 2, ..., 10 minutes (in `i` minutes)
        mgrAlarm.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + 60000 * i,
                pendingIntent);


        intentArrayList.add(alarmIntent);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this.getActivity(), uniqueId, alarmIntent, PendingIntent.FLAG_ONE_SHOT);
        long triggerTimeMillis = 0;
        alarmManager.set(AlarmManager.RTC_WAKEUP, triggerTimeMillis, )

    }



}
