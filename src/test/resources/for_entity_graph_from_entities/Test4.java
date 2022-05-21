@Entity
@NamedEntityGraph(name = "graph1",
        attributeNodes = {@NamedAttributeNode("node1"), @NamedAttributeNode("node2")},
        subgraphs = @NamedSubgraph(name = "subgraph1", attributeNodes = @NamedAttributeNode("node3"))
)
public class Test4 {
}
