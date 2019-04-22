package com.siem.siem.facts;
import java.io.Serializable;
import java.util.Objects;

public class Test implements Serializable {
	public enum Category {
        NA, TEST1, TEST2
    };
    
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String name;
    private Category category;
    
    public Test(String name) {
        this(null, name);
    }
    
    public Test(Long id, String name) {
        this.id = id;
        this.name = name;
        this.category = Category.NA;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.id);
        hash = 59 * hash + Objects.hashCode(this.name);
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Test other = (Test) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

	@Override
	public String toString() {
		return "Test [id=" + id + ", name=" + name + "]";
	}
}
