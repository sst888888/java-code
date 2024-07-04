package chapter.graph;

import utils.Vertex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* 基于邻接表实现的无向图类 */
public class GraphAdjList {
    // 邻接表，key 顶点 value 该顶点的所有邻接顶点
    Map<Vertex, List<Vertex>> adjList;

    /* 构造方法 */
    public GraphAdjList(Vertex[][] edges) {
        this.adjList = new HashMap<>();
        // 添加所有顶点和边
        for (Vertex[] edge : edges) {
            addVertex(edge[0]);
            addVertex(edge[1]);
            addEdge(edge[0], edge[1]);
        }
    }

    /* 添加边 */
    private void addEdge(Vertex vet1, Vertex vet2) {
        if (!adjList.containsKey(vet1) || !adjList.containsKey(vet2) || vet1 == vet2) {
            throw new IllegalArgumentException();
        }
        adjList.get(vet1).add(vet2);
        adjList.get(vet2).add(vet1);
    }

    /* 添加顶点 */
    private void addVertex(Vertex vet) {
        if (adjList.containsKey(vet)) {
            return;
        }

        // 在邻接表中添加一个新链表
        adjList.put(vet, new ArrayList<>());
    }

    /* 打印邻接表 */
    public void print() {
        System.out.println("邻接表 =");
        for (Map.Entry<Vertex, List<Vertex>> pair : adjList.entrySet()) {
            List<Integer> tmp = new ArrayList<>();
            for (Vertex vertex : pair.getValue()) {
                tmp.add(vertex.val);
            }
            System.out.println(pair.getKey().val + ": " + tmp + ",");
        }
    }
}
