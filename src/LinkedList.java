import sun.awt.image.ImageWatched;

/**
 * Development IDE: IntelliJ IDEA
 * Author: irving
 * Project Name: cs245-assignment-08
 * Date: 2018-10-29
 */
public class LinkedList implements List {

    private LinkListNode root;

    public LinkedList() {
        this.root = new LinkListNode();
    }

    public LinkedList(LinkListNode root) {
        this.root = root;
    }

    private void checkValueValidation(Object obj) {
        if (null == obj) {
            throw new RuntimeException("cannot add a null value to linkList");
        }
    }

    private LinkListNode findLastNode(LinkListNode currentNode) {
        checkListValidation("Cannot append value to an un-initialized linkList");
        return currentNode.hasNextNode() ? findLastNode(currentNode.nextNode) : currentNode;
    }

    private void checkListValidation(String message) {
        if (!listInitialized()) {
            throw new RuntimeException(message);
        }
    }

    private boolean listInitialized() {
        return root != null;
    }

    @Override
    public void add(Object obj) throws Exception {
        checkValueValidation(obj);
        LinkListNode lastNode = findLastNode(root);
        if (lastNode.equals(root) && null == root.getData()) {
            lastNode.setData(obj);
        } else {
            lastNode.setNextNode(new LinkListNode(obj));
        }
    }

    @Override
    public void add(int pos, Object obj) throws Exception {
        checkValueValidation(obj);
        LinkListNode targetNode = findNodeAtPosition(root, pos, 0);
        if (targetNode.equals(root) && null == root.getData()) {
            targetNode.setData(obj);
        } else if (targetNode.hasNextNode()) {
            targetNode.setNextNode(new LinkListNode(targetNode.getNextNode(), obj));
        } else {
            targetNode.setNextNode(new LinkListNode(obj));

        }
    }

    private LinkListNode findNodeAtPosition(LinkListNode currentNode, int pos, int index) {
        boolean hasNextNode = currentNode.hasNextNode();
        if (pos != index && hasNextNode) {
            return findNodeAtPosition(currentNode.nextNode, pos, ++index);
        } else if (!hasNextNode && currentNode != root && pos != index + 1) {
            throw new RuntimeException("cannot insert/fetch linkList node at position greater than the current capacity of the linkList");
        }
        return currentNode;
    }

    public void printLinkedList() {
        System.out.println(lookThroughLinkedList(root, new StringBuffer("[")).toString());
    }

    private StringBuffer lookThroughLinkedList(LinkListNode currentNode, StringBuffer stringBuffer) {
        if (currentNode.hasNextNode()) {
            stringBuffer.append(String.format("%s, ", currentNode.getData()));
            return lookThroughLinkedList(currentNode.getNextNode(), stringBuffer);
        } else {
            stringBuffer.append(String.format("%s]", currentNode.getData()));
            return stringBuffer;
        }
    }

    @Override
    public Object get(int pos) throws Exception {
        Object object = findNodeAtPosition(root, pos, 0).getData();
        if (null == object) {
            throw new RuntimeException("fetching value from a empty linkedList");
        }
        return object;
    }

    @Override
    public Object remove(int pos) throws Exception {
        LinkListNode targetNode = findNodeAtPosition(root, pos, 0);
        if (root.equals(targetNode)) {
            // removing the head
            if (root.hasNextNode()) {
                root = targetNode.getNextNode();
            } else {
                Object data = root.getData();
                root.setData(null);
                return data;
            }
        } else if (targetNode.hasNextNode() && targetNode.getNextNode().hasNextNode()) {
            targetNode.setNextNode(targetNode.getNextNode().getNextNode());
        }
        return targetNode.getData();
    }

    @Override
    public int size() {
        checkListValidation("Cannot append value to an un-initialized linkList");
        return null == root.getData() ? 0 : 1 + getSize(root);
    }

    private int getSize(LinkListNode currentNode) {
        return currentNode.hasNextNode() ? 1 + getSize(currentNode.getNextNode()) : 0;
    }


    private class LinkListNode {

        private LinkListNode nextNode;

        private Object data;

        public LinkListNode() {
        }

        public LinkListNode(Object data) {
            this.data = data;
        }

        public LinkListNode(LinkListNode nextNode, Object data) {
            this.nextNode = nextNode;
            this.data = data;
        }

        public LinkListNode getNextNode() {
            return nextNode;
        }

        public void setNextNode(LinkListNode nextNode) {
            this.nextNode = nextNode;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }

        public boolean hasNextNode() {
            return this.getNextNode() != null;
        }
    }
}
