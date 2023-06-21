package ru.otus.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "phone")
public class Phone {
    @Id
    @Column("id")
    private final Long id;
    @Column("client_id")
    private final Long clientId;
    private final String number;

    public Phone(String number) {
        this.id = null;
        this.clientId = null;
        this.number = number;
    }

    @PersistenceCreator
    private Phone(Long id, Long clientId, String number) {
        this.id = id;
        this.clientId = clientId;
        this.number = number;
    }

    public Long getId() {
        return id;
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
                '}';
    }
}
