@Entity
public class ParticipantDemandCorrectionTypeEntity extends BaseEntity<Integer> {

    private Integer id;

    private ParticipantDemandCorrectionEntity participantDemandCorrection;

    private CorrectionTypeEntity correctionType;
}
