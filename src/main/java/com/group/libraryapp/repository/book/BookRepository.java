package com.group.libraryapp.repository.book;

import com.group.libraryapp.domain.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface BookRepository extends JpaRepository<Book,Long> {

    void saveBook();


}
