@Entity
@Getter
@Setter
@NamedStoredProcedureQuery(
        name = "fullnessFlatDataFunction",
        procedureName = "lkau.fullness_flat_data_function",
        resultClasses = {FullnessFlatDataEntity.class},
        parameters = {
                @StoredProcedureParameter(name = "dev_id", type = Integer.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "bo_id", type = Integer.class, mode = ParameterMode.IN),
        }
)
public class FullnessFlatDataEntity implements Serializable {
    @Id
    private Integer id;
    private Integer percents;
    private BigInteger unfilledFlatDataCount;
}
