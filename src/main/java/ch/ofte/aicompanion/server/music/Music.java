package ch.ofte.aicompanion.server.music;

import java.time.LocalDate;
import jakarta.persistence.Entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import ch.ofte.commons.model.NamedBaseEntity;

@Entity
@Getter
@Setter
@ToString
public class Music extends NamedBaseEntity {
    private LocalDate releaseDate;
    private Composer composer;
}
