package com.company.clinic.listeners;

import com.company.clinic.entity.Visit;
import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.app.UniqueNumbersAPI;
import com.haulmont.cuba.core.listener.BeforeInsertEntityListener;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component("clinic_VisitEntityListener")
public class VisitEntityListener implements BeforeInsertEntityListener<Visit> {

    @Inject
    private UniqueNumbersAPI uniqueNumbersAPI;

    @Override
    public void onBeforeInsert(Visit entity, EntityManager entityManager) {
        long visitNumber = uniqueNumbersAPI.getNextNumber("VISIT_NUMBER");
        entity.setNumber(visitNumber);
    }
}
