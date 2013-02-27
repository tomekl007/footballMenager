package entgen;

import entgen.TeamGen;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2012-09-10T06:53:39")
@StaticMetamodel(LeagueGen.class)
public class LeagueGen_ { 

    public static volatile SingularAttribute<LeagueGen, Long> id;
    public static volatile ListAttribute<LeagueGen, TeamGen> teams;
    public static volatile SingularAttribute<LeagueGen, byte[]> imgSrc;
    public static volatile SingularAttribute<LeagueGen, String> name;
    public static volatile SingularAttribute<LeagueGen, String> img;
    public static volatile SingularAttribute<LeagueGen, String> country;

}