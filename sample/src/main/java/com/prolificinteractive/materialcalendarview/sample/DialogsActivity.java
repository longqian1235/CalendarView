package com.prolificinteractive.materialcalendarview.sample;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarUtils;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateFocusListener;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.format.ArrayWeekDayFormatter;
import com.prolificinteractive.materialcalendarview.sample.decorators.MoranDayDecorator;
import com.prolificinteractive.materialcalendarview.sample.decorators.MoranDayFocusDecorator;
import com.prolificinteractive.materialcalendarview.sample.decorators.MoranDaySelDecorator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashSet;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Shows off the most basic usage
 */
public class DialogsActivity extends AppCompatActivity {

    private static final DateFormat FORMATTER = SimpleDateFormat.getDateInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialogs);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button_normal_dialog)
    void onNormalDialogClick() {
        new SimpleDialogFragment().show(getSupportFragmentManager(), "test-normal");
    }

    @OnClick(R.id.button_simple_dialog)
    void onSimpleCalendarDialogClick() {
        new SimpleCalendarDialogFragment().show(getSupportFragmentManager(), "test-simple-calendar");
    }

    public static class SimpleDialogFragment extends AppCompatDialogFragment {

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return new AlertDialog.Builder(getActivity())
                    .setTitle(R.string.title_activity_dialogs)
                    .setMessage("Test Dialog")
                    .setPositiveButton(android.R.string.ok, null)
                    .create();
        }
    }

    public static class SimpleCalendarDialogFragment extends AppCompatDialogFragment
            implements OnDateSelectedListener ,OnDateFocusListener{

        private MoranDayDecorator decorator_nor;
        private MoranDaySelDecorator decorator_sel;
        private MoranDayFocusDecorator decorator_focus;

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            LayoutInflater inflater = getActivity().getLayoutInflater();

            //inflate custom layout and get views
            //pass null as parent view because will be in dialog layout
            View view = inflater.inflate(R.layout.dialog_basic, null);


            decorator_nor = new MoranDayDecorator(getContext());
            decorator_sel = new MoranDaySelDecorator(getContext());
            decorator_focus = new MoranDayFocusDecorator(getContext());
            MaterialCalendarView widget = (MaterialCalendarView) view.findViewById(R.id.calendarView);

            widget.addDecorators(decorator_nor,decorator_sel,decorator_focus);
            widget.setLeftArrowMask(getResources().getDrawable(R.drawable.selector_arrow_left));
            widget.setRightArrowMask(getResources().getDrawable(R.drawable.selector_arrow_right));
            widget.setWeekDayFormatter(new ArrayWeekDayFormatter(getResources().getStringArray(R.array.array_moran_weekdays)));
            widget.setTileHeight(60);
            widget.setTileWidth(84);
            widget.setWeekDayTextAppearance(R.style.WeekdayTextAppearance);
            widget.setHeaderTextAppearance(R.style.monthTextAppearance);
            widget.setDateTextAppearance(R.style.dayTextAppearance);
            widget.state().edit()
                    .setMinimumDate(CalendarDay.from(CalendarUtils.getInstance()))
                    .commit();

            widget.setOnDateChangedListener(this);
            widget.setOnDateFocusListener(this);

            return new AlertDialog.Builder(getActivity())
                    .setView(view)
                    .create();
        }

        @Override
        public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {

            HashSet<CalendarDay> hashSet = decorator_sel.getDays();
            if(hashSet != null && hashSet.contains(date)){
                decorator_sel.removeDay(date);
                decorator_focus.setFocusDay(date,false);

            }else{
                decorator_sel.addOneDay(date);
                decorator_focus.setFocusDay(date,true);

            }
            widget.invalidateDecorators();

        }

        @Override
        public void onDateFocus(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean isFocus) {
            HashSet<CalendarDay> hashSet = decorator_sel.getDays();
            if(hashSet != null && hashSet.contains(date)){
                decorator_focus.setFocusDay(date,true);
            }else{
                decorator_focus.setFocusDay(date,false);
            }
//            decorator_focus.setFocusDay(date,isFocus);
            widget.invalidateDecorators();
        }
    }
}
