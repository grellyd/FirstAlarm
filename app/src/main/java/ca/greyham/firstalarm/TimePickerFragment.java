package ca.greyham.firstalarm;


import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {


    public TimePickerFragment() {
        // Required empty public constructor
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), this, hour, minute, false);
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String textString = "Hour: " + String.valueOf(hourOfDay) + ", Minute: " + String.valueOf(minute);
        Toast.makeText(getActivity(), textString, Toast.LENGTH_LONG).show();
        // TODO: Set alarms from here
    }

}
