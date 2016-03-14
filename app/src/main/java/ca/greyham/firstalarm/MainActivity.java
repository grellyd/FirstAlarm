package ca.greyham.firstalarm;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;

public class MainActivity extends Activity {

    private final int MAX_ALARMS = 20;
    private final int MIN_ALARMS = 2;

    NumberPicker numberPicker;
    int numAlarms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numberPicker = (NumberPicker) findViewById(R.id.numberPicker_main);
        numberPicker.setMaxValue(MAX_ALARMS);
        numberPicker.setMinValue(MIN_ALARMS);
        numberPicker.setWrapSelectorWheel(false);
    }

    public void buttonMainNext_click(View v) {
        FragmentManager fragmentManager = getFragmentManager();
        numAlarms = numberPicker.getValue();

        DialogFragment newDialogFragment = new TimePickerFragment();
        newDialogFragment.show(fragmentManager, "timePicker");
    }

}
