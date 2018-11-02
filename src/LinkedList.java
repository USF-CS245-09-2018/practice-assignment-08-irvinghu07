
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

    private boolean isEmpty() {
        return this.root.getData() == null;
    }


    private LinkListNode findNodeBeforePosition(int pos) {
        LinkListNode currentNode = this.root;
        for (int i = 0; i < pos - 1; i++) {
            if (!currentNode.hasNextNode()) {
                throw new ArrayIndexOutOfBoundsException();
            }
            currentNode = currentNode.getNextNode();
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
    public void add(Object obj) throws Exception {
        checkValueValidation(obj);
        if (isEmpty()) {
            this.root.setData(obj);
        } else {
            LinkListNode lastNode = findLastNode(root);
            lastNode.setNextNode(new LinkListNode(obj));
        }
    }

    @Override
    public void add(int pos, Object obj) throws Exception {
        checkValueValidation(obj);
        LinkListNode targetNode = findNodeBeforePosition(pos);
        if (isEmpty()) {
            this.root.setData(obj);
        } else if (targetNode.hasNextNode()) {
            targetNode.setNextNode(new LinkListNode(targetNode.getNextNode(), obj));
        } else {
            targetNode.setNextNode(new LinkListNode(obj));
        }
    }

    @Override
    public Object get(int pos) throws Exception {
        Object object = findNodeAtPosition(pos).getData();
        if (null == object) {
            throw new RuntimeException("fetching value from a empty linkedList");
        }
        return object;
    }

    private LinkListNode findNodeAtPosition(int pos) {
        LinkListNode currentNode = this.root;
        for (int i = 0; i <= pos - 1; i++) {
            if (!currentNode.hasNextNode()) {
                throw new ArrayIndexOutOfBoundsException();
            }
            currentNode = currentNode.getNextNode();
        }
        return currentNode;
    }

    @Override
    public Object remove(int pos) throws Exception {
        if (pos == 0) {
            // removing the head
            Object data = root.getData();
            if (root.hasNextNode()) {
                root = this.root.getNextNode();
            } else {
                root.setData(null);
            }
            return data;
        } else {
            LinkListNode previousNode = findNodeBeforePosition(pos);
            LinkListNode targetNode = previousNode.getNextNode();
            if (targetNode.hasNextNode()) {
                previousNode.setNextNode(targetNode.getNextNode());
                return targetNode.getData();
            }
            previousNode.setNextNode(null);
            return targetNode.getData();
        }
    }

    @Override
    public int size() {
        checkListValidation("Cannot append value to an un-initialized linkList");
        return isEmpty() ? 0 : 1 + getSize(root);
    }

    private int getSize(LinkListNode currentNode) {
        return currentNode.hasNextNode() ? 1 + getSize(currentNode.getNextNode()) : 0;
    }

}
