package com.prolificinteractive.materialcalendarview;

import android.support.annotation.NonNull;

/**
 * The callback used to indicate a date has been selected or deselected
 */
public interface OnDateFocusListener {

    /**
     * Called when a user focus on a day.
     * There is no logic to prevent multiple calls for the same date and state.
     *
     * @param widget   the view associated with this listener
     * @param date     the date that was selected or unselected
     * @param isFocus true if the day is now selected, false otherwise
     */
    void onDateFocus(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean isFocus);
}
