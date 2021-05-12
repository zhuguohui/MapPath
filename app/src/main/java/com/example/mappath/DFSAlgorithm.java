package com.example.mappath;

/**
 * @author zhuguohui
 * @description:
 * @date :2021/5/12 14:39
 */
public class DFSAlgorithm extends MapPathAlgorithm {
    private boolean[][] mark = new boolean[5][5];//表示是否搜索过

    DFSAlgorithm(int[][] ege) {
        super(ege);
    }

    @Override
    public PathStack getMinPath(int from, int to) {
        pathStack.clear();
        minPath = max;
        findByDfs(from - 1, to - 1, 0, new PathStack());
        return pathStack;
    }

    int size = 0;

    int minPath = max;


    PathStack pathStack = new PathStack();

    /**
     * 使用深度优选寻找
     *
     * @param start 开始的点
     * @param dst   已经走过的距离
     * @return
     */
    private void findByDfs(int start, int end, int dst, PathStack stack) {
        if (dst > minPath) {
            return;
        }
        if (start == end) {
            //已经找到
            if (dst < minPath) {
                minPath = dst;
                pathStack.clear();
                pathStack.addAll(stack);
            }
            return;
        }
        //使用深度优先进行搜索
        for (int i = 0; i < ege.length; i++) {
            if (ege[start][i] != max && !mark[start][i]) {
                mark[start][i] = true;//表示这条边已经使用了
                stack.push(new Edge(start, i, ege[start][i]));
                findByDfs(i, end, dst + ege[start][i], stack);
                mark[start][i] = false;//重置状态
                stack.pop();
            }
            size++;
        }
    }
}