package com.prolificinteractive.materialcalendarview.sample.decorators;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.sample.R;

/**
 * Decorate 2 days.
 */
public class MoranDayUnFocusDecorator implements DayViewDecorator {

  private CalendarDay today;
  private boolean isSelected;

  public MoranDayUnFocusDecorator(final Context context) {
    today = new CalendarDay();
  }

  @Override
  public boolean shouldDecorate(CalendarDay day) {
    return day != null && day.isBefore(today);
  }

  @Override
  public void decorate(DayViewFacade view) {
    view.addSpan(new ForegroundColorSpan(0x33ffffff));

  }


}
