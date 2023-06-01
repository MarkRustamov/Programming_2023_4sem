package is.technologies.entities;

import is.technologies.exceptions.CarModelException;
import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

@Entity
@Table(name = "car_model")
public class CarModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private int length;

    private int width;

    @Column(name = "body_type")
    @Enumerated(EnumType.STRING)
    private BodyType bodyType;

    @ManyToOne
    @JoinColumn(name = "car_brand")
    private CarBrand carBrand;

    private int height;

    public CarModel(long id, @NotNull String name, int length, int width, @NotNull BodyType bodyType, @NotNull CarBrand carBrand, int height) throws CarModelException {
        if (length <= 0)
            throw new CarModelException(length);

        if (width <= 0)
            throw new CarModelException(width);

        if (height <= 0)
            throw new CarModelException(height);

        this.id = id;
        this.name = name;
        this.length = length;
        this.width = width;
        this.bodyType = bodyType;
        this.carBrand = carBrand;
        this.height = height;
    }

    public CarModel(@NotNull String name, int length, int width, @NotNull BodyType bodyType, @NotNull CarBrand carBrand, int height) throws CarModelException {
        this(0, name, length, width, bodyType, carBrand, height);
    }

    public CarModel() {

    }

    public String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) throws CarModelException {
        if (length <= 0) {
            throw new CarModelException(length);
        }

        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) throws CarModelException {
        if (length <= 0) {
            throw new CarModelException(length);
        }

        this.width = width;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BodyType getBodyType() {
        return bodyType;
    }

    public void setBodyType(@NotNull BodyType bodyType) {
        this.bodyType = bodyType;
    }

    public CarBrand getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(@NotNull CarBrand carBrand) {
        this.carBrand = carBrand;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) throws CarModelException {
        if (height <= 0) {
            throw new CarModelException(height);
        }

        this.height = height;
    }
}
