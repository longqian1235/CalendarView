package com.prolificinteractive.materialcalendarview.sample.decorators;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.sample.R;

import java.util.HashSet;
import java.util.Iterator;

/**
 * Decorate 2 days.
 */
public class MoranDaySelDecorator implements DayViewDecorator {

  private final HashSet<CalendarDay> list = new HashSet<>();
  private final Drawable drawable;

  public MoranDaySelDecorator(final Context context) {
    drawable = context.getResources().getDrawable(R.drawable.shape_day_sel);
  }

  @Override
  public boolean shouldDecorate(CalendarDay day) {
    return list.contains(day);
  }

  @Override
  public void decorate(DayViewFacade view) {
    view.setSelectionDrawable(drawable);
    view.addSpan(new RelativeSizeSpan(1.1f));
    view.addSpan(new ForegroundColorSpan(0xffffb400));
  }

  /**
   * We're changing the dates, so make sure to call {@linkplain MaterialCalendarView#invalidateDecorators()}
   */

  public void addOneDay(CalendarDay day){

    if(list.size() == 2)
      list.clear();
    list.add(day);
  }
  public void removeDay(CalendarDay day){

    if(list != null && list.size() > 0 && list.contains(day)){
      list.remove(day);
    }
  }

  public HashSet<CalendarDay> getDays(){
    return list;
  }
}
