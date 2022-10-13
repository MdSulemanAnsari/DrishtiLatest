package com.application.drishtigems.utils;

import android.content.Context;
import android.net.Uri;
import android.widget.TextView;

import com.application.drishtigems.R;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class MyMarkerView extends MarkerView {
    private final TextView tvDate, tvPrice;
    private final IAxisValueFormatter xAxisValueFormatter;

    private final DecimalFormat format;

    public MyMarkerView(Context context, IAxisValueFormatter xAxisValueFormatter) {
        super(context, R.layout.custom_marker_view);
        this.xAxisValueFormatter = xAxisValueFormatter;
        tvDate = findViewById(R.id.tvDate);
        tvPrice = findViewById(R.id.tvPrice);
        format = new DecimalFormat("###.0");
    }

    // runs every time the MarkerView is redrawn, can be used to update the
    // content (user-interface)
    @Override
    public void refreshContent(Entry e, Highlight highlight) {

        tvDate.setText(xAxisValueFormatter.getFormattedValue(e.getX(), null)+" 2021");

        tvPrice.setText("$"+ NumberFormat.getNumberInstance(Locale.US).format(Math.round(e.getY())));

        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-( getWidth() / 2 ), -getHeight());
    }
}

