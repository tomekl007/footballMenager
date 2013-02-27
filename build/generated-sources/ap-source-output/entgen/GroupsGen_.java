package entgen;

import entgen.PersonGen;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2012-09-10T06:53:39")
@StaticMetamodel(GroupsGen.class)
public class GroupsGen_ { 

    public static volatile SingularAttribute<GroupsGen, Long> id;
    public static volatile ListAttribute<GroupsGen, PersonGen> personList;
    public static volatile SingularAttribute<GroupsGen, String> description;
    public static volatile SingularAttribute<GroupsGen, String> name;

}