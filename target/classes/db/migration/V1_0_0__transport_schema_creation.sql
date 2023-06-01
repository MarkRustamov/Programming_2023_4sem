CREATE TABLE IF NOT EXISTS car_brand (
    id bigint NOT NULL AUTO_INCREMENT,
    name varchar(40) NOT NULL,
    date date DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS car_model (
    id bigint NOT NULL AUTO_INCREMENT,
    name varchar(50) NOT NULL,
    length int DEFAULT NULL,
    width int DEFAULT NULL,
    body_type varchar(20) NOT NULL,
    car_brand bigint NOT NULL,
    PRIMARY KEY (id),
    KEY car_model_car_brand_id_fk (car_brand),
    CONSTRAINT car_model_car_brand_id_fk
    FOREIGN KEY (car_brand) REFERENCES car_brand (id)
)