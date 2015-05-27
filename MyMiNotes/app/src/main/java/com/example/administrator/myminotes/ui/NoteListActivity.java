package com.example.administrator.myminotes.ui;

import android.app.Activity;
import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.myminotes.R;
import com.example.administrator.myminotes.data.Notes;
import com.example.administrator.myminotes.data.Notes.NoteColumns;


public class NoteListActivity extends Activity implements View.OnClickListener {

    private static final int FOLDER_NOTES_LIST_QUERY_TOKEN = 0;

    //界面组件
    private TextView mTittleBar;
    private ListView mNotesListView;
    private Button mNewNoteBtn;

    private NoteListAdapter mNoteListAdapter;
    private BackGroundQueryHandler mBackGroundQueryHandler;
    private int mCurrentFolderId;

    private static final String NORMAL_SELECTION = NoteColumns.PARENT_ID + "=?";
    private static final String ROOT_FOLDER_SELECTION = "(" + NoteColumns.TYPE + "<>"
            + Notes.TYPE_SYSTEM + " AND " + NoteColumns.PARENT_ID + "=?)" + " OR ("
            + NoteColumns.ID + "=" + Notes.ID_CALL_RECORD_FOLDER + " AND "
            + NoteColumns.NOTES_COUNT + ">0)";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_list);
        initResource();
    }

    private void initResource() {
        mCurrentFolderId = Notes.ID_ROOT_FOLDER;
        mTittleBar = (TextView) findViewById(R.id.tv_title_bar);
        mNotesListView = (ListView) findViewById(R.id.notes_list);
        mNoteListAdapter = new NoteListAdapter(this);
        mNotesListView.setAdapter(mNoteListAdapter);
        mNewNoteBtn = (Button) findViewById(R.id.btn_new_note);
        mNewNoteBtn.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        startSynQuery();
    }

    private void startSynQuery() {
        mBackGroundQueryHandler = new BackGroundQueryHandler(getContentResolver());
        String selection = (mCurrentFolderId == Notes.ID_ROOT_FOLDER) ? ROOT_FOLDER_SELECTION :
                NORMAL_SELECTION;
        Log.d("strat query", "开始查询:"+Notes.CONTENT_NOTE_URI);
        mBackGroundQueryHandler.startQuery(FOLDER_NOTES_LIST_QUERY_TOKEN, null, Notes.CONTENT_NOTE_URI,
                NoteItemData.PROJECTION, selection, new String[]{String.valueOf(mCurrentFolderId)},
                NoteColumns.TYPE + " DESC," + NoteColumns.MODIFIED_DATE + " DESC");
        Log.d("end query", "查询结束");
    }

    private class BackGroundQueryHandler extends AsyncQueryHandler{

        public BackGroundQueryHandler(ContentResolver cr) {
            super(cr);
        }

        @Override
        protected void onQueryComplete(int token, Object cookie, Cursor cursor) {
            super.onQueryComplete(token, cookie, cursor);
            mNoteListAdapter.changeCursor(cursor);
            //TODO:switch token

        }
    }

    @Override
    public void onClick(View v) {
        //TODO:把这搞完
    }
}
