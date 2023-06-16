package ru.otus.model;

import jakarta.annotation.Nonnull;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "address")
public class Address {
    @Id
    @Column("client_id")
    @Nonnull
    private Long id;
    @Nonnull
    private String street;

    public Address() {
    }

    public Address(String street) {
        this(null, street);
    }

    @PersistenceCreator
    public Address(Long id, String street) {
        this.id = id;
        this.street = street;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return street;
    }
}
