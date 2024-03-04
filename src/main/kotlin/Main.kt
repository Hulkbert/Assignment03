package org.example

fun <VertexType>dijkstra(graph: Graph<VertexType>, startNode: VertexType, endNode: VertexType): List<VertexType>{

    var unvisitedVerticies = graph.getVertices()
    var minValHeap = MyPriorityQueue<VertexType>()
    var distDictionary:MutableMap<VertexType, Double> = mutableMapOf()
    val prev: MutableMap<VertexType, VertexType> = mutableMapOf()
    //queue.addWithPriority(v, INFINITY)
    for(g in graph.getVertices()){
        distDictionary[g]=Double.MAX_VALUE
        minValHeap.addWithPriority(g, Double.MAX_VALUE)
    }
    distDictionary[startNode] = 0.0
    minValHeap.adjustPriority(startNode,0.0)

    while(!minValHeap.isEmpty()) {
        var currentNode = minValHeap.next()!!
        var currentDist = distDictionary[currentNode] ?: Double.MAX_VALUE
        graph.getEdges(currentNode).forEach() {
            it
            val u = it.key
            val cost = it.value
            var nextDist = distDictionary[u] ?: Double.MAX_VALUE

            var altDist = currentDist + cost //idk what im doing
            if (nextDist > altDist) {
                distDictionary[u] = altDist
                minValHeap.adjustPriority(u, altDist)
                prev[u]=currentNode
            }
        }
    }
    val path = mutableListOf(endNode)
    while (true) {
        val nextNode = prev[path.last()]
        if (nextNode == null) {
            return path.reversed()
        } else {
            path.add(nextNode)
        }
    }
}
fun main() {
    val graph=myGraph<String>()
    // Prices for only one way non stop from BOS boston  to ACV arcata
    graph.addEdge("BOS","SFO",232.0)
    graph.addEdge("BOS","DEN",176.0)
    graph.addEdge("BOS","LAX",405.0)
    graph.addEdge("LAX","SFO",89.0)
    graph.addEdge("LAX","DEN",380.0)
    graph.addEdge("LAX","ACV",450.0)
    graph.addEdge("SFO","LAX",89.0)
    graph.addEdge("SFO","DEN",229.0)
    graph.addEdge("SFO","ACV",400.0)
    graph.addEdge("DEN","SFO",229.0)
    graph.addEdge("DEN","LAX",380.0)
    graph.addEdge("DEN","ACV",475.0)




    println(dijkstra(graph,"BOS","ACV")) //[BOS, SFO, ACV]
}