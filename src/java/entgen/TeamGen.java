/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entgen;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Tomek
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "TeamGen.findAll",query = "SELECT t FROM TeamGen t")
    , @NamedQuery(name = "TeamGen.findById", query = "SELECT t FROM TeamGen t WHERE t.id = :id")
    , @NamedQuery(name = "TeamGen.findByName", query = "SELECT t FROM TeamGen t WHERE t.name = :name")
 //   , @NamedQuery(name = "Product.findByPrice", query = "SELECT p FROM Product p WHERE p.price = :price")
  //  , @NamedQuery(name = "Product.findByDescription", query = "SELECT p FROM Product p WHERE p.description = :description")
    , @NamedQuery(name = "TeamGen.findByImg", query = "SELECT t FROM TeamGen t WHERE t.img = :img") })
public class TeamGen implements Serializable {
    @ManyToOne
    private LeagueGen leagueBelong;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
   // @OneToOne
  //  CustomerGen coach;
    
      private Integer budget;
    private String name;
    private String img;
   
   @Lob
   @Basic(fetch = FetchType.LAZY) 
   private byte[] imgSrc;
   
   @OneToOne(mappedBy = "ownedTeam")
    private PersonGen coach;
   
   public TeamGen(){
       
   }
   
   

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public void setCoach(PersonGen c){
        this.coach = c;
   }
    
    public PersonGen getCoach(){
        return coach;
        
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public LeagueGen getLeagueBelong(){
        return leagueBelong;
    }
    
    public void setLeagueBelong(LeagueGen l){
        this.leagueBelong = l;
        
    }
    
     public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer b) {
        this.budget = b;
    }
   
   
   public void setImg(String simg) {
        this.img = simg;
    }

    public byte[] getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(byte[] imgSrc) {
        this.imgSrc = imgSrc;
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
        if (!(object instanceof TeamGen)) {
            return false;
        }
        TeamGen other = (TeamGen) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entgen.TeamGen[ id=" + id + " ]";
    }
    
}
