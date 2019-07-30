package io.pivotal.pal.tracker;

import io.pivotal.pal.tracker.TimeEntry;
import io.pivotal.pal.tracker.TimeEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryController {

    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository=timeEntryRepository;
    }


    @PostMapping("/time-entries")
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {
       TimeEntry timeEntry = timeEntryRepository.create(timeEntryToCreate);
        return  new ResponseEntity(timeEntry, HttpStatus.CREATED);
    }

    @GetMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable long timeEntryId) {
        TimeEntry timeEntry = timeEntryRepository.find(  timeEntryId);
        HttpStatus status = (timeEntry != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return   new ResponseEntity(timeEntry, status);
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> timeEntry = timeEntryRepository.list();
        return  new ResponseEntity(timeEntry, HttpStatus.OK);
    }

    @PutMapping("/time-entries/{timeEntryId}")
    public ResponseEntity update(@PathVariable long timeEntryId,@RequestBody TimeEntry expected) {
        TimeEntry timeEntry = timeEntryRepository.update(timeEntryId,expected);
        HttpStatus status = (timeEntry != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return   new ResponseEntity(timeEntry, status);
    }

    @DeleteMapping("/time-entries/{timeEntryId}")
    public ResponseEntity delete(@PathVariable long timeEntryId) {
         timeEntryRepository.delete(timeEntryId);
        return  new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
