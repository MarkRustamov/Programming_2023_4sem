package is.technologies.dto;

public class EngineDTO {
    private String name;

    private int capacity;

    private int numberOfCylinders;

    private int height;

    private long carModelId;

    public EngineDTO(String name, int capacity, int numberOfCylinders, int height, long carModelId) {
        this.name = name;
        this.capacity = capacity;
        this.numberOfCylinders = numberOfCylinders;
        this.height = height;
        this.carModelId = carModelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getNumberOfCylinders() {
        return numberOfCylinders;
    }

    public void setNumberOfCylinders(int numberOfCylinders) {
        this.numberOfCylinders = numberOfCylinders;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public long getCarModelId() {
        return carModelId;
    }

    public void setCarModelId(long carModelId) {
        this.carModelId = carModelId;
    }
}
