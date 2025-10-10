package ch.ofte.mplayer.server.user;

import jakarta.persistence.Entity;

import ch.ofte.commons.model.NamedBaseEntity;

@Entity
public class User extends NamedBaseEntity {
    private String password;
}
