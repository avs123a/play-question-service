package models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * answer for field of question
 */

@Entity
public class ResponseField {

    @Id
    @GeneratedValue
    private Long id;
	
	@Column(name = "response_id")
	private Long responseId;
	
    @Column(name = "field_id")
    private Long fieldId;
	
	@ManyToOne(optional = true)
    @JoinColumn(name = "field_id", referencedColumnName = "id",  insertable = false, updatable = false)
    private Field_ field;

    @NotNull
    private String value;

    //getters and setters

    public Long getId() {
        return id;
    }
	
	public Long getResponseId () {
        return responseId;
    }

    public void setResponseId(Long responseId) {
        this.responseId = responseId;
    }

    public Long getFieldId () {
        return fieldId;
    }

    public void setFieldId(Long fieldId) {
        this.fieldId = fieldId;
    }
	
	public Field_ getField () {
        return field;
    }

    public void setField(Field_ field) {
        this.field = field;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
