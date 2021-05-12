package com.example.mappath;

import androidx.annotation.NonNull;

import java.util.Stack;

/**
 * @author zhuguohui
 * @description:对图的单源最短路径算法的抽象
 * @date :2021/5/12 14:34
 */
public abstract class MapPathAlgorithm {
    protected final int[][] ege;
    /**
     * max表示无法到达
     */
    public static final int max = 999999999;
    MapPathAlgorithm(int[][] ege) {

        this.ege = ege;
    }

    public abstract PathStack getMinPath(int from, int to);

    public static class Edge {
        int from;
        int to;
        int length;

        public Edge(int from, int to, int length) {
            this.from = from;
            this.to = to;
            this.length = length;
        }

        @Override
        public String toString() {
            return (from + 1) + "---->";
        }
    }

    public static class PathStack extends Stack<Edge> {

        private int getPathSize() {
            int size = 0;
            for (Edge edge : this) {
                size += edge.length;
            }
            return size;
        }

        @NonNull
        @Override
        public synchronized String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("路径长度(" + getPathSize() + ")：");
            for (int i = 0; i < size(); i++) {
                Edge edge = get(i);
                builder.append(edge);
                if (i == size() - 1) {
                    builder.append(edge.to + 1);
                }

            }
            return builder.toString();
        }
    }

} 