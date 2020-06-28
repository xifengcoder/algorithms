package com.algorithms;

import java.util.*;

/**
 * 二叉树遍历问题
 * Created by Han on 2020/1/21
 */
public class BinaryTreeTraversal {

    public static void main(String[] args) {
    }

    /**
     * 中序遍历:
     * <p>
     * 对遍历的每一个结点，始终保证它的left孩子先进栈。
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        while (!stack.empty() || current != null) {
            if (current != null) {
                stack.push(current);
                current = current.left;
            } else {
                current = stack.pop();
                result.add(current.val);
                current = current.right;

            }
        }
        return result;

    }

    /**
     * 中序遍历:
     * <p>
     * 递归解法
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        return list;
    }

    private void inorder(TreeNode root, List<Integer> list) {
        if (root != null) {
            inorder(root.left, list);
            list.add(root.val);
            inorder(root.right, list);
        }
    }


    /**
     * 前序遍历:
     * <p>
     * 非递归
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        while (current != null || !stack.isEmpty()) {
            if (current != null) {
                result.add(current.val);
                if (current.right != null) {
                    stack.push(current.right);
                }
                current = current.left;
            } else {
                current = stack.pop();
                result.add(current.val);
                if (current.right != null) {
                    stack.push(current.right);
                }
                current = current.left;
            }

        }

        return result;
    }

    /**
     * 后序遍历:
     * <p>
     * 非递归解法
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Map<TreeNode, Integer> hashMap = new HashMap<>();

        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        while (!stack.empty() || current != null) {
            if (current != null) {
                stack.push(current);
                hashMap.put(current, 1);
                current = current.left;
            } else {
                current = stack.peek();
                if (hashMap.get(current) == 2) {
                    current = stack.pop();
                    result.add(current.val);
                    current = null;
                } else {
                    hashMap.put(current, 2);
                    current = current.right;
                }
            }
        }
        return result;
    }

    /**
     * 后续遍历:
     * 非递归法: 栈 + 前结点;
     * <p>
     * 1. 在遍历某一结点时, 如果该结点有左child, 则左child进栈, 表示左child优先级在自己之上;
     * 2. 在从栈中访问到某一结点时, 先不急于输出, 需要先判断它是否有右child, 如果有且右child还没有被遍历过, 则右child进栈, 表示右child优先级在自己之上。
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        TreeNode prev = null;
        while (current != null || !stack.empty()) {
            if (current != null) {
                stack.push(current);
                current = current.left;
            } else {
                current = stack.peek();
                if (current.right == null || current.right == prev) {
                    current = stack.pop();
                    result.add(current.val);
                    prev = current;
                    current = null;
                } else {
                    current = current.right;
                }
            }
        }
        return result;
    }

    /**
     * Definition for a binary tree node.
     */
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


}
