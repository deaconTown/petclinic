package com.company.clinic.listeners;

import com.company.clinic.entity.Veterinarian;
import com.company.clinic.entity.Visit;
import com.haulmont.cuba.core.app.EmailService;
import com.haulmont.cuba.core.app.events.AttributeChanges;
import com.haulmont.cuba.core.app.events.EntityChangedEvent;
import com.haulmont.cuba.core.entity.contracts.Id;
import com.haulmont.cuba.core.global.DataManager;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.inject.Inject;
import java.util.UUID;

@Component("clinic_VisitChangedListener")
public class VisitChangedListener {

    @Inject
    private DataManager dataManager;
    @Inject
    private EmailService emailService;
    @Inject
    private Logger log;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void afterCommit(EntityChangedEvent<Visit, UUID> event) {
        AttributeChanges changes = event.getChanges();
        if(changes.isChanged("date")){
            Id<Visit, UUID> entityId = event.getEntityId();
            Visit visit = dataManager.load(entityId).view("visit-browse").one();
            sendNotificationEmail(visit.getVeterinarian());
        }
    }

    private void sendNotificationEmail(Veterinarian veterinarian) {
        String email = veterinarian.getUser().getEmail();
        log.warn("Visit date was changed. Sending email to {}", email);
//        emailService.sendEmail();
    }
}