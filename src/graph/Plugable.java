package graph;

/**
 * Created by rkalhans on 3/16/2016.
 */
public interface Plugable {

    void forEachNode(Node n);
    boolean forEachParentChildPair(Node parent, Node child, double distance);
    void resetNode(Node n);
}
