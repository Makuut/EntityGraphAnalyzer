@Entity
@Getter
@Setter
@Table(name = "region", schema = "nsi")
@NoArgsConstructor
@Audited
public class RegionEntity extends RdmRefBookEntity {
    @Column(name = "short_name")
    private String shortName;
    @Column(name = "code_fias")
    private String codeFias;
    @Column(name = "code_okato")
    private String codeOkato;
    @Column(name = "code_oktmo")
    private String codeOktmo;
    @Column(name = "code_fo")
    private Integer codeFo;
    @Column(name = "short_name_fo")
    private String shortNameFo;
    @Column(name = "name_fo")
    private String nameFo;
}
