package com.hanzo.system.util;

import com.hanzo.system.dto.SysDeptResultParam;
import com.hanzo.system.dto.SysMenuResultParam;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author thy
 * @Date 2020/10/18 15:51
 * @Description:树形结构
 */
public class TreeUtil {

    private final static Integer TOP_NODE_ID = 0;

    /**
     * 所有的节点列表  部门
     * @param nodes
     * @return
     */
    public List data(List<SysDeptResultParam> nodes) {
        ArrayList<SysDeptResultParam> rootNode = new ArrayList<>();
        nodes.forEach(x -> {
            if(x.getParentId() == TOP_NODE_ID){
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
     * 获取根节点的子节点 部门
     * @param deptId
     * @param allNode
     * @return
     */
    public List<SysDeptResultParam> getChild(Integer deptId, List<SysDeptResultParam> allNode) {
        //存放子节点的集合
        ArrayList<SysDeptResultParam> listChild = new ArrayList<>();
        allNode.forEach(x -> {
            if (x.getParentId() == deptId) {
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

    /**
     * 所有的节点列表 菜单/按钮
     * @param nodes
     * @return
     */
    public List menuData(List<SysMenuResultParam> nodes) {
        ArrayList<SysMenuResultParam> rootNode = new ArrayList<>();
        nodes.forEach(x -> {
            if(x.getParentId() == TOP_NODE_ID){
                x.setHasParent(true);
                rootNode.add(x);
            }
        });
        rootNode.forEach(x -> {
            List<SysMenuResultParam> child = getMenuChild(x.getMenuId(), nodes);
            x.setChildren(child);
            x.setHasChildren(child != null ? true : false);
        });
        return rootNode;
    }


    /**
     * 获取根节点的子节点 菜单/按钮
     * @param menuId
     * @param allNode
     * @return
     */
    public List<SysMenuResultParam> getMenuChild(Integer menuId, List<SysMenuResultParam> allNode) {
        //存放子节点的集合
        ArrayList<SysMenuResultParam> listChild = new ArrayList<>();
        allNode.forEach(x -> {
            if (x.getParentId() == menuId) {
                x.setHasParent(true);
                listChild.add(x);
            }
        });
        //递归
        listChild.forEach(x -> {
            List<SysMenuResultParam> child = getMenuChild(x.getMenuId(), allNode);
            x.setChildren(child);
            x.setHasChildren(child != null ? true : false);
        });
        if (listChild.size() == 0) {
            return null;
        }
        return listChild;
    }
}
