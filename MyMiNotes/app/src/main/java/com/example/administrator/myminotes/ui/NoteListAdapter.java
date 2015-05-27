package com.example.administrator.myminotes.ui;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

import java.util.HashMap;

/**
 * Created by Administrator on 2015/5/27.
 * TODO:∞—’‚–¥ÕÍ
 */
public class NoteListAdapter extends CursorAdapter {

    private boolean mChoiceMode;
    private HashMap<Integer, Boolean> mSelectedIndex;


    public NoteListAdapter(Context context) {
        super(context, null);

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return new NoteListItem(context);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        if (view instanceof NoteListItem) {
            NoteItemData itemData = new NoteItemData(context, cursor);
            ((NoteListItem) view).bind(context, itemData, mChoiceMode,
                    isSelectedItem(cursor.getPosition()));
        }
    }

    @Override
    public void changeCursor(Cursor cursor) {
        super.changeCursor(cursor);
    }

    public boolean isSelectedItem(final int position) {
        if (null == mSelectedIndex.get(position)) {
            return false;
        }
        return mSelectedIndex.get(position);
    }
}
