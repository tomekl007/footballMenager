package entgen;

import entgen.LeagueGen;
import entgen.PersonGen;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2012-09-10T06:53:39")
@StaticMetamodel(TeamGen.class)
public class TeamGen_ { 

    public static volatile SingularAttribute<TeamGen, Long> id;
    public static volatile SingularAttribute<TeamGen, Integer> budget;
    public static volatile SingularAttribute<TeamGen, LeagueGen> leagueBelong;
    public static volatile SingularAttribute<TeamGen, byte[]> imgSrc;
    public static volatile SingularAttribute<TeamGen, PersonGen> coach;
    public static volatile SingularAttribute<TeamGen, String> name;
    public static volatile SingularAttribute<TeamGen, String> img;

}