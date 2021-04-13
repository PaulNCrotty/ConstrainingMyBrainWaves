package com.example.constrainingmybrainwaves;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;

import static android.view.View.generateViewId;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConstraintLayout degreePlanContainer = findViewById(R.id.degree_plan_container);
        Log.d("IDS", String.format("Degree Plan Container ID: %d",degreePlanContainer.getId())); //2131230845

        int defaultTextHeight = 100; //getResources().getDimensionPixelSize(R.dimen.text_view_term_list_layout_height);
        int termBannerBackgroundColor = Color.parseColor("#3700B3");
        int courseContainerBackgroundColor = Color.parseColor("#5100CC");
        int defaultTextColor = Color.parseColor("#FFFFFF");

        // Degree plan level constraints (highest container level)
        ConstraintSet constraints = new ConstraintSet();
        constraints.clone(degreePlanContainer);

        //High-level term details
        TextView background = new TextView(this);
        background.setId(generateViewId());
//        background.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_CONSTRAINT, defaultTextHeight)); //get overwritten by constraint set... of no use
        background.setBackgroundColor(termBannerBackgroundColor);
        degreePlanContainer.addView(background);

        TextView termName = new TextView(this);
        termName.setId(generateViewId());
//        termName.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, defaultTextHeight)); //get overwritten by constraint set... of no use
        termName.setBackgroundColor(termBannerBackgroundColor);
        termName.setTextColor(defaultTextColor);
        termName.setText("Term One");
        degreePlanContainer.addView(termName);

        TextView termDates = new TextView(this);
        termDates.setId(generateViewId());
        termDates.setBackgroundColor(termBannerBackgroundColor);
        termDates.setTextColor(defaultTextColor);
        termDates.setText("2020-01-01 until 2020-06-30");
        degreePlanContainer.addView(termDates);

        ImageButton editIcon = new ImageButton(this);
        editIcon.setId(generateViewId());
        editIcon.setBackgroundColor(termBannerBackgroundColor);
        editIcon.setImageResource(R.drawable.ic_baseline_edit_24);
        degreePlanContainer.addView(editIcon);

        constraints.connect(background.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START);
        constraints.connect(background.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END);
        constraints.connect(background.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);
        constraints.constrainHeight(background.getId(), defaultTextHeight);

        constraints.connect(termName.getId(), ConstraintSet.START, background.getId(), ConstraintSet.START);
        constraints.connect(termName.getId(), ConstraintSet.TOP, background.getId(), ConstraintSet.TOP);
        constraints.constrainWidth(termName.getId(), ConstraintSet.WRAP_CONTENT);
        constraints.setMargin(termName.getId(), ConstraintSet.TOP, 20); //perhaps the style will still take effect; otherwise, we'll need some dimens in dp
        constraints.setMargin(termName.getId(), ConstraintSet.START, 20); //perhaps the style will still take effect; otherwise, we'll need some dimens in dp

        constraints.connect(termDates.getId(), ConstraintSet.END, editIcon.getId(), ConstraintSet.START);
        constraints.connect(termDates.getId(), ConstraintSet.TOP, background.getId(), ConstraintSet.TOP);
        constraints.constrainWidth(termDates.getId(), ConstraintSet.WRAP_CONTENT);
        constraints.setMargin(termDates.getId(), ConstraintSet.TOP, 20); //perhaps the style will still take effect; otherwise, we'll need some dimens in dp
        constraints.setMargin(termDates.getId(), ConstraintSet.START, 20);
        constraints.setMargin(termDates.getId(), ConstraintSet.END, 20);

        constraints.connect(editIcon.getId(), ConstraintSet.END, background.getId(), ConstraintSet.END);
        constraints.connect(editIcon.getId(), ConstraintSet.TOP, background.getId(), ConstraintSet.TOP);
        constraints.constrainWidth(editIcon.getId(), ConstraintSet.WRAP_CONTENT);
        constraints.setMargin(editIcon.getId(), ConstraintSet.END, 20);

        // Course container and high-level course details
        ConstraintLayout termCoursesContainer = new ConstraintLayout(this);
        termCoursesContainer.setId(generateViewId());
        termCoursesContainer.setBackgroundColor(courseContainerBackgroundColor);
        degreePlanContainer.addView(termCoursesContainer);

        constraints.connect(termCoursesContainer.getId(), ConstraintSet.START, background.getId(), ConstraintSet.START);
        constraints.connect(termCoursesContainer.getId(), ConstraintSet.END, background.getId(), ConstraintSet.END);
        constraints.connect(termCoursesContainer.getId(), ConstraintSet.TOP, background.getId(), ConstraintSet.BOTTOM); //begin where background border ends (vertically)
        constraints.constrainHeight(termCoursesContainer.getId(), ConstraintSet.WRAP_CONTENT); //TODO default to some generic value to allow for adding a course?

        ConstraintSet termCoursesConstraints = new ConstraintSet();
        termCoursesConstraints.clone(termCoursesContainer);

        TextView courseOneName = new TextView(termCoursesContainer.getContext());
        courseOneName.setId(generateViewId());
        courseOneName.setTextColor(defaultTextColor);
        courseOneName.setText("C191 - Android is hard ... isn't it");
        termCoursesContainer.addView(courseOneName);

        termCoursesConstraints.connect(courseOneName.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START);
        termCoursesConstraints.connect(courseOneName.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);
        termCoursesConstraints.constrainHeight(courseOneName.getId(), ConstraintSet.WRAP_CONTENT);
        termCoursesConstraints.constrainWidth(courseOneName.getId(), ConstraintSet.WRAP_CONTENT);
        termCoursesConstraints.setMargin(courseOneName.getId(), ConstraintSet.START, 35);

        TextView courseTwoName = new TextView(termCoursesContainer.getContext());
        courseTwoName.setId(generateViewId());
        courseTwoName.setTextColor(defaultTextColor);
        courseTwoName.setText("C291 - More Android is even harder ... Wow!");
        termCoursesContainer.addView(courseTwoName);

        termCoursesConstraints.connect(courseTwoName.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START);
        termCoursesConstraints.connect(courseTwoName.getId(), ConstraintSet.TOP, courseOneName.getId(), ConstraintSet.BOTTOM);
        termCoursesConstraints.constrainHeight(courseTwoName.getId(), ConstraintSet.WRAP_CONTENT);
        termCoursesConstraints.constrainWidth(courseTwoName.getId(), ConstraintSet.WRAP_CONTENT);
        termCoursesConstraints.setMargin(courseTwoName.getId(), ConstraintSet.START, 35);
        termCoursesConstraints.setMargin(courseTwoName.getId(), ConstraintSet.TOP, 35);

        TextView courseThreeName = new TextView(termCoursesContainer.getContext());
        courseThreeName.setId(generateViewId());
        courseThreeName.setTextColor(defaultTextColor);
        courseThreeName.setText("C391 - Almost getting it now ....");
        termCoursesContainer.addView(courseThreeName);

        termCoursesConstraints.connect(courseThreeName.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START);
        termCoursesConstraints.connect(courseThreeName.getId(), ConstraintSet.TOP, courseTwoName.getId(), ConstraintSet.BOTTOM);
        termCoursesConstraints.constrainHeight(courseThreeName.getId(), ConstraintSet.WRAP_CONTENT);
        termCoursesConstraints.constrainWidth(courseThreeName.getId(), ConstraintSet.WRAP_CONTENT);
        termCoursesConstraints.setMargin(courseThreeName.getId(), ConstraintSet.START, 35);
        termCoursesConstraints.setMargin(courseThreeName.getId(), ConstraintSet.TOP, 35);

        termCoursesConstraints.applyTo(termCoursesContainer);

        constraints.applyTo(degreePlanContainer);

    }


//    private void doStuff() {
//        View previousItem = null;
//        for(TextView tv : textViews) {
//            boolean lastItem =textViews.indexOf(tv) ==textViews.size() - 1;
//            if(previousItem == null) {
//                constraintSet.connect(tv.getId(), ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT);
//            } else {
//                constraintSet.connect(tv.getId(), ConstraintSet.LEFT, previousItem.getId(), ConstraintSet.RIGHT);
//                if(lastItem) {
//                    constraintSet.connect(tv.getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT);
//                }
//            }
//            previousItem = tv;
//        }
//    }
}

