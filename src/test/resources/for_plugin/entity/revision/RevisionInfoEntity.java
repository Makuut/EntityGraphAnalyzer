package ru.i_novus.domrf.lkau.entity.revision;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;
import ru.i_novus.domrf.lkau.listener.revision.RevisionInfoListener;

import javax.persistence.*;

/**
 * Ревизия для основных сущностей
 *
 * @author akuznetcov
 **/
@Entity
@Table(name = "revision_info", schema = "revision")
@RevisionEntity(RevisionInfoListener.class)
@Setter
@Getter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RevisionInfoEntity {

    /**
     * Идентификатор ревизии
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "revision_info_seq")
    @SequenceGenerator(name = "revision_info_seq", sequenceName = "revision.revision_info_seq", allocationSize = 1)
    @RevisionNumber
    private Integer revision;

    /**
     * Время изменения
     */
    @Column(name = "revision_tstmp")
    @RevisionTimestamp
    private Long revisionTimestamp;

    /**
     * Автор изменения (логин)
     */
    @Column(name = "revision_author")
    private String revisionAuthor;

    /**
     * Фамилия автора изменения
     */
    @Column(name = "family_name_author")
    private String familyNameAuthor;

    /**
     * Имя автора изменения
     */
    @Column(name = "given_name_author")
    private String givenNameAuthor;
}
