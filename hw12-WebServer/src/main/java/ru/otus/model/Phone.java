package ru.otus.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "phone")
public class Phone implements Cloneable {
    @Id
    @SequenceGenerator(name = "phone_gen", sequenceName = "phone_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "phone_gen")
    @Column(name = "id")
    private Long id;
    @Column
    private String phoneNumber;

    public Phone(Long id, String number) {
        this.id = id;
        this.phoneNumber = number;
    }

    public Phone clone() {
        return new Phone(this.id, this.phoneNumber);
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", number='" + phoneNumber + '\'' +
                '}';
    }
}
