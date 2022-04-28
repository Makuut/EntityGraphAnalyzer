@Entity
@Getter
@Setter
@Table(name = "country", schema = "nsi")
@NoArgsConstructor
@Audited
public class CountryEntity extends RdmRefBookEntity {

    @Column(name = "short_name")
    private String shortName;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "alfa2")
    private String alfa2;

    @Column(name = "alfa3")
    private String alfa3;
}
