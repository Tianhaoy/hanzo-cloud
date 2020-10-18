package com.hanzo.system.util;

import com.hanzo.system.dto.SysDeptResultParam;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author thy
 * @Date 2020/10/18 15:51
 * @Description:树形结构
 */
public class TreeUtil {

    private final static String TOP_NODE_ID = "0";

    /**
     * 所有的节点列表
     * @param nodes
     * @return
     */
    public List data(List<SysDeptResultParam> nodes) {
        ArrayList<SysDeptResultParam> rootNode = new ArrayList<>();
        nodes.forEach(x -> {
            if(x.getParentId().equals(TOP_NODE_ID)){
                x.setHasParent(true);
                rootNode.add(x);
            }
        });
        rootNode.forEach(x -> {
            List<SysDeptResultParam> child = getChild(x.getDeptId(), nodes);
            x.setChildren(child);
            x.setHasChildren(child != null ? true : false);
        });
        return rootNode;
    }


    /**
     * 获取根节点的子节点
     * @param deptId
     * @param allNode
     * @return
     */
    public List<SysDeptResultParam> getChild(Integer deptId, List<SysDeptResultParam> allNode) {
        //存放子节点的集合
        ArrayList<SysDeptResultParam> listChild = new ArrayList<>();
        allNode.forEach(x -> {
            if (x.getParentId().equals(deptId)) {
                x.setHasParent(true);
                listChild.add(x);
            }
        });
        //递归
        listChild.forEach(x -> {
            List<SysDeptResultParam> child = getChild(x.getDeptId(), allNode);
            x.setChildren(child);
            x.setHasChildren(child != null ? true : false);
        });
        if (listChild.size() == 0) {
            return null;
        }
        return listChild;
    }
}
