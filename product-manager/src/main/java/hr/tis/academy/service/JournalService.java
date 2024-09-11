package hr.tis.academy.service;

import hr.tis.academy.dto.JournalDto;
import hr.tis.academy.model.Journal;

public interface JournalService {

    void addJournal(JournalDto journalDto, Long userId);

    JournalDto getJournal(Long id);

    void modifyJournal(Long journalId, JournalDto journalDto);

}
