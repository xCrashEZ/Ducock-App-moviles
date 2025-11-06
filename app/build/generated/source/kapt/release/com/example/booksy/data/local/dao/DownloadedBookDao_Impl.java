package com.example.booksy.data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.booksy.data.local.entities.DownloadedBookEntity;
import java.lang.Boolean;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@SuppressWarnings({"unchecked", "deprecation"})
public final class DownloadedBookDao_Impl implements DownloadedBookDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<DownloadedBookEntity> __insertionAdapterOfDownloadedBookEntity;

  private final EntityDeletionOrUpdateAdapter<DownloadedBookEntity> __deletionAdapterOfDownloadedBookEntity;

  private final SharedSQLiteStatement __preparedStmtOfClearAllDownloadedBooks;

  public DownloadedBookDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDownloadedBookEntity = new EntityInsertionAdapter<DownloadedBookEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `downloaded_books` (`id`,`title`,`author`,`description`,`coverImage`,`category`,`price`,`rating`,`publicationDate`,`pages`,`downloadDate`,`localFilePath`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final DownloadedBookEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
        if (entity.getTitle() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getTitle());
        }
        if (entity.getAuthor() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getAuthor());
        }
        if (entity.getDescription() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getDescription());
        }
        if (entity.getCoverImage() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getCoverImage());
        }
        if (entity.getCategory() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getCategory());
        }
        statement.bindDouble(7, entity.getPrice());
        statement.bindDouble(8, entity.getRating());
        if (entity.getPublicationDate() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getPublicationDate());
        }
        statement.bindLong(10, entity.getPages());
        statement.bindLong(11, entity.getDownloadDate());
        if (entity.getLocalFilePath() == null) {
          statement.bindNull(12);
        } else {
          statement.bindString(12, entity.getLocalFilePath());
        }
      }
    };
    this.__deletionAdapterOfDownloadedBookEntity = new EntityDeletionOrUpdateAdapter<DownloadedBookEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `downloaded_books` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final DownloadedBookEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
      }
    };
    this.__preparedStmtOfClearAllDownloadedBooks = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM downloaded_books";
        return _query;
      }
    };
  }

  @Override
  public Object insertDownloadedBook(final DownloadedBookEntity book,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfDownloadedBookEntity.insert(book);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteDownloadedBook(final DownloadedBookEntity book,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfDownloadedBookEntity.handle(book);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object clearAllDownloadedBooks(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClearAllDownloadedBooks.acquire();
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfClearAllDownloadedBooks.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<DownloadedBookEntity>> getAllDownloadedBooks() {
    final String _sql = "SELECT * FROM downloaded_books ORDER BY downloadDate DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"downloaded_books"}, new Callable<List<DownloadedBookEntity>>() {
      @Override
      @NonNull
      public List<DownloadedBookEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfAuthor = CursorUtil.getColumnIndexOrThrow(_cursor, "author");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfCoverImage = CursorUtil.getColumnIndexOrThrow(_cursor, "coverImage");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
          final int _cursorIndexOfRating = CursorUtil.getColumnIndexOrThrow(_cursor, "rating");
          final int _cursorIndexOfPublicationDate = CursorUtil.getColumnIndexOrThrow(_cursor, "publicationDate");
          final int _cursorIndexOfPages = CursorUtil.getColumnIndexOrThrow(_cursor, "pages");
          final int _cursorIndexOfDownloadDate = CursorUtil.getColumnIndexOrThrow(_cursor, "downloadDate");
          final int _cursorIndexOfLocalFilePath = CursorUtil.getColumnIndexOrThrow(_cursor, "localFilePath");
          final List<DownloadedBookEntity> _result = new ArrayList<DownloadedBookEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final DownloadedBookEntity _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpAuthor;
            if (_cursor.isNull(_cursorIndexOfAuthor)) {
              _tmpAuthor = null;
            } else {
              _tmpAuthor = _cursor.getString(_cursorIndexOfAuthor);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpCoverImage;
            if (_cursor.isNull(_cursorIndexOfCoverImage)) {
              _tmpCoverImage = null;
            } else {
              _tmpCoverImage = _cursor.getString(_cursorIndexOfCoverImage);
            }
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final double _tmpPrice;
            _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
            final double _tmpRating;
            _tmpRating = _cursor.getDouble(_cursorIndexOfRating);
            final String _tmpPublicationDate;
            if (_cursor.isNull(_cursorIndexOfPublicationDate)) {
              _tmpPublicationDate = null;
            } else {
              _tmpPublicationDate = _cursor.getString(_cursorIndexOfPublicationDate);
            }
            final int _tmpPages;
            _tmpPages = _cursor.getInt(_cursorIndexOfPages);
            final long _tmpDownloadDate;
            _tmpDownloadDate = _cursor.getLong(_cursorIndexOfDownloadDate);
            final String _tmpLocalFilePath;
            if (_cursor.isNull(_cursorIndexOfLocalFilePath)) {
              _tmpLocalFilePath = null;
            } else {
              _tmpLocalFilePath = _cursor.getString(_cursorIndexOfLocalFilePath);
            }
            _item = new DownloadedBookEntity(_tmpId,_tmpTitle,_tmpAuthor,_tmpDescription,_tmpCoverImage,_tmpCategory,_tmpPrice,_tmpRating,_tmpPublicationDate,_tmpPages,_tmpDownloadDate,_tmpLocalFilePath);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getDownloadedBookById(final String bookId,
      final Continuation<? super DownloadedBookEntity> $completion) {
    final String _sql = "SELECT * FROM downloaded_books WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (bookId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, bookId);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<DownloadedBookEntity>() {
      @Override
      @Nullable
      public DownloadedBookEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfAuthor = CursorUtil.getColumnIndexOrThrow(_cursor, "author");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfCoverImage = CursorUtil.getColumnIndexOrThrow(_cursor, "coverImage");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
          final int _cursorIndexOfRating = CursorUtil.getColumnIndexOrThrow(_cursor, "rating");
          final int _cursorIndexOfPublicationDate = CursorUtil.getColumnIndexOrThrow(_cursor, "publicationDate");
          final int _cursorIndexOfPages = CursorUtil.getColumnIndexOrThrow(_cursor, "pages");
          final int _cursorIndexOfDownloadDate = CursorUtil.getColumnIndexOrThrow(_cursor, "downloadDate");
          final int _cursorIndexOfLocalFilePath = CursorUtil.getColumnIndexOrThrow(_cursor, "localFilePath");
          final DownloadedBookEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpAuthor;
            if (_cursor.isNull(_cursorIndexOfAuthor)) {
              _tmpAuthor = null;
            } else {
              _tmpAuthor = _cursor.getString(_cursorIndexOfAuthor);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpCoverImage;
            if (_cursor.isNull(_cursorIndexOfCoverImage)) {
              _tmpCoverImage = null;
            } else {
              _tmpCoverImage = _cursor.getString(_cursorIndexOfCoverImage);
            }
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final double _tmpPrice;
            _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
            final double _tmpRating;
            _tmpRating = _cursor.getDouble(_cursorIndexOfRating);
            final String _tmpPublicationDate;
            if (_cursor.isNull(_cursorIndexOfPublicationDate)) {
              _tmpPublicationDate = null;
            } else {
              _tmpPublicationDate = _cursor.getString(_cursorIndexOfPublicationDate);
            }
            final int _tmpPages;
            _tmpPages = _cursor.getInt(_cursorIndexOfPages);
            final long _tmpDownloadDate;
            _tmpDownloadDate = _cursor.getLong(_cursorIndexOfDownloadDate);
            final String _tmpLocalFilePath;
            if (_cursor.isNull(_cursorIndexOfLocalFilePath)) {
              _tmpLocalFilePath = null;
            } else {
              _tmpLocalFilePath = _cursor.getString(_cursorIndexOfLocalFilePath);
            }
            _result = new DownloadedBookEntity(_tmpId,_tmpTitle,_tmpAuthor,_tmpDescription,_tmpCoverImage,_tmpCategory,_tmpPrice,_tmpRating,_tmpPublicationDate,_tmpPages,_tmpDownloadDate,_tmpLocalFilePath);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object isBookDownloaded(final String bookId,
      final Continuation<? super Boolean> $completion) {
    final String _sql = "SELECT EXISTS(SELECT 1 FROM downloaded_books WHERE id = ?)";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (bookId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, bookId);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Boolean>() {
      @Override
      @NonNull
      public Boolean call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Boolean _result;
          if (_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp == null ? null : _tmp != 0;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getDownloadedBooksCount(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM downloaded_books";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
