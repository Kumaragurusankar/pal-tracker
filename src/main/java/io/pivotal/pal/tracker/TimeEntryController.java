package io.pivotal.pal.tracker;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import io.pivotal.pal.tracker.TimeEntry;
import io.pivotal.pal.tracker.TimeEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.cache.CacheMetricsRegistrar;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryController {

    private TimeEntryRepository timeEntryRepository;

    private final Counter actionCounter;

    private final DistributionSummary timeEntrySummary;

    public TimeEntryController(TimeEntryRepository timeEntryRepository, MeterRegistry meterRegistry) {
        this.timeEntryRepository=timeEntryRepository;
        actionCounter = meterRegistry.counter("timeEntry.actionCounter");
        timeEntrySummary = meterRegistry.summary("timeEntry.summary");
    }


    @PostMapping("/time-entries")
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {
       TimeEntry timeEntry = timeEntryRepository.create(timeEntryToCreate);
        actionCounter.increment();
        return  new ResponseEntity(timeEntry, HttpStatus.CREATED);
    }

    @GetMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable long timeEntryId) {
        TimeEntry timeEntry = timeEntryRepository.find(  timeEntryId);
        actionCounter.increment();
        HttpStatus status = (timeEntry != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return   new ResponseEntity(timeEntry, status);
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> timeEntry = timeEntryRepository.list();
        actionCounter.increment();
        return  new ResponseEntity(timeEntry, HttpStatus.OK);
    }

    @PutMapping("/time-entries/{timeEntryId}")
    public ResponseEntity update(@PathVariable long timeEntryId,@RequestBody TimeEntry expected) {
        TimeEntry timeEntry = timeEntryRepository.update(timeEntryId,expected);
        actionCounter.increment();
        HttpStatus status = (timeEntry != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return   new ResponseEntity(timeEntry, status);
    }

    @DeleteMapping("/time-entries/{timeEntryId}")
    public ResponseEntity delete(@PathVariable long timeEntryId) {
         timeEntryRepository.delete(timeEntryId);
        actionCounter.increment();
        return  new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
