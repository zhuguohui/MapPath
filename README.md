# MapPath
研究单源最短路径的demo
# 序言
为了研究单源最短路径的相关算法，写了一个demo
算法的细节请参考
[图的四种最短路径算法](https://blog.csdn.net/wzy_2017/article/details/78910697)

我实现了其中的两种。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210512155731825.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzIyNzA2NTE1,size_16,color_FFFFFF,t_70)
我将算法进行了封装,这是基类

```java
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
```
直接使用,相比之前的算法。我的算法可以获取到形成的路径。

```java
  private void start() {
        MapPathAlgorithm apa = rb_dj.isChecked() ? new DijkstraAlgorithm(ege) : new DFSAlgorithm(ege);
        int from = 0;
        int to = 0;
        try {
            from = Integer.parseInt(et_from.getText().toString());
            to = Integer.parseInt(et_to.getText().toString());
        } catch (Exception e) {
            Toast.makeText(this, "请输入正确的数字", Toast.LENGTH_SHORT).show();
            return;
        }
        MapPathAlgorithm.PathStack pathStack = apa.getMinPath(from, to);
//        Log.i("zzz", "找到的路径为:" + pathStack);
        tv_result.setText("找到路径：" + pathStack);
    }
```
边是通过数组定义的

```java

    private final int[][] ege = new int[][]{
            {max, 2, max, max, 10},
            {max, max, 3, max, 7},
            {4, max, max, 4, max},
            {max, max, max, max, 5},
            {max, max, 3, max, max}
    };
```


# 源码

> [MapPath](https://github.com/zhuguohui/MapPath)
