using System.Runtime.Serialization;

namespace FscIntegrationSeosServiceModels.Enums
{
    [DataContract(Name = "DocumentStatusType", Namespace = "http://schemas.egov.bg/messaging/v1")]
    public enum DocumentStatusType
    {
        [EnumMember()]
        DS_WAIT_REGISTRATION,

        [EnumMember()]
        DS_REGISTERED,

        [EnumMember()]
        DS_STOPPED,

        [EnumMember()]
        DS_CLOSED,

        [EnumMember()]
        DS_NOT_FOUND,

        [EnumMember()]
        DS_ALREADY_RECEIVED,

        [EnumMember()]
        DS_REJECTED,

        [EnumMember()]
        DS_TRY_SEND,

        [EnumMember()]
        DS_SENT,

        [EnumMember()]
        DS_SENT_FAILED
    }
}
