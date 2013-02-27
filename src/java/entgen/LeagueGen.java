/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entgen;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Tomek
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "LeagueGen.findAll",query = "SELECT l FROM LeagueGen l")
    , @NamedQuery(name = "LeagueGen.findById", query = "SELECT l FROM LeagueGen l WHERE l.id = :id")
    , @NamedQuery(name = "LeagueGen.findByName", query = "SELECT l FROM LeagueGen l WHERE l.name = :name")
   // , @NamedQuery(name = "Category.findByTags", query = "SELECT c FROM Category c WHERE c.tags = :tags")
  , @NamedQuery(name = "LeagueGen.findByImg", query = "SELECT l FROM LeagueGen l WHERE l.img = :img")
})
public class LeagueGen implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String name;
    
    @OneToMany(mappedBy = "leagueBelong")
    private List<TeamGen> teams;
   
   private String country;
    private String img;
   
   @Lob
   @Basic(fetch = FetchType.LAZY) 
   private byte[] imgSrc;

    public LeagueGen() {
        this.teams = new ArrayList<TeamGen>();
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
    
    
    public String geCountry() {
        return country;
    }

    public void setCountry(String c) {
        this.country = c;
    }
    
    public List<TeamGen> getTeams() {
        return teams;
    }

    public void setTeams(List<TeamGen> t) {
        this.teams = t;
    }
    
    public String getImg() {
        return img;
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
        if (!(object instanceof LeagueGen)) {
            return false;
        }
        LeagueGen other = (LeagueGen) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.name;
    }
    
}
