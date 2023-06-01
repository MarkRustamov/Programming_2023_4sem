package is.technologies.entities;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import org.jetbrains.annotations.NotNull;

import java.sql.Date;

@Entity
@Table(name = "car_brand")
public class CarBrand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date foundingDate;

    public CarBrand(long id, @NotNull String name, @NotNull Date foundingDate) {
        this.id = id;
        this.name = name;
        this.foundingDate = foundingDate;
    }

    public CarBrand(@NotNull String name, @NotNull Date foundingDate) {
        this(0, name, foundingDate);
    }

    public CarBrand() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public Date getFoundingDate() {
        return foundingDate;
    }

    public void setFoundingDate(@NotNull Date foundingDate) {
        this.foundingDate = foundingDate;
    }
}
