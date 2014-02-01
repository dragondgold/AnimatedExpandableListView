package com.expandable.test;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.tjerkw.slideexpandable.library.AbstractSlideExpandableListAdapter;
import com.tjerkw.slideexpandable.library.ActionSlideExpandableListView;

public class MainActivity extends ActionBarActivity {

    ActionSlideExpandableListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        list = (ActionSlideExpandableListView) findViewById(R.id.list);
        list.setAdapter(buildDummyData());
        ((AbstractSlideExpandableListAdapter)list.getAdapter()).setAnimationDuration(200);
        ((AbstractSlideExpandableListAdapter)list.getAdapter()).setCollapseLastExpanded(false);
        list.setDivider(null);

        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        list.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode actionMode, int i, long l, boolean b) {
                actionMode.setSubtitle(list.getCheckedItemCount() + " items checked");
            }

            @Override
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                actionMode.setTitle("Select items!");
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return true;
            }

            @Override
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                return true;
            }

            @Override
            public void onDestroyActionMode(ActionMode actionMode) {

            }
        });
    }

    /**
     * Builds dummy data for the test.
     * In a real app this would be an adapter
     * for your data. For example a CursorAdapter
     */
    public ListAdapter buildDummyData() {
        final int SIZE = 3;

        String[] values = new String[SIZE];
        for(int i = 0; i < SIZE; i++) {
            values[i] = "Item " + i;
        }

        return new ArrayAdapter<String>(this, R.layout.expandable_list_item, R.id.title_view, values);
    }

}
