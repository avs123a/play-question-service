package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/*
* Options for field
* Create if field have next types : radiobutton, checkbox, combobox
*/

@Entity
public class FieldOptions {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="field_id")
    private Long fieldId;

    @NotNull
    private String value;


    //getters and setters

    public Long getId() {
        return id;
    }

    public Long getFieldId() {
        return fieldId;
    }

    public void setFieldId(Long fieldId) {
        this.fieldId = fieldId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
