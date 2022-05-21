@Entity
@NamedEntityGraph(name = "graph1", attributeNodes = @NamedAttributeNode("node1"))
@NamedEntityGraph(name = "graph2", attributeNodes = @NamedAttributeNode("node2"))
public class Test1 {
}
