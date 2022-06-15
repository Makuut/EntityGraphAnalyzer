@Entity
public class FlatographyCorrectionEntity extends BaseEntity<Integer> {

    private Integer id;

    private FlatographyEntity flatography;

    private Integer version;

    private String correction;
}
