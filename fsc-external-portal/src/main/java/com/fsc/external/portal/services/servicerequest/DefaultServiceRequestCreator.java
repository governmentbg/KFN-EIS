package com.fsc.external.portal.services.servicerequest;

import com.fsc.common.addon.entity.Person;
import com.fsc.common.addon.entity.PnlPerson;
import com.fsc.common.addon.entity.User;
import com.fsc.common.addon.entity.catalog.service.ServiceRequest;
import com.fsc.common.addon.service.person.PersonService;
import com.fsc.exception.library.exception.RecordNotFoundException;
import com.fsc.exception.library.exception.enums.ErrorDefinition;
import io.jmix.core.querycondition.LogicalCondition;
import io.jmix.core.querycondition.PropertyCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;
import java.util.Optional;

public class DefaultServiceRequestCreator extends CommonServiceRequestCreator {

    @Autowired
    private ServiceRequestService serviceRequestService;

    @Autowired
    private PersonService personService;

    @Override
    protected void deleteExistingDraft(Long catalogId, Long pnlId, Long personId) {

        Optional<ServiceRequest> existingServiceRequestId =
                serviceRequestService.checkIfDraftServiceRequestExist(catalogId, pnlId, personId);

        if (existingServiceRequestId.isPresent()) {
            //throw new ResponseStatusException(HttpStatus.CONFLICT); TODO: remove delete and uncomment this line
            serviceRequestService.delete(existingServiceRequestId.get().getId());
        }
    }

    @Override
    protected void checkIfUserManagePnl(User user, Long pnlId, Long personId) {

        final Person person = personService.getPersonWithManagedPnl(user, pnlId, personId);

        if (CollectionUtils.isEmpty(person.getPnlPerson())) {
            throw new RecordNotFoundException(ErrorDefinition.RECORD_NOT_FOUND);  // TODO
        }
    }

    @Override
    protected PnlPerson getPnlPerson(Long pnlId, Long personId, User user) {

        PnlPerson pnlPerson = null;
        if (!Objects.isNull(personId)) {

            LogicalCondition logicalCondition = LogicalCondition.and();
            if (!Objects.isNull(pnlId)) {
                logicalCondition.add(PropertyCondition.equal("pnl.id", pnlId));
            }

            logicalCondition.add(PropertyCondition.equal("mainPerson.id", personId));

            pnlPerson = dataManager.load(PnlPerson.class)
                    .condition(logicalCondition)
                    .optional().orElse(null);
        }

        return pnlPerson;
    }
}