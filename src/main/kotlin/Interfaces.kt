package org.example

/**
 * This ``Graph`` that represents a directed graph
 * @param VertexType the representation of a vertex in the graph
 */
interface Graph<VertexType> {
    /**
     * @return the vertices in the graph
     */
    fun getVertices(): Set<VertexType>

    /**
     * Add an
     */
    fun addEdge(from: VertexType, to: VertexType, cost: Double)

    /**
     *
     */
    fun getEdges(from: VertexType): Map<VertexType, Double>

    /**
     * Remove all edges and vertices from the graph
     */
    fun clear()
}
class myGraph<VertexType>:Graph<VertexType>{
    private var firstDict:MutableMap<VertexType,MutableMap<VertexType,Double>> = mutableMapOf()
    override fun getVertices(): Set<VertexType> {
        return firstDict.keys

    }

    override fun clear() {
        firstDict.clear()
    }

    override fun getEdges(from: VertexType): Map<VertexType, Double> {

        return firstDict[from]!!.toMap()
    }

    override fun addEdge(from: VertexType, to: VertexType, cost: Double) {
        if (from in firstDict.keys) {
            firstDict[from]!!.put(to, cost)
        }else{
            firstDict.put(from, mutableMapOf(to to cost))
        }
        if (to !in firstDict.keys){
            firstDict.put(to,mutableMapOf())
        }
    }

}
/**
 * ``MinPriorityQueue`` maintains a priority queue where the lower
 *  the priority value, the sooner the element will be removed from
 *  the queue.
 *  @param T the representation of the items in the queue
 */
interface MinPriorityQueue<T> {
    /**
     * @return true if the queue is empty, false otherwise
     */
    fun isEmpty(): Boolean

    /**
     * Add [elem] with at level [priority]
     */
    fun addWithPriority(elem: T, priority: Double)

    /**
     * Get the next (lowest priority) element.
     * @return the next element in terms of priority.  If empty, return null.
     */
    fun next(): T?

    /**
     * Adjust the priority of the given element
     * @param elem whose priority should change
     * @param newPriority the priority to use for the element
     *   the lower the priority the earlier the element int
     *   the order.
     */
    fun adjustPriority(elem: T, newPriority: Double)
}
class MyPriorityQueue<T>:MinPriorityQueue<T> {
    private var minHeap = MinHeap<T>()
    override fun isEmpty(): Boolean {
        return minHeap.isEmpty()
    }

    override fun next(): T? {
        return minHeap.getMin()
    }

    override fun adjustPriority(elem: T, newPriority: Double) {
        minHeap.adjustHeapNumber(elem,newPriority)
    }

    override fun addWithPriority(elem: T, priority: Double) {
        minHeap.insert(elem,priority)

    }

}