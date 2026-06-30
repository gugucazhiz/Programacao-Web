package br.ufrn.tads.prova.domain.interfaces;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Setter
@Getter
@EnableJpaAuditing
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor //vai funcionar pois sem argumentos o lombook injeta a chamada para super()
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private UUID id;

    @Setter(AccessLevel.NONE)
    @Column(updatable = false)
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    
    @Column(nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date modicationDate;

    @Column
    private Boolean active = true;
}