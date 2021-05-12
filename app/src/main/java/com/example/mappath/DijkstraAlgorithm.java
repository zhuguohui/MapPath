package com.example.mappath;

/**
 * @author zhuguohui
 * @description: 使用迪杰斯特拉算法实现
 * <p>
 * * @date :2021/5/12 15:14
 */
public class DijkstraAlgorithm extends MapPathAlgorithm {


    DijkstraAlgorithm(int[][] ege) {
        super(ege);
    }

    @Override
    public PathStack getMinPath(int from, int to) {
        from--;
        to--;
        int n = ege.length;
        boolean[] check = new boolean[n];
        check[from] = true;
        check[from] = true;
        int[] dis = new int[n];
        int checkNumber = 1;
        for (int i = 0; i < n; i++) {
            dis[i] = ege[from][i];
        }
        PathStack[] pathStacks = new PathStack[n];
        for (int i = 0; i < n; i++) {
            PathStack pathStack = new PathStack();
            pathStack.push(new Edge(from, i, ege[from][i]));
            pathStacks[i] = pathStack;
        }

        while (checkNumber < n) {
            //从dfs中选择没有check的最小的值
            int minIndex = -1;
            int minValue = max;
            for (int i = 0; i < n; i++) {
                if(!check[i]&&dis[i]<minValue){
                    minIndex=i;
                    minValue=dis[i];
                }
            }
            //尝试松弛
            for(int i=0;i<n;i++){
                if(ege[minIndex][i]!=max){
                    int newDis=dis[minIndex]+ege[minIndex][i];
                    if(newDis<dis[i]){
                        //松弛成功
                        //记录路径
                        PathStack pathStack=pathStacks[i];
                        pathStack.clear();
                        pathStack.addAll(pathStacks[minIndex]);
                        pathStack.add(new Edge(minIndex,i,ege[minIndex][i]));
                        //保存dis
                        dis[i]=newDis;
                    }
                }
            }
            //松弛完成
            check[minIndex]=true;
            checkNumber++;

        }


        return pathStacks[to];
    }
}