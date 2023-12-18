package com.fsc.external.portal.mappers.person;

import com.fsc.common.addon.entity.Address;
import com.fsc.common.addon.entity.Person;
import com.fsc.common.addon.entity.PersonContact;
import com.fsc.common.addon.entity.enums.PersonContactType;
import com.fsc.external.portal.dtos.person.PhysicalPersonDataResponse;
import com.fsc.external.portal.dtos.person.PhysicalPersonProfileResponse;
import com.fsc.external.portal.mappers.BasePageResponseMapper;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.HashSet;
import java.util.Set;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class PhysicalPersonProfileMapper  implements BasePageResponseMapper<Person, PhysicalPersonProfileResponse> {

    @Mapping(target = "personFirstName", source = "person.firstName")
    @Mapping(target = "personMiddleName", source = "person.middleName")
    @Mapping(target = "personLastName", source = "person.lastName")
    @Mapping(target = "egn", source = "person.eikEgn")
    @Mapping(target = "phoneResponse", source = "person.contacts", qualifiedByName = "mapPhone")
    @Mapping(target = "emailResponse", source = "person.contacts", qualifiedByName = "mapEmail")
    @Mapping(target = "addressResponse", source = "person.addresses", qualifiedByName = "mapAddress")
    public abstract PhysicalPersonProfileResponse map(Person person);

    @Named("mapEmail")
    Set<PhysicalPersonDataResponse> mapEmail(Set<PersonContact> emails) {

        return getPhysicalPersonDataResponses(emails, PersonContactType.EMAIL);
    }

    @Named("mapPhone")
    Set<PhysicalPersonDataResponse> mapPhone(Set<PersonContact> phones) {

        return getPhysicalPersonDataResponses(phones, PersonContactType.PHONE);
    }

    private Set<PhysicalPersonDataResponse> getPhysicalPersonDataResponses(Set<PersonContact> contacts,
                                                                                  PersonContactType contactType) {

        Set<PhysicalPersonDataResponse> set = new HashSet<>();

        for (PersonContact contact : contacts) {
            if (contactType.equals(contact.getType())) {
                PhysicalPersonDataResponse contactResponse = new PhysicalPersonDataResponse();
                contactResponse.setId(contact.getId());
                contactResponse.setValue(contact.getValue());
                set.add(contactResponse);
            }
        }
        return set;
    }

    @Named("mapAddress")
    Set<PhysicalPersonDataResponse> mapAddress(Set<Address> addresses) {

        Set<PhysicalPersonDataResponse> addressSet = new HashSet<>();

        for (Address address : addresses) {
            PhysicalPersonDataResponse addressResponse = new PhysicalPersonDataResponse();
            addressResponse.setId(address.getId());
            addressResponse.setValue(address.getAddress());
            addressSet.add(addressResponse);
        }
        return addressSet;
    }
}