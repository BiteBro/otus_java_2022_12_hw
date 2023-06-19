package ru.otus.model;

import jakarta.annotation.Nonnull;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Set;

@Table(name = "client")
public class Client implements Persistable<Long> {

    @Id
    @Column("id")
    private final Long id;
    private final String name;

    @MappedCollection(idColumn = "client_id")
    private final Address address;
    @MappedCollection(idColumn = "client_id")
    private final Set<Phone> numbers;
    @Transient
    private final boolean isNew;

    @PersistenceCreator
    private Client(Long id, String name, Address address, Set<Phone> numbers) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.numbers = numbers;
        this.isNew = false;
    }

    public Client(Long id, String name, Address address, Set<Phone> numbers, boolean isNew) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.numbers = numbers;
        this.isNew = isNew;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return isNew;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public Set<Phone> getNumbers() {
        return numbers;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", numbers=" + numbers +
                '}';
    }
}
