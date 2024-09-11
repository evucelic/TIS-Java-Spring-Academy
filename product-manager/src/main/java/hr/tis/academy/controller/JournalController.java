package hr.tis.academy.controller;

import hr.tis.academy.dto.JournalDto;

import hr.tis.academy.service.JournalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/travel-journal")
public class JournalController {

    private final JournalService journalService;

    public JournalController(JournalService journalService) {
        this.journalService = journalService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<String> addJournal(@RequestBody JournalDto journalDto, @PathVariable Long userId) {
        journalService.addJournal(journalDto, userId);
        return ResponseEntity.ok("Journal added to user with id" + userId);
    }

    @GetMapping("/{travelJournalId}")
    public ResponseEntity<JournalDto> getJournal(@PathVariable Long travelJournalId) {
        JournalDto journalDto = journalService.getJournal(travelJournalId);
        return new ResponseEntity<>(journalDto, HttpStatus.OK);
    }




    @PatchMapping("/{travelJournalId}")
    public ResponseEntity<String> modifyJournal(@PathVariable Long travelJournalId,
                                         @RequestBody JournalDto journalDto) {
        journalService.modifyJournal(travelJournalId, journalDto);
        return ResponseEntity.ok("Journal modified with id" + travelJournalId);
    }
}
