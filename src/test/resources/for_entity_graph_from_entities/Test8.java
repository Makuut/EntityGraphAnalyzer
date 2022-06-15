@Entity
@NamedEntityGraph(name = "graph1",
        attributeNodes = {
                @NamedAttributeNode("node0"),
                @NamedAttributeNode(value = "node1", subgraphs = "subgraph1"),
                @NamedAttributeNode(value = "node2", subgraphs = "subgraph2"),
                @NamedAttributeNode(value = "node3", subgraphs = "subgraph3")},
        subgraphs = {
                @NamedSubgraph(name = "subgraph1", attributeNodes = @NamedAttributeNode("node3 ")),
                @NamedSubgraph(name = "subgraph2", attributeNodes = {
                        @NamedAttributeNode("node5"),
                        @NamedAttributeNode("node6")}),
                @NamedSubgraph(name = "subgraph3", attributeNodes = {
                        @NamedAttributeNode(value = "node7", subgraphs = "subgraph4"),
                        @NamedAttributeNode(value = "node8", subgraphs = "subgraph5")}),
                @NamedSubgraph(name = "subgraph4", attributeNodes = {
                        @NamedAttributeNode("node9"),
                        @NamedAttributeNode(value = "node10", subgraphs = "subgraph5")}),
                @NamedSubgraph(name = "subgraph5", attributeNodes = {
                        @NamedAttributeNode(value = "node11"),
                        @NamedAttributeNode("node12")})
        }
)
public class Test8 {
}
