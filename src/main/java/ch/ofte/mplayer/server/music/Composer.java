package ch.ofte.mplayer.server.music;

import jakarta.persistence.Entity;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

import ch.ofte.commons.model.BaseEntity;

@Entity
@Getter
@Setter
public class Composer extends BaseEntity {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
}
