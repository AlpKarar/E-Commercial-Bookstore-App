package dev.alp.TestProject.ECommercialApp.Repository;

import dev.alp.TestProject.ECommercialApp.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
