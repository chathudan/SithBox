package com.sithagi.sithbox.database.data.provider;

import com.sithagi.sithbox.database.data.provider.util.ColumnMetadata;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.net.Uri;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * @author Chathura Wijesinghe <cdanasiri@gmail.com>
 * 
 * 
 * */

public abstract class SithBoxContent {

    public static final Uri CONTENT_URI = Uri.parse("content://" + SithBoxProvider.AUTHORITY);

    private SithBoxContent() {
    }

    /**
     * Created in version 1
     */
    public static final class account extends SithBoxContent {

        private static final String LOG_TAG = account.class.getSimpleName();

        public static final String TABLE_NAME = "account";
        public static final String TYPE_ELEM_TYPE = "vnd.android.cursor.item/sithbox-account";
        public static final String TYPE_DIR_TYPE = "vnd.android.cursor.dir/sithbox-account";

        public static final Uri CONTENT_URI = Uri.parse(SithBoxContent.CONTENT_URI + "/" + TABLE_NAME);

        public static enum Columns implements ColumnMetadata {
            _ID(BaseColumns._ID, "integer"),
            ACCOUNT_NAME("account_name", "text"),
            ACCOUNT_ICON("account_icon", "text"),
            ACCOUNT_USER_NAME("account_userName", "text"),
            ACCOUNT_PASSWORD("account_password", "text"),
            ACCOUNT_2STEP("account_2Step", "text"),
            ACCOUNT_RECOVERY_EMAIL("account_recoveryEmail", "text"),
            ACCOUNT_PHONE("account_phone", "text"),
            ACCOUNT_KEY_PHASE("account_keyPhase", "text"),
            ACCOUNT_DESCRIPTION("account_description", "text");

            private final String mName;
            private final String mType;

            private Columns(String name, String type) {
                mName = name;
                mType = type;
            }

            @Override
            public int getIndex() {
                return ordinal();
            }

            @Override
            public String getName() {
                return mName;
            }

            @Override
            public String getType() {
                return mType;
            }
        }

        public static final String[] PROJECTION = new String[] {
                Columns._ID.getName(),
                Columns.ACCOUNT_NAME.getName(),
                Columns.ACCOUNT_ICON.getName(),
                Columns.ACCOUNT_USER_NAME.getName(),
                Columns.ACCOUNT_PASSWORD.getName(),
                Columns.ACCOUNT_2STEP.getName(),
                Columns.ACCOUNT_RECOVERY_EMAIL.getName(),
                Columns.ACCOUNT_PHONE.getName(),
                Columns.ACCOUNT_KEY_PHASE.getName(),
                Columns.ACCOUNT_DESCRIPTION.getName()
        };

        private account() {
            // No private constructor
        }

        public static void createTable(SQLiteDatabase db) {
            if (SithBoxProvider.ACTIVATE_ALL_LOGS) {
                Log.d(LOG_TAG, "account | createTable start");
            }
            db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + Columns._ID.getName() + " " + Columns._ID.getType() + ", " + Columns.ACCOUNT_NAME.getName() + " " + Columns.ACCOUNT_NAME.getType() + ", " + Columns.ACCOUNT_ICON.getName() + " " + Columns.ACCOUNT_ICON.getType() + ", " + Columns.ACCOUNT_USER_NAME.getName() + " " + Columns.ACCOUNT_USER_NAME.getType() + ", " + Columns.ACCOUNT_PASSWORD.getName() + " " + Columns.ACCOUNT_PASSWORD.getType() + ", " + Columns.ACCOUNT_2STEP.getName() + " " + Columns.ACCOUNT_2STEP.getType() + ", " + Columns.ACCOUNT_RECOVERY_EMAIL.getName() + " " + Columns.ACCOUNT_RECOVERY_EMAIL.getType() + ", " + Columns.ACCOUNT_PHONE.getName() + " " + Columns.ACCOUNT_PHONE.getType() + ", " + Columns.ACCOUNT_KEY_PHASE.getName() + " " + Columns.ACCOUNT_KEY_PHASE.getType() + ", " + Columns.ACCOUNT_DESCRIPTION.getName() + " " + Columns.ACCOUNT_DESCRIPTION.getType() + ", PRIMARY KEY (" + Columns._ID.getName() + ")" + ");");

            if (SithBoxProvider.ACTIVATE_ALL_LOGS) {
                Log.d(LOG_TAG, "account | createTable end");
            }
        }

        // Version 1 : Creation of the table
        public static void upgradeTable(SQLiteDatabase db, int oldVersion, int newVersion) {
            if (SithBoxProvider.ACTIVATE_ALL_LOGS) {
                Log.d(LOG_TAG, "account | upgradeTable start");
            }

            if (oldVersion < 1) {
                Log.i(LOG_TAG, "Upgrading from version " + oldVersion + " to " + newVersion
                        + ", data will be lost!");

                db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME + ";");
                createTable(db);
                return;
            }


            if (oldVersion != newVersion) {
                throw new IllegalStateException("Error upgrading the database to version "
                        + newVersion);
            }

            if (SithBoxProvider.ACTIVATE_ALL_LOGS) {
                Log.d(LOG_TAG, "account | upgradeTable end");
            }
        }

        static String getBulkInsertString() {
            return new StringBuilder("INSERT INTO ").append(TABLE_NAME).append(" ( ").append(Columns._ID.getName()).append(", ").append(Columns.ACCOUNT_NAME.getName()).append(", ").append(Columns.ACCOUNT_ICON.getName()).append(", ").append(Columns.ACCOUNT_USER_NAME.getName()).append(", ").append(Columns.ACCOUNT_PASSWORD.getName()).append(", ").append(Columns.ACCOUNT_2STEP.getName()).append(", ").append(Columns.ACCOUNT_RECOVERY_EMAIL.getName()).append(", ").append(Columns.ACCOUNT_PHONE.getName()).append(", ").append(Columns.ACCOUNT_KEY_PHASE.getName()).append(", ").append(Columns.ACCOUNT_DESCRIPTION.getName()).append(" ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)").toString();
        }

        static void bindValuesInBulkInsert(SQLiteStatement stmt, ContentValues values) {
            int i = 1;
            String value;
            stmt.bindLong(i++, values.getAsLong(Columns._ID.getName()));
            value = values.getAsString(Columns.ACCOUNT_NAME.getName());
            stmt.bindString(i++, value != null ? value : "");
            value = values.getAsString(Columns.ACCOUNT_ICON.getName());
            stmt.bindString(i++, value != null ? value : "");
            value = values.getAsString(Columns.ACCOUNT_USER_NAME.getName());
            stmt.bindString(i++, value != null ? value : "");
            value = values.getAsString(Columns.ACCOUNT_PASSWORD.getName());
            stmt.bindString(i++, value != null ? value : "");
            value = values.getAsString(Columns.ACCOUNT_2STEP.getName());
            stmt.bindString(i++, value != null ? value : "");
            value = values.getAsString(Columns.ACCOUNT_RECOVERY_EMAIL.getName());
            stmt.bindString(i++, value != null ? value : "");
            value = values.getAsString(Columns.ACCOUNT_PHONE.getName());
            stmt.bindString(i++, value != null ? value : "");
            value = values.getAsString(Columns.ACCOUNT_KEY_PHASE.getName());
            stmt.bindString(i++, value != null ? value : "");
            value = values.getAsString(Columns.ACCOUNT_DESCRIPTION.getName());
            stmt.bindString(i++, value != null ? value : "");
        }
    }
}

