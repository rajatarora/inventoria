package co.rajat.inventoriainventory.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Document(collection = "products")
public class InventoriaProduct {

    @Id
    private UUID id;

    @Field(name = "product_name")
    private String name;

    @Field(name = "category")
    private String category;

    @Field(name = "created_on")
    private LocalDateTime createdOn;



}

@Data
class InventoriaProductAttribute {

    private String name;
    private Object value;
    private InventoriaProductAttributeDatatype dataType;

}

enum InventoriaProductAttributeDatatype {

    STRING,
    NUMBER,
    DATE,
    DATETIME,
    BOOLEAN

}

enum InventoriaProductAttributeUnit {

    MILLIMETER,
    CENTIMETER,
    METER,
    INCH,
    GRAM,
    KILOGRAM,
    POUND,
    MILLILITER,
    LITER

}

//TODO: Create a Perishable Product, extending from Product.