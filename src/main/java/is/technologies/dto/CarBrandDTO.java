package is.technologies.dto;

import java.sql.Date;

public class CarBrandDTO {
    private String name;

    private Date foundingDate;

    public CarBrandDTO(String name, Date foundingDate) {
        this.name = name;
        this.foundingDate = foundingDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getFoundingDate() {
        return foundingDate;
    }

    public void setFoundingDate(Date foundingDate) {
        this.foundingDate = foundingDate;
    }
}
