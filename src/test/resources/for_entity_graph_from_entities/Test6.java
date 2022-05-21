@Entity
@NamedEntityGraph(name = "graph1",
        attributeNodes = {
                @NamedAttributeNode(value = "node1", subgraphs = "subgraph1"),
                @NamedAttributeNode(value = "node2", subgraphs = "subgraph2")
        },
        subgraphs= {
        @NamedSubgraph(name = "subgraph1", attributeNodes = @NamedAttributeNode("node3")),
        @NamedSubgraph(name = "subgraph2", attributeNodes = @NamedAttributeNode("node4"))
        }
)
public class Test6 {
}
