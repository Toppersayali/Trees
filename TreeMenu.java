package tree;

import java.util.*;

public class TreeMenu {
    public static <T> void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Tree<String> tree = new Tree<>("A");
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add child node");
            System.out.println("2. Display tree");
            System.out.println("3. Find siblings of a node");
            System.out.println("4. List leaves of the tree");
            System.out.println("5. List internal nodes of the tree");
            System.out.println("6. List edges of the tree");
            System.out.println("7. Find depth of a node");
            System.out.println("8. Find height of the tree");
            System.out.println("9. Find subtree rooted at a given node");
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter parent node data: ");
                    String parentData = scanner.next();
                    System.out.print("Enter child node data: ");
                    String childData = scanner.next();
                    TreeNode<String> parentNode = tree.findNode(tree.root, parentData);
                    if (parentNode != null) {
                        tree.addChild(parentNode, childData);
                        System.out.println("Child node added successfully.");
                    } else {
                        System.out.println("Parent node not found.");
                    }
                    break;
                case 2:
                    System.out.println("Tree structure:");
                    int l;
                    System.out.println("Enter the number of levels to be displayed.");
                    l=scanner.nextInt();
                    tree.displayTree(tree.root,l);
                    break;
                case 3:
                    System.out.print("Enter node data to find its siblings: ");
                    String nodeData = scanner.next();
                    TreeNode<String> nodeToFindSiblings = tree.findNode(tree.root, nodeData);
                    if (nodeToFindSiblings != null) {
                        List<TreeNode<String>> siblings = tree.findSiblings(nodeToFindSiblings);
                        System.out.print("Siblings of '" + nodeData + "': ");
                        for (TreeNode<String> sibling : siblings) {
                            System.out.print(sibling.data + " ");
                        }
                        System.out.println();
                    } else {
                        System.out.println("Node not found.");
                    }
                    break;
                case 4:
                    List<TreeNode<String>> leaves = tree.listLeaves(tree.root);
                    System.out.print("Leaves of the tree: ");
                    for (TreeNode<String> leaf : leaves) {
                        System.out.print(leaf.data + " ");
                    }
                    System.out.println();
                    break;
                case 5:
                    List<TreeNode<String>> internalNodes = tree.listInternalNodes(tree.root);
                    System.out.print("Internal nodes of the tree: ");
                    for (TreeNode<String> internalNode : internalNodes) {
                        System.out.print(internalNode.data + " ");
                    }
                    System.out.println();
                    break;
                case 6:
                    List<String[]> edges = tree.listEdges();
                    System.out.println("Edges of the tree:");
                    for (String[] edge : edges) {
                        System.out.println(edge[0] + " -> " + edge[1]);
                    }
                    break;

                case 7:
                    System.out.print("Enter node data to find its depth: ");
                    String nodeDataForDepth = scanner.next();
                    TreeNode<String> nodeForDepth = tree.findNode(tree.root, nodeDataForDepth);
                    if (nodeForDepth != null) {
                        int depth = tree.findDepth(nodeForDepth);
                        System.out.println("Depth of '" + nodeDataForDepth + "': " + depth);
                    } else {
                        System.out.println("Node not found.");
                    }
                    break;
                case 8:
                    int height = tree.findHeight(tree.root);
                    System.out.println("Height of the tree: " + height);
                    break;
                case 9:
                    System.out.print("Enter node data to find its subtree: ");
                    String subtreeData = scanner.next();
                    TreeNode<String> subtreeRoot = tree.findNode(tree.root, subtreeData);
                    if (subtreeRoot != null) {
                        Tree<String> subtree = tree.findSubtree(subtreeRoot);
                        System.out.println("Subtree rooted at '" + subtreeData + "':");
                        subtree.displayTree(subtree.root, 0);
                    } else {
                        System.out.println("Node not found.");
                    }
                    break;
                case 10:
                    System.out.println("Exiting program.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

    }
}

	
