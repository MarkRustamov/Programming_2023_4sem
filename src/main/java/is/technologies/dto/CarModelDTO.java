package is.technologies.dto;

import is.technologies.entities.BodyType;

public class CarModelDTO {
    private String name;

    private int length;

    private int width;

    private BodyType bodyType;

    private long carBrandId;

    private int height;

    public CarModelDTO(String name, int length, int width, BodyType bodyType, long carBrandId, int height) {
        this.name = name;
        this.length = length;
        this.width = width;
        this.bodyType = bodyType;
        this.carBrandId = carBrandId;
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public BodyType getBodyType() {
        return bodyType;
    }

    public void setBodyType(BodyType bodyType) {
        this.bodyType = bodyType;
    }

    public long getCarBrandId() {
        return carBrandId;
    }

    public void setCarBrandId(long carBrandId) {
        this.carBrandId = carBrandId;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
