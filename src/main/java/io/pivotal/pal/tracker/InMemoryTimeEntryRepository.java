package io.pivotal.pal.tracker;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InMemoryTimeEntryRepository implements TimeEntryRepository{

    Map<Long,TimeEntry> inMemory = new HashMap<Long,TimeEntry>();

    private long timeEntryId=0L;
    public TimeEntry create(TimeEntry timeEntry){
        TimeEntry  timeEntry1 = new TimeEntry(++timeEntryId, timeEntry.getProjectId(), timeEntry.getUserId(), timeEntry.getDate(), timeEntry.getHours());
        inMemory.put(timeEntry1.getId(),timeEntry1);
        return timeEntry1;
    }

    public TimeEntry find(long timeEntryId){
        return inMemory.get(timeEntryId);
    }

    public List<TimeEntry> list(){
        List<TimeEntry> timeEntryList = new ArrayList<>();

        for(Long id : inMemory.keySet()){
            TimeEntry timeEntry = inMemory.get(id);
            if(timeEntry !=null){
                timeEntryList.add(timeEntry);
            }
        }

        return timeEntryList;
    }

    public TimeEntry update(long id, TimeEntry timeEntry) {
        TimeEntry  timeEntry1 = inMemory.get(id);

        if(timeEntry1!=null) {
            timeEntry1.setDate(timeEntry.getDate());
            timeEntry1.setHours(timeEntry.getHours());
            timeEntry1.setProjectId(timeEntry.getProjectId());
            timeEntry1.setUserId(timeEntry.getUserId());
        }
        return timeEntry1;
    }

    public void delete(long id) {
        inMemory.remove(id);
    }
}
