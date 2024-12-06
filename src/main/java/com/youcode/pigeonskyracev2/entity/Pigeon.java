package com.youcode.pigeonskyracev2.entity;
import com.youcode.pigeonskyracev2.entity.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pigeons")
@Builder
public class Pigeon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numberBague;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull(message = "Gender cannot be null")
    private Gender gender;

    private int age;

    private String color;

    @ManyToOne
    @JoinColumn(name = "competition_id")
    private Competition competition;

    public Pigeon(Long id, String numberBague, Gender gender, int age, String color, Competition competition) {
        this.id = id;
        this.numberBague = numberBague;
        this.gender = gender;
        this.age = age;
        this.color = color;
        this.competition = competition;
    }

    public Pigeon() {}

    public Pigeon(String numberBague, Gender gender, int age, String color) {
        this.numberBague = numberBague;
        this.gender = gender;
        this.age = age;
        this.color = color;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumberBague() {
        return numberBague;
    }

    public void setNumberBague(String numberBague) {
        this.numberBague = numberBague;
    }

    public @NotNull(message = "Gender cannot be null") Gender getGender() {
        return gender;
    }

    public void setGender(@NotNull(message = "Gender cannot be null") Gender gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }
}

