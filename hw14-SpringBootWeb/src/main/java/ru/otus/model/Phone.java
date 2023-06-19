package ru.otus.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "phone")
public class Phone implements Persistable<Long> {
    @Id
    @Column("id")
    private final Long id;
    @Column("client_id")
    private final Long clientId;
    private final String number;

    @Transient
    private final boolean isNew;

    public Phone(Long id, String number, boolean isNew) {
        this.id = id;
        this.clientId = null;
        this.number = number;
        this.isNew = isNew;
    }

    @PersistenceCreator
    private Phone(Long id, Long clientId, String number) {
        this.id = id;
        this.clientId = clientId;
        this.number = number;
        this.isNew = false;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return isNew;
    }

    public Long getClientId() {
        return clientId;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", number='" + number + '\'' +
                ", isNew=" + isNew +
                '}';
    }
}
