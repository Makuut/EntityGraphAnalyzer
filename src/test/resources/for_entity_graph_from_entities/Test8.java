@Entity
@NamedEntityGraph(name = "graph1",
        attributeNodes = @NamedAttributeNode(value = "node1", subgraphs = "subgraph1"),
        subgraphs = {
                @NamedSubgraph(name = "subgraph1", attributeNodes = @NamedAttributeNode("node1")),
                @NamedSubgraph(name = "subgraph2", attributeNodes = {
                        @NamedAttributeNode("node2"),
                        @NamedAttributeNode("node3")}),
                @NamedSubgraph(name = "subgraph3", attributeNodes = {
                        @NamedAttributeNode(value = "node4", subgraphs= "subgraph4"),
                        @NamedAttributeNode(value = "node5", subgraphs= "subgraph5")}),
                @NamedSubgraph(name = "subgraph4", attributeNodes = {
                        @NamedAttributeNode("node6"),
                        @NamedAttributeNode(value = "node7", subgraphs= "subgraph5")}),
                @NamedSubgraph(name = "subgraph5", attributeNodes = {
                        @NamedAttributeNode(value = "node8", subgraphs= "subgraph2"),
                        @NamedAttributeNode(value = "node9", subgraphs= "subgraph1")})
        }
)
public class Test8 {
}
