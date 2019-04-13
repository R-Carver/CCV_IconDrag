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
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import static com.example.android.ccv_drag_test.R.id.container;

/**
 * Created by dma004 on 13.04.2019.
 */

public class Fragment2 extends Fragment {

    private static final String TAG = "Fragment2";
    GridLayout grid;
    ImageView deleteIcon;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment2_layout, container, false);

        view.findViewById(R.id.relativeLayout2).setOnDragListener(new DropZoneDragListener());
        view.findViewById(R.id.deletezone).setOnDragListener(new DeleteZoneDragListener());

        grid =  view.findViewById(R.id.gridLayout);

        deleteIcon = view.findViewById(R.id.ic_delete);
        deleteIcon.setVisibility(View.INVISIBLE);

        return view;
    }

    class DropZoneDragListener implements View.OnDragListener{

        @Override
        public boolean onDrag(View v, DragEvent event)
        {

            int action = event.getAction();
            switch(event.getAction()){
                case DragEvent.ACTION_DRAG_STARTED:
                    //do nothing
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    deleteIcon.setVisibility(View.VISIBLE);
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    deleteIcon.setVisibility(View.VISIBLE);
                    break;
                case DragEvent.ACTION_DROP:
                    // Dropped, reassign View to ViewGroup
                    // the view here is the image that is dragged
                    View view = (View)event.getLocalState();
                    // here the dragged image is removed from the view in which it was
                    ViewGroup owner = (ViewGroup) view.getParent();
                    owner.removeView(view);
                    //RelativeLayout container = (RelativeLayout) v;
                    //container.addView(view);
                    //view.setVisibility(View.VISIBLE);

                    grid.addView(view);
                    view.setVisibility(View.VISIBLE);
                    deleteIcon.setVisibility(View.INVISIBLE);

                    break;
                case DragEvent.ACTION_DRAG_ENDED:

                default:
                    break;
            }

            return true;
        }
    }

    class DeleteZoneDragListener implements View.OnDragListener{

        @Override
        public boolean onDrag(View v, DragEvent event)
        {

            int action = event.getAction();
            switch(event.getAction()){
                case DragEvent.ACTION_DRAG_STARTED:
                    //do nothing
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    deleteIcon.setVisibility(View.VISIBLE);
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    deleteIcon.setVisibility(View.VISIBLE);
                    break;
                case DragEvent.ACTION_DROP:
                    // Dropped, reassign View to ViewGroup
                    // the view here is the image that is dragged
                    View view = (View)event.getLocalState();
                    // here the dragged image is removed from the view in which it was
                    ViewGroup owner = (ViewGroup) view.getParent();
                    owner.removeView(view);
                    grid.removeView(view);
                    view.setVisibility(View.INVISIBLE);
                    deleteIcon.setVisibility(View.INVISIBLE);

                    break;
                case DragEvent.ACTION_DRAG_ENDED:

                default:
                    break;
            }

            return true;
        }
    }
}
