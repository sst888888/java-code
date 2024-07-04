package chapter.graph;

import utils.Vertex;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 图 深度优先遍历
 * https://github.com/krahets/hello-algo/blob/main/codes/java/chapter_graph/graph_dfs.java
 */
public class GraphDfs {

    /* 深度优先遍历辅助函数 */
    static void dfs(GraphAdjList graph, Set<Vertex> visited, List<Vertex> res, Vertex vet) {
        res.add(vet); // 记录访问顶点
        visited.add(vet); // 标记该顶点已被访问
        // 遍历该顶点的所有邻接顶点
        for (Vertex adjVet : graph.adjList.get(vet)) {
            if (visited.contains(adjVet)) {
                continue;
            }
            // 递归遍历邻接顶点
            dfs(graph, visited, res, adjVet);
        }
    }


    /* 深度优先遍历 */
    // 使用邻接表来表示图 以便获取指定顶点的所有邻接顶点
    static List<Vertex> graphDFS(GraphAdjList graph, Vertex startVet) {
        // 顶点遍历序列
        List<Vertex> res = new ArrayList<>();
        // 哈希集合 用于记录已被访问过的顶点
        Set<Vertex> visited = new HashSet<>();
        dfs(graph, visited, res, startVet);
        return res;
    }

    /**
     *  0 -- 1 -- 2
     *  |         |
     *  3    4 -- 5
     *            |
     *            6
     */
    public static void main(String[] args) {
        /* 初始化无向图 */
        Vertex[] v = Vertex.valsToVets(new int[] {0, 1, 2, 3, 4, 5, 6});
        Vertex[][] edges = { { v[0], v[1] }, { v[0], v[3] }, { v[1], v[2] },
                { v[2], v[5] }, { v[4], v[5] }, { v[5], v[6] } }; // {{0,1}, {0,3},{1,2},{2,5},{4,5},{5,6}}
        GraphAdjList graph = new GraphAdjList(edges);
        System.out.println("\n初始化后，图为");
        graph.print();
    }
}
