package com.example.administrator.myminotes.data;

import android.net.Uri;

/**
 * Created by Administrator on 2015/5/27.
 */
public class Notes {

    public static final String AUTHORITY = "my_minotes";

    public static final int TYPE_NOTE = 0;
    public static final int TYPE_FOLDER = 1;
    public static final int TYPE_SYSTEM = 2;

    public static final int ID_ROOT_FOLDER = 0;
    public static final int ID_TEMPORARY_FOLDER = -1;
    public static final int ID_CALL_RECORD_FOLDER = -2;
    public static final int ID_TRASH_FOLDER = -3;

    public static class DataConstants {
        public static final String NOTE = TextNote.CONTENT_ITEM_TYPE;
        public static final String CALL_NOTE = CallNote.CONTENT_ITEM_TYPE;
    }

    /**
     * Uri to query all notes and folders
     */
    public static final Uri CONTENT_NOTE_URI = Uri.parse("content://" + AUTHORITY + "/note");

    /**
     * Uri to query data
     */
    public static final Uri CONTENT_DATA_URI = Uri.parse("content://" + AUTHORITY + "/data");

    public interface NoteColumns {
        /**
         * ID
         */
        public static final String ID = "_id";

        /**
         * ��һ���ID
         */
        public static final String PARENT_ID = "parent_id";

        /**
         * ����ʱ��
         */
        public static final String CREATED_DATE = "created_date";

        /**
         * �޸�ʱ��
         */
        public static final String MODIFIED_DATE = "modified_date";


        /**
         * ����ʱ��
         */
        public static final String ALERTED_DATE = "alert_date";

        /**
         * ��Ҫ����
         */
        public static final String SNIPPET = "snippet";

        /**
         * ���沿��ID
         */
        public static final String WIDGET_ID = "widget_id";

        /**
         * ���沿������
         */
        public static final String WIDGET_TYPE = "widget_type";

        /**
         * ������ɫID
         */
        public static final String BG_COLOR_ID = "bg_color_id";

        /**
         * �Ƿ��и�����������Ƶ��ͼƬ��Note�и���
         */
        public static final String HAS_ATTACHMENT = "has_attachment";

        /**
         * ������ļ��У����ʾ������Note����
         */
        public static final String NOTES_COUNT = "notes_count";

        /**
         * ��ʾ�ļ��л���Note
         */
        public static final String TYPE = "type";

        /**
         * ���һ��ͬ����ʱ��
         */
        public static final String SYNC_ID = "sync_id";

        /**
         * ��־���������Ƿ�ı䣬����ͬ��ʱʹ��
         */
        public static final String LOCAL_MODIFIED = "local_modified";

        /**
         * �ƶ�������ļ���ʱ����¼ԭ�������ļ���
         */
        public static final String ORIGIN_PARENT_ID = "origin_parent_id";

        /**
         * �ճ�ͬ��ID
         */
        public static final String GTASK_ID = "gtask_id";

        /**
         * �汾
         */
        public static final String VERSION = "version";
    }

    public interface DataColumns {
        /**
         * ID
         * <P> Type: INTEGER (long) </P>
         */
        public static final String ID = "_id";

        /**
         * MIME����
         * <P> Type: Text </P>
         */
        public static final String MIME_TYPE = "mime_type";

        /**
         * ������Note��ID
         * <P> Type: INTEGER (long) </P>
         */
        public static final String NOTE_ID = "note_id";

        /**
         * ����ʱ��
         * <P> Type: INTEGER (long) </P>
         */
        public static final String CREATED_DATE = "created_date";

        /**
         * ���һ���޸�ʱ��
         * <P> Type: INTEGER (long) </P>
         */
        public static final String MODIFIED_DATE = "modified_date";

        /**
         * ����
         * <P> Type: TEXT </P>
         */
        public static final String CONTENT = "content";


        /**
         * Generic data column, the meaning is {@link #MIMETYPE} specific, used for
         * integer data type
         * <P> Type: INTEGER </P>
         */
        public static final String DATA1 = "data1";

        /**
         * Generic data column, the meaning is {@link #MIMETYPE} specific, used for
         * integer data type
         * <P> Type: INTEGER </P>
         */
        public static final String DATA2 = "data2";

        /**
         * Generic data column, the meaning is {@link #MIMETYPE} specific, used for
         * TEXT data type
         * <P> Type: TEXT </P>
         */
        public static final String DATA3 = "data3";

        /**
         * Generic data column, the meaning is {@link #MIMETYPE} specific, used for
         * TEXT data type
         * <P> Type: TEXT </P>
         */
        public static final String DATA4 = "data4";

        /**
         * Generic data column, the meaning is {@link #MIMETYPE} specific, used for
         * TEXT data type
         * <P> Type: TEXT </P>
         */
        public static final String DATA5 = "data5";
    }

    public static final class TextNote implements DataColumns {
        /**
         * Mode to indicate the text in check list mode or not
         * <P> Type: Integer 1:check list mode 0: normal mode </P>
         */
        public static final String MODE = DATA1;

        public static final int MODE_CHECK_LIST = 1;

        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/text_note";

        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/text_note";

        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/text_note");
    }

    public static final class CallNote implements DataColumns {
        /**
         * Call date for this record
         * <P> Type: INTEGER (long) </P>
         */
        public static final String CALL_DATE = DATA1;

        /**
         * Phone number for this record
         * <P> Type: TEXT </P>
         */
        public static final String PHONE_NUMBER = DATA3;

        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/call_note";

        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/call_note";

        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/call_note");
    }
}
