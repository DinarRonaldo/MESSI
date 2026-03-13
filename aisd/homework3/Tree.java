package aisd.homework3;

public class Tree<T extends Comparable<T>> {
    private TreeNode<T> root;
    private int height;

    public Tree() {
        this.root = null;
        this.height = 0;
    }

    public Tree(TreeNode<T> root) {
        this.root = root;
        this.height = 1;
    }

    public void add(T value) {
        TreeNode<T> newNode = new TreeNode<>(value);

        if (this.root == null) {
            this.root = newNode;
            this.height = 1;
        } else {
            add(this.root, value);
            updateHeight();
        }
    }

    private void add(TreeNode<T> current, T value) {
        if (current.getValue().compareTo(value) > 0) {
            if (current.getLeft() == null) {
                current.setLeft(new TreeNode<>(value, current));
            } else {
                add(current.getLeft(), value);
            }
        } else {
            if (current.getRight() == null) {
                current.setRight(new TreeNode<>(value, current));
            } else {
                add(current.getRight(), value);
            }
        }
    }

    public void preOrder() {
        preOrder(root);
        System.out.println();
    }

    private void preOrder(TreeNode<T> node) {
        if (node != null) {
            System.out.print(node.getValue() + " ");
            preOrder(node.getLeft());
            preOrder(node.getRight());
        }
    }

    public void inOrder() {
        inOrder(root);
        System.out.println();
    }

    private void inOrder(TreeNode<T> node) {
        if (node != null) {
            inOrder(node.getLeft());
            System.out.print(node.getValue() + " ");
            inOrder(node.getRight());
        }
    }

    public void postOrder() {
        postOrder(root);
        System.out.println();
    }

    private void postOrder(TreeNode<T> node) {
        if (node != null) {
            postOrder(node.getLeft());
            postOrder(node.getRight());
            System.out.print(node.getValue() + " ");
        }
    }

    public void set(T oldValue, T newValue) {
        TreeNode<T> node = find(root, oldValue);
        if (node != null) {
            node.setValue(newValue);
        }
    }

    private TreeNode<T> find(TreeNode<T> node, T value) {
        if (node == null) return null;

        if (node.getValue().equals(value)) {
            return node;
        }

        TreeNode<T> leftResult = find(node.getLeft(), value);
        if (leftResult != null) return leftResult;

        return find(node.getRight(), value);
    }

    public void remove(T value) {
        root = remove(root, value);
        updateHeight();
    }

    private TreeNode<T> remove(TreeNode<T> node, T value) {
        if (node == null) return null;

        if (value.compareTo(node.getValue()) < 0) {
            node.setLeft(remove(node.getLeft(), value));
        } else if (value.compareTo(node.getValue()) > 0) {
            node.setRight(remove(node.getRight(), value));
        } else {
            if (node.getLeft() == null && node.getRight() == null) {
                return null;
            } else if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            } else {
                TreeNode<T> temp = node.getLeft();
                while (temp.getRight() != null) {
                    temp = temp.getRight();
                }
                node.setValue(temp.getValue());
                node.setLeft(remove(node.getLeft(), temp.getValue()));
            }
        }
        return node;
    }

    public int getHeight() {
        return height;
    }

    private void updateHeight() {
        this.height = calculateHeight(root);
    }

    private int calculateHeight(TreeNode<T> node) {
        if (node == null) return 0;
        return 1 + Math.max(calculateHeight(node.getLeft()), calculateHeight(node.getRight()));
    }

    public TreeNode<T> getRoot() {
        return root;
    }
}