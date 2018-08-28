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

/**
 * Decorate 2 days.
 */
public class MoranDayFocusDecorator implements DayViewDecorator {

  private CalendarDay day = new CalendarDay();
  private final Drawable drawable;
  private boolean isSelected;

  public MoranDayFocusDecorator(final Context context) {
    drawable = context.getResources().getDrawable(R.drawable.shape_day_focus);
  }

  @Override
  public boolean shouldDecorate(CalendarDay day) {
    return day != null && day.equals(this.day);
  }

  @Override
  public void decorate(DayViewFacade view) {
    view.setSelectionDrawable(drawable);
    view.addSpan(new RelativeSizeSpan(1.1f));
    if(isSelected){
      view.addSpan(new ForegroundColorSpan(0xffffb400));
    }else{
      view.addSpan(new ForegroundColorSpan(0xffffffff));

    }
  }

  /**
   * We're changing the dates, so make sure to call {@linkplain MaterialCalendarView#invalidateDecorators()}
   */
//  public void setFocusDay(final CalendarDay day) {
//    setFocusDay(day,false);
//
//  }
  public void setFocusDay(final CalendarDay day,boolean isSelected,boolean isFocus) {
    if(day == null)
      return;
    if(isFocus){
      this.day = day;
      this.isSelected = isSelected;
    }else{
      this.day = null;
      this.isSelected = isSelected;
    }


  }
}
