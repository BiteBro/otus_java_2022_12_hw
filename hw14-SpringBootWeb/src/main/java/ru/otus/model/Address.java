package ru.otus.model;

import jakarta.annotation.Nonnull;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "address")
public class Address implements Persistable<Long>{
    @Id
    @Column("client_id")
    private final Long id;
    private final String street;
    @Transient
    private final boolean isNew;

    public Address(Long id, String street, boolean isNew) {
        this.id = id;
        this.street = street;
        this.isNew = isNew;
    }

    @PersistenceCreator
    private Address(Long id, String street) {
        this.id = id;
        this.street = street;
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

    public String getStreet() {
        return street;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                '}';
    }
}