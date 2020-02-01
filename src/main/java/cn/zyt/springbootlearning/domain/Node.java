package cn.zyt.springbootlearning.domain;

import lombok.Data;

@Data
public class Node {
    private String name;
    private long capacity;
    private int cores;

    @Override
    public String toString() {
        return "Node{" +
                "name='" + name + '\'' +
                ", capacity=" + capacity +
                ", cores=" + cores +
                '}';
    }
}
