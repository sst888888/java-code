package org.code.example.sort;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName Graph
 * @Description 无向图
 * @Author Chaiphat
 * @Date 2020/3/11 14:35
 * @Version 1.0
 **/
public class Graph {
    // 顶点的个数
    private int v;
    // 邻接表
    private LinkedList<Integer> adj[];

    public Graph(int v){
        this.v = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; i++){
            adj[i] = new LinkedList<>();
        }
    }

    // 无向图一条边存2次
    public void addEdge(int s, int t){
        adj[s].add(t);
        adj[t].add(s);
    }

    /**
     * 递归打印 s->t 的路径
     * @param prev
     * @param s
     * @param t
     */
    private void print(int[] prev, int s, int t){
        if(prev[t] != -1 && (t != s)){
            print(prev,s,prev[t]);
        }
        System.out.println(t + " ");
    }


    /**
     * 广度优先搜索（BFS） Breadth-First-Search
     * @param s
     * @param t
     */
    public void bfs(int s, int t){
        if(s == t) {
            return;
        }
        /**
         * 用来记录已经被访问的顶点，避免顶点被重复访问。
         * 如果顶点q被访问，那相应的visited[q]会被设置为true。
         */
        boolean[] visited = new boolean[v];
        visited[s] = true;
        /**
         * 用来存储已经被访问、但相连的顶点还没有被访问的顶点。
         */
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        /**
         * 记录搜索路径
         * 这个路径是反向存储的
         * pre[w]存储的是 顶点w是从哪个前驱顶点遍历过来的
         */
        int[] prev = new int[v];
        for(int i = 0; i < v; i++){
            prev[i] = -1;
        }

        while(queue.size() != 0){
            int w = queue.poll();
            for(int i = 0; i < adj[w].size(); i++){
                int q = adj[w].get(i);
                // 没有被访问
                if (!visited[q]) {
                    prev[q] = w;
                    if(q == t){
                        print(prev,s,t);
                        return;
                    }
                    visited[q] = true;
                    queue.add(q);
                }
            }
        }
    }


    boolean found = false;

    /**
     * 深度优先搜索（DFS） Depth-First-Search
     * @param s
     * @param t
     */
    public void dfs(int s, int t){
        found = false;
        boolean[] visited = new boolean[v];
        int[] prev = new int[v];
        for(int i = 0; i < v; i++){
            prev[i] = -1;
        }
        // Arrays.fill(prev,-1); 同for循环
        recurDfs(s,t,visited,prev);
        print(prev,s,t);
    }

    private void recurDfs(int w, int t, boolean[] visited, int[] prev){
        if(found == true){
            return;
        }
        visited[w] = true;
        if(w == t){
            found = true;
            return;
        }

        for(int i = 0; i < adj[w].size(); ++i){
            int q = adj[w].get(i);
            if(!visited[q]){
                prev[q] = w;
                recurDfs(q,t,visited,prev);
            }
        }
    }

}
