package is.technologies.entities;

import is.technologies.exceptions.EngineException;
import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

@Entity
@Table(name = "engine")
public class Engine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private int capacity;

    @Column(name = "number_of_cylinders")
    private int numberOfCylinders;

    private int height;

    @ManyToOne
    @JoinColumn(name = "car_model")
    private CarModel carModel;



    public Engine(long id, @NotNull String name, int capacity, int numberOfCylinders, int height, @NotNull CarModel carModel) throws EngineException {
        if (capacity <= 0)
            throw new EngineException(capacity);

        if (numberOfCylinders <= 0)
            throw new EngineException(numberOfCylinders);

        if (height <= 0)
            throw new EngineException(height);

        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.numberOfCylinders = numberOfCylinders;
        this.height = height;
        this.carModel = carModel;
    }

    public Engine(@NotNull String name, int capacity, int numberOfCylinders, int height, @NotNull CarModel carModel) throws EngineException {
        this(0, name, capacity, numberOfCylinders, height, carModel);
    }

    public Engine() {

    }

    public String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) throws EngineException {
        if (capacity <= 0)
            throw new EngineException(capacity);

        this.capacity = capacity;
    }

    public int getNumberOfCylinders() {
        return numberOfCylinders;
    }

    public void setNumberOfCylinders(int numberOfCylinders) throws EngineException {
        if (numberOfCylinders <= 0)
            throw new EngineException(numberOfCylinders);

        this.numberOfCylinders = numberOfCylinders;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) throws EngineException {
        if (height <= 0)
            throw new EngineException(height);

        this.height = height;
    }

    public CarModel getCarModel() {
        return carModel;
    }

    public void setCarModel(@NotNull CarModel carModel) {
        this.carModel = carModel;
    }
}
