package com.example.helloworld

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.net.Uri
import android.util.Log
import com.example.helloworld.database.MyDatabaseHelper

class MyContentProvider : ContentProvider() {

    private val bookDir = 0
    private val bookItem = 1
    private val categoryDir = 2
    private val categoryItem = 3

    private val authority = "com.example.helloworld.provider"
    private lateinit var dbHelpers: MyDatabaseHelper

   // private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

//    init {
//
//        uriMatcher.addURI(authority, "book", bookDir)
//        uriMatcher.addURI(authority, "book/#", bookItem)
//        uriMatcher.addURI(authority, "category", categoryDir)
//        uriMatcher.addURI(authority, "category/#", categoryItem)
//    }

    private val uriMatcher by lazy {
        Log.e("qinhouliu", "uriMatcher 初始化成功")
        val matcher = UriMatcher(UriMatcher.NO_MATCH)
        matcher.addURI(authority, "book", bookDir)
        matcher.addURI(authority, "book/#", bookItem)
        matcher.addURI(authority, "category", categoryDir)
        matcher.addURI(authority, "category/#", categoryItem)
        matcher
    }


    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int = dbHelpers?.let {
        // 删除数据
        val db = it.writableDatabase
        val deletedRows = when (uriMatcher.match(uri)) {
            bookDir -> db.delete("Book", selection, selectionArgs)
            bookItem -> {
                val bookId = uri.pathSegments[1]
                db.delete("Book", "id = ?", arrayOf(bookId))
            }
            categoryDir -> db.delete("Category", selection, selectionArgs)
            categoryItem -> {
                val categoryId = uri.pathSegments[1]
                db.delete("Category", "id = ?", arrayOf(categoryId))
            }
            else -> 0
        }
        deletedRows
    } ?: 0

    override fun getType(uri: Uri): String? {
        val type = when (uriMatcher.match(uri)) {
            bookDir -> "vnd.android.cursor.dir/vnd.$authority.book"
            bookItem -> "vnd.android.cursor.item/vnd.$authority.book"
            categoryDir -> "vnd.android.cursor.dir/vnd.$authority.category"
            categoryItem -> "vnd.android.cursor.item/vnd.$authority.category"
            else -> null
        }
        return type
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri?  = dbHelpers?.let {
        // 添加数据
        val db = it.writableDatabase
        val uriReturn = when (uriMatcher.match(uri)) {
            bookDir, bookItem -> {
                val newBookId = db.insert("book", null, values)
                Uri.parse("content://$authority/book/$newBookId")
            }
            categoryDir, categoryItem -> {
                val newCategoryId = db.insert("category", null, values)
                Uri.parse("content://$authority/category/$newCategoryId")
            }
            else -> null
        }
        uriReturn
    }

    override fun onCreate(): Boolean {
        if (context != null) {
            dbHelpers = MyDatabaseHelper(context!!, "BookStore.db", null, 2)
            return true
        }
        return false
    }

//    override fun query(uri: Uri, projection: Array<String>?, selection: String?,
//                       selectionArgs: Array<String>?, sortOrder: String?): Cursor? {
//        val db = dbHelpers.readableDatabase
//        val cursor: Cursor
//        when (uriMatcher.match(uri)) {
//            bookDir -> cursor = db.query("Book", projection, selection, selectionArgs, null, null, sortOrder)
//            bookItem -> {
//                val bookId = uri.pathSegments[1]
//                cursor = db.query("Book", projection, "id = ?", arrayOf(bookId), null, null, sortOrder)
//            }
//            categoryDir -> cursor = db.query("Category", projection, selection, selectionArgs, null, null, sortOrder)
//            categoryItem -> {
//                val categoryId = uri.pathSegments[1]
//                cursor = db.query("Category", projection, "id = ?", arrayOf(categoryId), null, null, sortOrder)
//            }
//        }
//        return cursor
//    }

    override fun query(uri: Uri, projection: Array<String>?, selection: String?,
                       selectionArgs: Array<String>?, sortOrder: String?) = dbHelpers?.let {
        // 查询数据
        val db = it.readableDatabase
        Log.e("qinhouliu", "start query in contentprovider")
        val cursor = when (uriMatcher.match(uri)) {
            bookDir -> db.query("book", projection, selection, selectionArgs,
                    null, null, sortOrder)
            bookItem -> {
                val bookId = uri.pathSegments[1]
                db.query("book", projection, "id = ?", arrayOf(bookId), null, null,
                        sortOrder)
            }
            categoryDir -> db.query("category", projection, selection, selectionArgs,
                    null, null, sortOrder)
            categoryItem -> {
                val categoryId = uri.pathSegments[1]
                db.query("category", projection, "id = ?", arrayOf(categoryId),
                        null, null, sortOrder)
            }
            else -> null
        }
        cursor
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?,
                        selectionArgs: Array<String>?): Int  = dbHelpers?.let {
        // 更新数据
        val db = it.writableDatabase
        val updatedRows = when (uriMatcher.match(uri)) {
            bookDir -> db.update("book", values, selection, selectionArgs)
            bookItem -> {
                val bookId = uri.pathSegments[1]
                db.update("book", values, "id = ?", arrayOf(bookId))
            }
            categoryDir -> db.update("category", values, selection, selectionArgs)
            categoryItem -> {
                val categoryId = uri.pathSegments[1]
                db.update("category", values, "id = ?", arrayOf(categoryId))
            }
            else -> 0
        }
        updatedRows
    } ?: 0
}