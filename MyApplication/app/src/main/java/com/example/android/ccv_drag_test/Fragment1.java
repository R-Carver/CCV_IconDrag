package com.example.android.ccv_drag_test;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Display;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * Created by dma004 on 13.04.2019.
 */

public class Fragment1 extends Fragment {

    private static final String TAG = "Fragment1";

    ImageView nextPageIcon;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment1_layout, container, false);

        view.findViewById(R.id.myimage1).setOnTouchListener(new MyTouchListener());
        view.findViewById(R.id.myimage2).setOnTouchListener(new MyTouchListener());
        view.findViewById(R.id.relativeLayout1).setOnDragListener(new MyDragListener());

        nextPageIcon = view.findViewById(R.id.nextpage);
        nextPageIcon.setVisibility(View.INVISIBLE);

        return view;
    }

    private final class MyTouchListener implements View.OnTouchListener{

        public boolean onTouch(View view, MotionEvent motionEvent)
        {
            if(motionEvent.getAction() == MotionEvent.ACTION_DOWN)
            {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);

                // only show arrow icon if on the first page
                if(((MainActivity)getActivity()).getCurrentViewPager() == 0){
                    nextPageIcon.setVisibility(View.VISIBLE);
                }

                return true;
            }else{
                return false;
            }
        }
    }

    class MyDragListener implements View.OnDragListener{

        @Override
        public boolean onDrag(View v, DragEvent event)
        {
            final Point point = new Point((int) event.getX(), (int) event.getY());
            //Log.d("point",point.toString() );

            WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            //Log.d("size",size.toString() );

            int action = event.getAction();
            switch(event.getAction()){
                case DragEvent.ACTION_DRAG_STARTED:
                    //do nothing
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:

                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    nextPageIcon.setVisibility(View.INVISIBLE);
                    break;
                case DragEvent.ACTION_DROP:
                    // Dropped, reassign View to ViewGroup
                    // the view here is the image that is dragged
                    View view = (View)event.getLocalState();
                    // here the dragged image is removed from the view in which it was
                    ViewGroup owner = (ViewGroup) view.getParent();
                    owner.removeView(view);
                    RelativeLayout container = (RelativeLayout) v;
                    container.addView(view);
                    view.setVisibility(View.VISIBLE);
                    break;
                case DragEvent.ACTION_DRAG_ENDED:

                default:
                    break;
            }

            // change page if in 40 pixels to the right end of the screen
            boolean outOfRight = point.x > (size.x-40);
            if(outOfRight && ((MainActivity)getActivity()).getCurrentViewPager() == 0){
                Toast.makeText(getActivity(), "OUT Right", Toast.LENGTH_SHORT).show();
                ((MainActivity)getActivity()).setViewPager(1);
            }
            return true;
        }
    }
}
