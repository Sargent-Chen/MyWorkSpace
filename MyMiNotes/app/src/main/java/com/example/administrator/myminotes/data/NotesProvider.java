package com.example.administrator.myminotes.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import com.example.administrator.myminotes.ui.NoteDataBaseHandler;
import com.example.administrator.myminotes.data.Notes.NoteColumns;
import com.example.administrator.myminotes.data.Notes.DataColumns;
import com.example.administrator.myminotes.ui.NoteDataBaseHandler.TABLE;

public class NotesProvider extends ContentProvider {

    private static final int URI_NOTE = 1;
    private static final int URI_NOTE_ITEM = 2;
    private static final int URI_DATA = 3;
    private static final int URI_DATA_ITEM = 4;

    private static UriMatcher mUriMatcher;

    private NoteDataBaseHandler mNoteDataBaseHandler;
    private SQLiteDatabase db;

    static {
        mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mUriMatcher.addURI(Notes.AUTHORITY, "note", URI_NOTE);
        mUriMatcher.addURI(Notes.AUTHORITY, "note/#", URI_NOTE_ITEM);
        mUriMatcher.addURI(Notes.AUTHORITY, "data", URI_DATA);
        mUriMatcher.addURI(Notes.AUTHORITY, "data/#", URI_DATA_ITEM);
    }

    @Override
    public boolean onCreate() {
        // 完成初始化
        mNoteDataBaseHandler = NoteDataBaseHandler.getInstance(getContext());
        return false;
    }

    //TODO:完成查询
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // TODO:Implement this to handle requests to delete one or more rows.
        int count = 0;
        String id;
        db = mNoteDataBaseHandler.getWritableDatabase();
        boolean deleteData = false;
        switch (mUriMatcher.match(uri)) {
            case URI_NOTE:
                selection = "(" + selection + ") AND " + NoteColumns.ID + ">0 ";
                count = db.delete(TABLE.NOTE, selection, selectionArgs);
                break;
            case URI_NOTE_ITEM:
                id = uri.getPathSegments().get(1);
                /**
                 * ID that smaller than 0 is system folder which is not allowed to
                 * trash
                 */
                long noteId = Long.valueOf(id);
                if (noteId <= 0) {
                    break;
                }
                count = db.delete(TABLE.NOTE,
                        NoteColumns.ID + "=" + id + parseSelection(selection), selectionArgs);
                break;
            case URI_DATA:
                count = db.delete(TABLE.DATA, selection, selectionArgs);
                deleteData = true;
                break;
            case URI_DATA_ITEM:
                id = uri.getPathSegments().get(1);
                count = db.delete(TABLE.DATA,
                        DataColumns.ID + "=" + id + parseSelection(selection), selectionArgs);
                deleteData = true;
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        if (count > 0) {
            if (deleteData) {
                getContext().getContentResolver().notifyChange(Notes.CONTENT_NOTE_URI, null);
            }
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return count;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        long insertId;
        db = mNoteDataBaseHandler.getWritableDatabase();
        switch (mUriMatcher.match(uri)){
            case URI_NOTE:
                insertId = db.insert(TABLE.NOTE,null,values);
                break;
            case URI_DATA:
                //TODO:判断values中有无ID
                insertId = db.insert(TABLE.DATA,null,values);
                break;
            default:
                throw new IllegalArgumentException("Illegal URI");
        }
        //TODO:notifyChange
        return ContentUris.withAppendedId(uri,insertId);
    }


    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        db = mNoteDataBaseHandler.getReadableDatabase();
        Cursor c;
        String id;
        Log.d("NotesProvider", String.valueOf(mUriMatcher.match(uri)));
        Log.d("NotesProvider", uri.toString());
        switch (mUriMatcher.match(uri)) {
            case URI_NOTE:
                c = db.query(TABLE.NOTE, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case URI_NOTE_ITEM:
                id = uri.getPathSegments().get(1);
                c = db.query(TABLE.NOTE, projection, NoteColumns.ID + "=" + id
                        + parseSelection(selection), selectionArgs, null, null, sortOrder);
                break;
            case URI_DATA:
                c = db.query(TABLE.DATA, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case URI_DATA_ITEM:
                id = uri.getPathSegments().get(1);
                c = db.query(TABLE.DATA, projection, DataColumns.ID + "=" + id
                        + parseSelection(selection), selectionArgs, null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("illegal URI");
        }
        //在后台检测数据的变化，如果有变化就会有返回
        if (c != null) {
            c.setNotificationUri(getContext().getContentResolver(), uri);
        }
        return c;
    }

    private String parseSelection(String selection) {
        return TextUtils.isEmpty(selection) ? "(" + selection + ")" : "";
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: 还没有写完
        switch (mUriMatcher.match(uri)){
            case URI_NOTE:
                break;
            case URI_NOTE_ITEM:
                break;
            case URI_DATA:
                break;
            case URI_DATA_ITEM:
                break;
            default:

        }
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
