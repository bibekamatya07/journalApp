package net.bvk.personalapp.service;

import lombok.extern.slf4j.Slf4j;
import net.bvk.personalapp.entity.JournalEntry;
import net.bvk.personalapp.entity.User;
import net.bvk.personalapp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service

public class JournalEntryService  {

    private final JournalEntryRepository journalEntryRepository;
    private final UserService userService;

   private static final Logger logger = LoggerFactory.getLogger(JournalEntryService.class);

    @Autowired
    public JournalEntryService(JournalEntryRepository journalEntryRepository, UserService userService) {
        this.journalEntryRepository = journalEntryRepository;
        this.userService = userService;
    }
    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
        try{
            User user = userService.findByUserName(userName);
            // Date will be set upon entry
            journalEntry.setCreatedDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry); // add to journal_entries collection
            user.getJournalEntries().add(saved); // add to user journalEntries list
            userService.saveUser(user);
        }catch (Exception e){
            logger.error("Exception ", e);
        }
    }
    public void saveEntry(JournalEntry journalEntry) {
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
       return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public boolean deleteById(ObjectId id, String userName){
        boolean removed = false;
        try {
           // this will find the user by the username
           User user = userService.findByUserName(userName);
           // removes the journal entry from the user journal entries list
           removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
           if (removed){
               userService.saveUser(user); // saves the user after journal entry deletion
               journalEntryRepository.deleteById(id); // deletes the journal entry
           }
        }catch (Exception e){
           logger.error("An error occurred while deleting the entry.", e);
        }
        return removed;
    }
}
