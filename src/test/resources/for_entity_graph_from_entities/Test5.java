@Entity
@NamedEntityGraph(name = "graph1",
        attributeNodes = @NamedAttributeNode(value = "node1", subgraphs = "subgraph1"),
        subgraphs = @NamedSubgraph(name = "subgraph1", attributeNodes = @NamedAttributeNode("node3"))
)
public class Test5 {
}
