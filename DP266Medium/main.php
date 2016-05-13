<?php
class Node {
    private $connections = [];
    public function connect(Node $node)
    {
        array_push($this->$connections, $node);
    }
    public function getConnections()
    {
        return $this->$connections;
    }
    public function distance(Node $node)
    {
        // Will this work? Who knows!
        $n = 0;
        $nodes = $this->$connections;
        while(!array_search($nodes, $node))
        {
            $n++;
            $nodes = array_map(array_merge, array_map(Node::getConnections, $nodes));
        }
        return $n;
    }
}
class Graph {
    private $nodes = [];
    public function eccentricity(Node $node)
    {
        return max(array_map(function ($n) {
            return $node->distance($n);
        }, $this->$nodes));
    }
    public addNode(Node $node)
    {
        array_push($this->$nodes, $node);
    }
}
