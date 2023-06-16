package ru.otus.model;

import jakarta.annotation.Nonnull;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Table(name = "client")
public class Client {

    @Id
    @Column("id")
    @Nonnull
    private Long id;
    @Nonnull
    private String name;

    @MappedCollection(idColumn = "client_id")
    private Address address;
    @MappedCollection(idColumn = "client_id", keyColumn = "id")
    private List<Phone> numbers;

    public Client() {
    }

    @PersistenceCreator
    public Client(Long id, String name, Address address, List<Phone> numbers) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.numbers = numbers;
    }

    public Client(String name, Address address, List<Phone> numbers) {
        this(null, name, address, numbers);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public List<Phone> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<Phone> numbers) {
        this.numbers = numbers;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
