@Entity
@NamedEntityGraph(name = "graph1",
        attributeNodes = @NamedAttributeNode(value = "node1", subgraphs = "subgraph1"),
        subgraphs = @NamedSubgraph(name = "subgraph1", attributeNodes = {@NamedAttributeNode("node2"), @NamedAttributeNode(value = "node3", subgraphs="subgtaph2")})
)
public class Test7 {
}
