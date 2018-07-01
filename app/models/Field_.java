package models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


@Entity
@Table(name = "Field")
public class Field_ {
	
	@Id
	@GeneratedValue
	private Long id;

	@Size(min = 3, max = 20)
	@Column(length = 20)
	private String label;
	
	@Enumerated(EnumType.STRING)
	private FieldType type;

	//relation for work with options if they are exists

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "field_id", referencedColumnName = "id")
	private List<FieldOptions> options;

	//boolean marks for required and active
	@NotNull
	private boolean required;
	
	@NotNull
	private boolean active;//for showing
	
	
	//getters and setters
	
	public Long getId() {
		return id;
	}
	
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public FieldType getType() {
        return type;
    }

    public void setType(FieldType type) {
        this.type = type;
    }

    public List<FieldOptions> getOptions() {
		return options;
	}

	public void setOptions(List<FieldOptions> options) {
		this.options = options;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
}