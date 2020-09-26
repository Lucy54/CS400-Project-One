package main.java.backend;// --== CS400 File Header Information ==--
// Name: Nathan Hoersch
// Email: nhoersch@wisc.edu
// Team: GD
// TA: Dan
// Lecturer: Florian HEIMERL
// Notes to Grader: <optional extra notes>

class HashTableMapNode<Key, Value> {
    private Key key;
    private Value value;
    private HashTableMapNode<Key, Value> next;

    public HashTableMapNode(Key key, Value value) {
        this.key = key;
        this.value = value;
    }

    public HashTableMapNode<Key, Value> getNext() {
        return next;
    }

    public void setNext(HashTableMapNode<Key, Value> next) {
        this.next = next;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public boolean hasNext() {
        if (this.next != null) return true;
        return false;
    }
}
