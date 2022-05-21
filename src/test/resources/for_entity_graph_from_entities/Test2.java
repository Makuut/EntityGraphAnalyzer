@Entity
@NamedEntityGraph(name = "graph1", attributeNodes = {@NamedAttributeNode("node1"), @NamedAttributeNode("node2")})
@NamedEntityGraph(name = "graph2", attributeNodes = {@NamedAttributeNode("node3"), @NamedAttributeNode("node4")})
public class Test2 {
}
