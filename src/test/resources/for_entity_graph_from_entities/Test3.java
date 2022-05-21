@Entity
@NamedEntityGraph(name = "graph1",
        attributeNodes = @NamedAttributeNode("node1"),
        subgraphs = @NamedSubgraph(name = "subgraph1", attributeNodes = @NamedAttributeNode("sro"))
)
public class Test3 {
}
