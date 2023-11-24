package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a generic tree data structure.
 *
 * @param <T> The type of data stored in the tree nodes.
 */
class TreeNode<T> {
    T data;
    List<TreeNode<T>> children;

    /**
     * Constructs a tree node with the given data.
     *
     * @param data The data to be stored in the node.
     */
    public TreeNode(T data) {
        this.data = data;
        this.children = new ArrayList<>();
    }
}

/**
 * Represents a generic tree data structure with basic operations.
 *
 * @param <T> The type of data stored in the tree nodes.
 */
public class Tree<T> {

    TreeNode<T> root;

    /**
     * Constructs a tree with a root node containing the specified data.
     *
     * @param rootData The data for the root node.
     */
    public Tree(T rootData) {
        this.root = new TreeNode<>(rootData);
    }

    /**
     * Adds a child with the given data to the specified parent node.
     *
     * @param parent    The parent node to which the child will be added.
     * @param childData The data for the child node.
     */
    public void addChild(TreeNode<T> parent, T childData) {
        try {
            if (parent == null) {
                throw new IllegalArgumentException("Parent is null");
            }

            TreeNode<T> child = new TreeNode<>(childData);
            parent.children.add(child);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Adds a root node with the specified data to the tree.
     *
     * @param rootData The data for the new root node.
     */
    public void addRootNode(T rootData) {
        this.root = new TreeNode<>(rootData);
    }

    /**
     * Displays the tree starting from the specified node.
     *
     * @param node  The starting node for displaying the tree.
     * @param level The level of the current node in the tree.
     */
    public void displayTree(TreeNode<T> node, int level) {
        if (node == null)
            System.out.println("Invalid node.");
        for (int i = 0; i < level; i++) {
            System.out.print(" ");
        }
        System.out.println(node.data);

        for (TreeNode<T> child : node.children) {
            displayTree(child, level + 1);
        }
    }

    /**
     * Finds and returns the siblings of the specified node.
     *
     * @param node The node for which to find siblings.
     * @return A list of sibling nodes.
     */
    public List<TreeNode<T>> findSiblings(TreeNode<T> node) {
        TreeNode<T> parent = findParent(node);
        List<TreeNode<T>> siblings = new ArrayList<>();
        if (parent != null && parent.children.size() > 1) {
            for (TreeNode<T> child : parent.children) {
                if (child != node) {
                    siblings.add(child);
                }
            }
            System.out.print("Siblings of '" + node.data + "': ");
        } else {
            System.out.println("No siblings.");
        }
        return siblings;
    }
    /**
 * Retrieves a list of leaf nodes in the tree rooted at the specified node.
 *
 * <p>A leaf node is a node in the tree that has no children.</p>
 *
 * @param node The root node of the tree to find leaves in.
 * @return A list of leaf nodes in the tree rooted at the specified node.
 * @throws NullPointerException if the provided node is null.
 *
 * @param <T> The type of data held in each tree node.
 */
public List<TreeNode<T>> listLeaves(TreeNode<T> node) {
    
        List<TreeNode<T>> leaves = new ArrayList<>();
        if (node.children.isEmpty()) {
            leaves.add(node);
        } 
        else {
            for (TreeNode<T> child : node.children) {
                leaves.addAll(listLeaves(child));
            }
        }
        return leaves;
    }
      
    /**
     * Returns a list of internal nodes in the subtree rooted at the specified node.
     *
     * @param node The root of the subtree for which to find internal nodes.
     * @return A list of internal nodes in the subtree.
     */
    public List<TreeNode<T>> listInternalNodes(TreeNode<T> node) {
        List<TreeNode<T>> internalNodes = new ArrayList<>();
        if (!node.children.isEmpty()) {
            internalNodes.add(node);
            for (TreeNode<T> child : node.children) {
                internalNodes.addAll(listInternalNodes(child));
            }
            System.out.print("Internal nodes of the tree: ");
        }
        else
        {
            System.out.println("No internal nodes not found.");
        }
        
        return internalNodes;
    }

    /**
     * Returns a list of edges (pairs of parent-child nodes) in the tree.
     *
     * @return A list of edges represented as arrays of strings.
     */
    public List<String[]> listEdges() {
        List<String[]> edges = new ArrayList<>();
        List<TreeNode<T>> stack = new ArrayList<>();
        stack.add(root);
        while (!stack.isEmpty()) {

            TreeNode<T> node = stack.remove(stack.size() - 1);
            for (TreeNode<T> child : node.children) {
                edges.add(new String[] { String.valueOf(node.data), String.valueOf(child.data) });
                stack.add(child);
            }
        }

        return edges;
    }

    /**
     * Finds and returns the depth of the specified node in the tree.
     *
     * @param node The node for which to find the depth.
     * @return The depth of the node in the tree.
     */
    public int findDepth(TreeNode<T> node) {
        int depth = 0;
        TreeNode<T> currentNode = node;
        while (currentNode != null) {
            depth++;
            currentNode = findParent(currentNode);
        }
        return depth;
    }

    /**
     * Finds and returns the height of the subtree rooted at the specified node.
     *
     * @param node The root of the subtree for which to find the height.
     * @return The height of the subtree.
     */
    public int findHeight(TreeNode<T> node) {
        if (node == null || node.children.isEmpty()) {
            return 0;
        } else {
            int maxHeight = 0;
            for (TreeNode<T> child : node.children) {
                int childHeight = findHeight(child);
                if (childHeight > maxHeight) {
                    maxHeight = childHeight;
                }
            }
            return 1 + maxHeight;
        }
    }

    /**
     * Returns a subtree rooted at the specified node.
     *
     * @param node The root of the subtree.
     * @return A new tree representing the subtree rooted at the specified node.
     */
    public Tree<T> findSubtree(TreeNode<T> node) {
        Tree<T> subtree = new Tree<>(node.data);
        subtree.root.children = node.children;
        return subtree;
    }

    /**
     * Finds and returns the parent of the specified node in the tree.
     *
     * @param node The node for which to find the parent.
     * @return The parent node, or null if the node has no parent.
     */
    public TreeNode<T> findParent(TreeNode<T> node) {
        return findParent(root, node);
    }

    /**
     * Helper function to find the parent of a node within a subtree.
     *
     * @param currentNode The current node in the traversal.
     * @param target      The node for which to find the parent.
     * @return The parent node, or null if the node has no parent in the subtree.
     */
    private TreeNode<T> findParent(TreeNode<T> currentNode, TreeNode<T> target) {
        if (currentNode == null) {
            return null;
        }
        for (TreeNode<T> child : currentNode.children) {
            if (child == target) {
                return currentNode;
            } else {
                TreeNode<T> result = findParent(child, target);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }

    /**
     * Finds and returns the path from the specified node to the root of the tree.
     *
     * @param node The node for which to find the path.
     * @return A list representing the path from the node to the root.
     */
    public List<T> findPath(TreeNode<T> node) {
        List<T> path = new ArrayList<>();
        if (node == null)
            return path;
        else {
            path.addAll(findPath(findParent(node)));
            path.add(node.data);
        }
        return path;
    }

    /**
     * Finds and returns the node containing the specified data in the subtree
     * rooted at the given node.
     *
     * @param node       The root of the subtree to search.
     * @param targetData The data to search for.
     * @return The node containing the target data, or null if not found.
     */
    public TreeNode<T> findNode(TreeNode<T> node, T targetData) {
        if (node.data.equals(targetData)) {
            return node;
        } 
        else {
            for (TreeNode<T> child : node.children) {
                TreeNode<T> result = findNode(child, targetData);
                if (result != null) {
                    return result;
                }
            }
            return null;
        }
    }

    
}