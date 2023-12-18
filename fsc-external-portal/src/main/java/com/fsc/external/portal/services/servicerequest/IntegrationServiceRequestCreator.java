package com.fsc.external.portal.services.servicerequest;

import com.fsc.common.addon.entity.PnlPerson;
import com.fsc.common.addon.entity.User;

public class IntegrationServiceRequestCreator extends CommonServiceRequestCreator {

    @Override
    protected void deleteExistingDraft(Long catalogId, Long pnlId, Long personId) {
    }

    @Override
    protected void checkIfUserManagePnl(User user, Long pnlId, Long personId) {
    }

    @Override
    protected PnlPerson getPnlPerson(Long pnlId, Long personId, User user) {
        return null;  // TODO every system user will manage one PNL (at some point)
    }
}
