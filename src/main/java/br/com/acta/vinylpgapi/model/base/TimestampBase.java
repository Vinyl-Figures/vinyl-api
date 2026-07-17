package br.com.acta.vinylpgapi.model.base;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;

@MappedSuperclass
@Getter
public class TimestampBase extends ModelBase{
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private OffsetDateTime createdAt;
}
