package com.sahana_siva_m1.coachnutrition;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

public class CoachNutContentProvider extends ContentProvider {

    private static final String LOG = "CoachNutContentProvider";
    private BaseCoachNutrition dbhelper;

    private static String authority = "com.sahana_siva_m1.coachnutrition";
    private static final int CODE_ALIMENTS = 1;
    private static final int CODE_DONNEES_NUTRITIONNELLES = 2;
    private static final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        matcher.addURI(authority, "aliments", CODE_ALIMENTS);
        matcher.addURI(authority, "donneesnutritionnelles", CODE_DONNEES_NUTRITIONNELLES);
    }

    public CoachNutContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        int code = matcher.match(uri);
        Log.d(LOG, "Uri=" + uri.toString());
        long id = 0;
        String path;
        switch (code) {
            case CODE_ALIMENTS:
                id = db.insertOrThrow(dbhelper.TABLE_ALIMENTS, null, values);
                path = "aliments";
                break;
            case CODE_DONNEES_NUTRITIONNELLES:
                id = db.insertOrThrow(dbhelper.TABLE_DONNEES_NUTRITIONNELLES, null, values);
                path = "donneesnutritionnelles";
                break;
            default:
                throw new UnsupportedOperationException("this insert not yet implemented");
        }

        Uri.Builder builder = (new Uri.Builder())
                .authority(authority)
                .appendPath(path);

        return ContentUris.appendId(builder, id).build();
    }

    @Override
    public boolean onCreate() {
        dbhelper = BaseCoachNutrition.getInstance(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
