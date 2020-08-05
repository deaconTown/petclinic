package com.company.clinic.web.screens.visit;

import com.company.clinic.entity.Consumable;
import com.company.clinic.service.VisitService;
import com.haulmont.cuba.gui.components.HasValue;
import com.haulmont.cuba.gui.model.CollectionChangeType;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.screen.*;
import com.company.clinic.entity.Visit;

import javax.inject.Inject;
import java.math.BigDecimal;

@UiController("clinic_Visit.edit")
@UiDescriptor("visit-edit.xml")
@EditedEntityContainer("visitDc")
@LoadDataBeforeShow
public class VisitEdit extends StandardEditor<Visit> {

    @Inject
    private VisitService visitService;

    @Subscribe("hoursSpentField")
    public void onHoursSpentFieldValueChange(HasValue.ValueChangeEvent<Integer> event) {
        refreshAmount();
    }

    @Subscribe(id = "consumablesDc", target = Target.DATA_CONTAINER)
    public void onConsumablesDcCollectionChange(CollectionContainer.CollectionChangeEvent<Consumable> event) {
        if(event.getChangeType() != CollectionChangeType.REFRESH){
            refreshAmount();
        }
    }

    private  void refreshAmount(){
        Visit visit = getEditedEntity();
        visit.setAmount(visitService.calculateAmount(visit));
    }
}