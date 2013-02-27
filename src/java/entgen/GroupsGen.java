/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entgen;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Tomek
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "GroupsGen.findAll",query = "SELECT g FROM GroupsGen g")
    , @NamedQuery(name = "GroupsGen.findById", query = "SELECT g FROM GroupsGen g WHERE g.id = :id")
    , @NamedQuery(name = "GroupsGen.findByName", query = "SELECT g FROM GroupsGen g WHERE g.name = :name")
    , @NamedQuery(name = "GroupsGen.findByDescription", query = "SELECT g FROM GroupsGen g WHERE g.description = :description")
})
public class GroupsGen implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @OneToMany(mappedBy = "ownedGroup")
     private List<PersonGen> personList;
    
    
    private String description;
   private String name;
   
   public GroupsGen() {
        this.personList = new ArrayList<PersonGen>();
    }

    public GroupsGen(Long id) {
        this.id = id;
        this.personList = new ArrayList<PersonGen>();
    }

    public GroupsGen(
        Long id,
        String name) {
        this.personList = new ArrayList<PersonGen>();
        this.id = id;
        this.name = name;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

  
    public List<PersonGen> getPersonList() {
        return personList;
    }

    public void setPersonList(List<PersonGen> personList) {
        this.personList = personList;
    }
    
    public void addPerson(PersonGen p){
        this.getPersonList().add(p);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GroupsGen)) {
            return false;
        }
        GroupsGen other = (GroupsGen) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entgen.GroupsGen[ id=" + id + " ]";
    }
    
}
