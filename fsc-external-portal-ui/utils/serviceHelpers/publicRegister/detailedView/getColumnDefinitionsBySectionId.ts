import { PublicRegisterDetailsSectionColumnDefinition } from '../../../../contracts/types/publicRegisterPnl/details/dataGrid';

const getColumnDefinitionsBySectionId = (
  sectionId: number
): PublicRegisterDetailsSectionColumnDefinition[] => {
  switch (sectionId) {
    case 10:
      return [
        { label: 'mailingAddress', order: 1 },
        { label: 'phone', order: 2 },
        { label: 'fax', order: 3 },
        { label: 'email', order: 4 },
        { label: 'website', order: 5 },
      ];
    case 20:
      return [];
    case 30:
      return [
        { label: 'role', order: 1 },
        { label: 'personName', order: 2 },
        { label: 'regOfficeAddress', order: 3 },
        { label: 'managementAddress', order: 4 },
        { label: 'mailingAddress', order: 5 },
      ];
    case 40:
      return [
        { label: 'capital', order: 1 },
        { label: 'assetType', order: 2 },
        { label: 'emissionValues', order: 3 },
        { label: 'assetCount', order: 4 },
        { label: 'assetNominal', order: 5 },
      ];
    case 50:
      return [
        { tableName: 'legalPersons', label: 'personName', order: 1 },
        { tableName: 'legalPersons', label: 'eik', order: 2 },
        { tableName: 'legalPersons', label: 'regOfficeAddress', order: 3 },
        { tableName: 'legalPersons', label: 'managementAddress', order: 4 },
        { tableName: 'legalPersons', label: 'activity', order: 5 },

        { tableName: 'individualPersons', label: 'personName', order: 1 },
      ];
    case 60:
      return [
        { tableName: 'noTable', label: 'wayOfRepresentative', order: 1 },

        { tableName: 'respresentativeList', label: 'personName', order: 1 },

        { tableName: 'bordOfDirectorList', label: 'role', order: 1 },
        { tableName: 'bordOfDirectorList', label: 'personTypeName', order: 2 },
        { tableName: 'bordOfDirectorList', label: 'personName', order: 3 },
        { tableName: 'bordOfDirectorList', label: 'eik', order: 4 },
        {
          tableName: 'bordOfDirectorList',
          label: 'personRepresentative',
          order: 5,
        },
        {
          tableName: 'bordOfDirectorList',
          label: 'regOfficeAddress',
          order: 6,
        },
        {
          tableName: 'bordOfDirectorList',
          label: 'managementAddress',
          order: 7,
        },

        { tableName: 'directorList', label: 'role', order: 1 },
        { tableName: 'directorList', label: 'personTypeName', order: 2 },
        { tableName: 'directorList', label: 'personName', order: 3 },
        { tableName: 'directorList', label: 'eik', order: 4 },
        { tableName: 'directorList', label: 'personRepresentative', order: 5 },
        { tableName: 'directorList', label: 'regOfficeAddress', order: 6 },
        { tableName: 'directorList', label: 'managementAddress', order: 7 },

        { tableName: 'supervisoryList', label: 'role', order: 1 },
        { tableName: 'supervisoryList', label: 'personTypeName', order: 2 },
        { tableName: 'supervisoryList', label: 'personName', order: 3 },
        { tableName: 'supervisoryList', label: 'eik', order: 4 },
        {
          tableName: 'supervisoryList',
          label: 'personRepresentative',
          order: 5,
        },
        { tableName: 'supervisoryList', label: 'regOfficeAddress', order: 6 },
        { tableName: 'supervisoryList', label: 'managementAddress', order: 7 },
      ];
    case 70:
      return [];
    case 80:
      return [];
    case 90:
      return [];
    case 100:
      return [];
    case 110:
      return [];
    case 120:
      return [];
    case 130:
      return [];
    case 140:
      return [
        { label: 'pnlName', order: 1 },
        { label: 'eikEgn', order: 2 },
        { label: 'regOfficeAddress', order: 3 },
        { label: 'managementAddress', order: 4 },
      ];
    case 150:
      return [
        { label: 'pnlName', order: 1 },
        { label: 'eikEgn', order: 2 },
        { label: 'regOfficeAddress', order: 3 },
        { label: 'managementAddress', order: 4 },
      ];
    case 160:
      return [
        { label: 'pnlName', order: 1 },
        { label: 'eikEgn', order: 2 },
        { label: 'regOfficeAddress', order: 3 },
        { label: 'managementAddress', order: 4 },
      ];
    case 170:
      return [
        { label: 'pnlName', order: 1 },
        { label: 'eikEgn', order: 2 },
        { label: 'regOfficeAddress', order: 3 },
        { label: 'managementAddress', order: 4 },
      ];
    case 180:
      return [
        { label: 'pnlName', order: 1 },
        { label: 'eikEgn', order: 2 },
        { label: 'regOfficeAddress', order: 3 },
        { label: 'managementAddress', order: 4 },
      ];
    case 190:
      return [
        { label: 'pnlName', order: 1 },
        { label: 'eikEgn', order: 2 },
        { label: 'regOfficeAddress', order: 3 },
        { label: 'managementAddress', order: 4 },
      ];
    case 200:
      return [
        { label: 'pnlName', order: 1 },
        { label: 'eikEgn', order: 2 },
        { label: 'regOfficeAddress', order: 3 },
        { label: 'managementAddress', order: 4 },
      ];
    case 210:
      return [
        { label: 'pnlName', order: 1 },
        { label: 'eikEgn', order: 2 },
        { label: 'regOfficeAddress', order: 3 },
        { label: 'managementAddress', order: 4 },
      ];
    case 220:
      return [];
    case 230:
      return [
        { label: 'pnlName', order: 1 },
        { label: 'eikEgn', order: 2 },
        { label: 'regOfficeAddress', order: 3 },
        { label: 'managementAddress', order: 4 },
      ];
    case 240:
      return [{ label: 'personName', order: 1 }];
    case 250:
      return [
        { tableName: 'personList', label: 'pnlName', order: 1 },
        { tableName: 'personList', label: 'eik', order: 2 },
        { tableName: 'personList', label: 'regOfficeAddress', order: 3 },
        { tableName: 'personList', label: 'managementAddress', order: 4 },

        { tableName: 'documentsList', label: 'documentType', order: 1 },
        { tableName: 'documentsList', label: 'documentNumber', order: 2 },
        { tableName: 'documentsList', label: 'documentFile', order: 3 },
        { tableName: 'documentsList', label: 'documentText', order: 4 },
      ];
    case 260:
      return [
        { label: 'role', order: 1 },
        { label: 'personName', order: 2 },
      ];
    case 270:
      return [
        { label: 'pnlName', order: 1 },
        { label: 'eikEgn', order: 2 },
        { label: 'regOfficeAddress', order: 3 },
        { label: 'managementAddress', order: 4 },
        { label: 'activity', order: 5 },
      ];
    case 280:
      return [
        { tableName: 'legalPersons', label: 'personName', order: 1 },
        { tableName: 'legalPersons', label: 'eik', order: 2 },
        { tableName: 'legalPersons', label: 'regOfficeAddress', order: 3 },
        { tableName: 'legalPersons', label: 'managementAddress', order: 4 },
        { tableName: 'legalPersons', label: 'activity', order: 5 },

        { tableName: 'individualPersons', label: 'personName', order: 1 },
      ];
    case 290:
      return [];
    case 300:
      return [];
    case 310:
      return [];
    case 320:
      return [
        { tableName: 'statusList', label: 'publicRegisterName', order: 1 },

        { tableName: 'documentsList', label: 'documentType', order: 1 },
        { tableName: 'documentsList', label: 'documentNumber', order: 2 },
        { tableName: 'documentsList', label: 'documentFile', order: 3 },
        { tableName: 'documentsList', label: 'documentText', order: 4 },
      ];
    case 330:
      return [
        { label: 'personName', order: 1 },
        { label: 'eik', order: 2 },
        { label: 'regOfficeAddress', order: 3 },
        { label: 'managementAddress', order: 4 },
        { label: 'insContract', order: 5 },
      ];

    default: {
      return [];
    }
  }
};

export default getColumnDefinitionsBySectionId;
