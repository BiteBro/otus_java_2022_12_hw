package ru.otus.model;

import jakarta.annotation.Nonnull;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "phone")
public class Phone {
    @Id
    @Column("id")
    @Nonnull
    private Long id;
    @Column("client_id")
    @Nonnull
    private Long client_id;
    @Nonnull
    private String number;

    public Phone() {
    }

    public Phone(String number) {
        this(null, number, null);
    }

    @PersistenceCreator
    public Phone(Long id, String number, Long client_id) {
        this.id = id;
        this.number = number;
        this.client_id = client_id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", number='" + number + '\'' +
                '}';
    }
}
