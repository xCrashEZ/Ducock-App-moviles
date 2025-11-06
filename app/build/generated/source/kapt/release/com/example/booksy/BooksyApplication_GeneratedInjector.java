package com.example.booksy;

import dagger.hilt.InstallIn;
import dagger.hilt.codegen.OriginatingElement;
import dagger.hilt.components.SingletonComponent;
import dagger.hilt.internal.GeneratedEntryPoint;

@OriginatingElement(
    topLevelClass = BooksyApplication.class
)
@GeneratedEntryPoint
@InstallIn(SingletonComponent.class)
public interface BooksyApplication_GeneratedInjector {
  void injectBooksyApplication(BooksyApplication booksyApplication);
}
