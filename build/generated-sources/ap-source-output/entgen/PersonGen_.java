package entgen;

import entgen.GroupsGen;
import entgen.TeamGen;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2012-09-10T06:53:38")
@StaticMetamodel(PersonGen.class)
public class PersonGen_ { 

    public static volatile SingularAttribute<PersonGen, Long> id;
    public static volatile SingularAttribute<PersonGen, TeamGen> ownedTeam;
    public static volatile SingularAttribute<PersonGen, GroupsGen> ownedGroup;
    public static volatile SingularAttribute<PersonGen, String> email;
    public static volatile SingularAttribute<PersonGen, String> lastname;
    public static volatile SingularAttribute<PersonGen, String> firstname;
    public static volatile SingularAttribute<PersonGen, String> password;
    public static volatile SingularAttribute<PersonGen, String> city;

}