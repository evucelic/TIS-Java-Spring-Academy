package hr.tis.academy.service.impl;

import hr.tis.academy.dto.JournalAtrReviewDto;
import hr.tis.academy.dto.JournalDto;
import hr.tis.academy.model.Journal;
import hr.tis.academy.model.JournalAtrReview;
import hr.tis.academy.repository.JournalRepository;
import hr.tis.academy.repository.UserRepository;
import hr.tis.academy.repository.exception.JournalNotFoundException;
import hr.tis.academy.service.JournalService;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class JournalServiceImpl implements JournalService {

    private final JournalRepository journalRepository;

    private final UserRepository userRepository;

    public JournalServiceImpl(JournalRepository journalRepository, UserRepository userRepository) {
        this.journalRepository = journalRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void addJournal(JournalDto journalDto, Long userId) {
        List<JournalAtrReview> journalAtrReviewList = new ArrayList<>();

        List<JournalAtrReviewDto> journalAtrReviewDtoList = journalDto.attractions();
        for (JournalAtrReviewDto journalAtrReviewDto : journalAtrReviewDtoList) {
            JournalAtrReview journalAtrReview = new JournalAtrReview(
                    journalAtrReviewDto.attractionLocationName(),
                    journalAtrReviewDto.attractionName(),
                    journalAtrReviewDto.attractionComment(),
                    Date.valueOf(journalAtrReviewDto.attractionDate())

            );
            journalAtrReviewList.add(journalAtrReview);

            Journal journal = new Journal(Date.valueOf(journalDto.startDate()),
                    Date.valueOf(journalDto.endDate()),
                    journalDto.description(),
                    userRepository.findByUserId(userId),
                    journalAtrReviewList
            );
            journalRepository.save(journal);
        }
    }



    @Override
    public JournalDto getJournal(Long id) {
        Journal journal = journalRepository.findByJournalId(id);
        if (journal == null) {
            throw new JournalNotFoundException("Journal with ID " + id + " does not exist");
        }
        List<JournalAtrReview> journalAtrReviewList = journal.getJournalsAtrReviews();
        List<JournalAtrReviewDto> journalAtrReviewDtoList = new ArrayList<>();

        for (JournalAtrReview journalAtrReview : journalAtrReviewList) {
            JournalAtrReviewDto journalAtrReviewDto = new JournalAtrReviewDto(
                    journalAtrReview.getAttractionLocationName(),
                    journalAtrReview.getAttractionName(),
                    journalAtrReview.getAttractionComment(),
                    journalAtrReview.getAttractionDate().toString()
            );
            journalAtrReviewDtoList.add(journalAtrReviewDto);
        }
        return new JournalDto(
                journal.getStartDate().toString(),
                journal.getEndDate().toString(),
                journal.getJournalDescription(),
                journalAtrReviewDtoList
        );
    }

    @Override
    public void modifyJournal(Long journalId, JournalDto journalDto) {
        Journal journal = journalRepository.findByJournalId(journalId);
        if (journal == null) {
            throw new JournalNotFoundException("Journal with ID " + journalId + " does not exist");
        }
        List<JournalAtrReview> existingReviews = journal.getJournalsAtrReviews();
        if (journalDto.endDate() != null) {
            journal.setEndDate(Date.valueOf(journalDto.endDate()));
        }
        if (journalDto.description() != null) {
            journal.setJournalDescription(journalDto.description());
        }
        if (journalDto.attractions() != null) {List<JournalAtrReviewDto> journalAtrReviewModiyList = journalDto.attractions();
            for (JournalAtrReviewDto journalAtrReviewDto : journalAtrReviewModiyList) {
                JournalAtrReview journalAtrReview = new JournalAtrReview(
                        journalAtrReviewDto.attractionLocationName(),
                        journalAtrReviewDto.attractionName(),
                        journalAtrReviewDto.attractionComment(),
                        Date.valueOf(journalAtrReviewDto.attractionDate()),
                        journal
                );
                existingReviews.add(journalAtrReview);
            }
            journal.setJournalsAtrReviews(existingReviews);
        }
    }
}
