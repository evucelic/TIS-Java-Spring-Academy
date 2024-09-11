package hr.tis.academy.repository;

import hr.tis.academy.model.Journal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalRepository extends JpaRepository<Journal, Long> {
    Journal findByJournalId(Long journalId);

}
