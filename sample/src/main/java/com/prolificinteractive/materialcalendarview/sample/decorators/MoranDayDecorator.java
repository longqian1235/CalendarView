package com.prolificinteractive.materialcalendarview.sample.decorators;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.sample.R;

import java.util.HashSet;

/**
 * Decorate 2 days.
 */
public class MoranDayDecorator implements DayViewDecorator {

  private final Drawable drawable_nor;


  public MoranDayDecorator(final Context context) {
    drawable_nor = context.getResources().getDrawable(R.drawable.shape_day_nor);

  }

  @Override
  public boolean shouldDecorate(CalendarDay day) {
    return true;
  }

  @Override
  public void decorate(DayViewFacade view) {
    view.setSelectionDrawable(drawable_nor);
//    view.addSpan(new AbsoluteSizeSpan(17));
//    view.addSpan(new ForegroundColorSpan(0xffffffff));
  }

}
