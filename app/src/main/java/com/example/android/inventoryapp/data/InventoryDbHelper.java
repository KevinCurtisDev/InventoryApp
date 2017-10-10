package com.example.android.inventoryapp.data;

/**
 * Created by kevincurtis on 26/07/2017.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.android.inventoryapp.data.InventoryContract.InventoryEntry;

/**
 * Database helper for Inventory app. Manages database creation and version management.
 */
public class InventoryDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "Inventory.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String INT_TYPE = " INTEGER";
    private static final String REAL_TYPE = " REAL";
    private static final String BLOB_TYPE = " BLOB";

    private static final String SQL_CREATE_PRODUCTS_TABLE =
            "CREATE TABLE " + InventoryContract.InventoryEntry.TABLE_NAME + " (" +
                    InventoryEntry._ID + INT_TYPE + " PRIMARY KEY AUTOINCREMENT," +
                    InventoryEntry.COLUMN_PRODUCT_NAME + TEXT_TYPE + " NOT NULL,"  +
                    InventoryEntry.COLUMN_PRODUCT_COUNT + INT_TYPE + " NOT NULL DEFAULT 0," +
                    InventoryEntry.COLUMN_PRODUCT_PRICE + REAL_TYPE + " NOT NULL DEFAULT 0.00," +
                    InventoryEntry.COLUMN_PRODUCT_IMAGE + BLOB_TYPE +
                    ")";

    private static final String SQL_DELETE_PRODUCTS_TABLE =
            "DROP TABLE IF EXISTS " + InventoryContract.InventoryEntry.TABLE_NAME;

    public InventoryDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_PRODUCTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if( oldVersion != newVersion) {
            db.execSQL(SQL_DELETE_PRODUCTS_TABLE);
            onCreate(db);
        }
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}