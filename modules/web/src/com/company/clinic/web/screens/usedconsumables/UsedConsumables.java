package com.company.clinic.web.screens.usedconsumables;

import com.company.clinic.entity.Consumable;
import com.company.clinic.service.ConsumablesService;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.screen.*;

import javax.inject.Inject;
import java.util.List;

@UiController("clinic_UsedConsumables")
@UiDescriptor("used-consumables.xml")
@LoadDataBeforeShow
public class UsedConsumables extends Screen {

    @Inject
    private ConsumablesService consumablesService;
    @Inject
    private CollectionContainer<Consumable> consumablesDc;

    @Install(to = "consumablesDl", target = Target.DATA_LOADER)
    private List<Consumable> consumablesDlLoadDelegate(LoadContext<Consumable> loadContext) {
        return consumablesService.getUsedConsumables();
    }


}