<?php
class Node {
    private $connections = [];
    public function connect(Node $node)
    {
        array_push($this->connections, $node);
    }
    public function getConnections()
    {
        return $this->connections;
    }
    public function distance(Node $node)
    {
        // Will this work? Who knows!
        $n = 0;
        $nodes = $this->connections;
        while(!array_search($node, $nodes))
        {
            $n++;
            $nodes = array_map("array_merge", array_map(function ($no) use ($nodes)
            {
                var_dump($nodes);
                echo "\n\n\n\n\n";
                var_dump($no);
                echo "\n\n\n\n\n";
                echo "\n\n\n\n\n";
                echo "\n\n\n\n\n";
                echo "\n\n\n\n\n";
                echo "\n\n\n\n\n";
                echo "\n\n\n\n\n";
                echo "\n\n\n\n\n";
                echo "\n\n\n\n\n";
                // Apparently, $no is an array here. I'm so confused
                return $no->getConnections();
            }, $nodes));
        }
        return $n;
    }
}
class Graph {
    private $nodes = array();
    public function eccentricity(Node $node)
    {
        return max(array_map(function ($n) use ($node) {
            return $node->distance($n);
        }, $this->nodes));
    }
    public function radius()
    {
        return min(array_map(array($this, "eccentricity"), $this->nodes));
    }
    public function diameter()
    {
        return max(array_map(array($this, "eccentricity"), $this->nodes));
    }
    public function addNode($idx, Node $node)
    {
        if(is_int($idx)){
            if(!isset($nodes[$i]))
                $this->nodes[$i] =  $node;
        }
    }
    public function &getNode($i)
    {
        if(is_int($i)){
            if(!isset($this->nodes[$i]))
            {
                $this->nodes[$i] = new Node();
            }
            return $this->nodes[$i];
        }
    }
}

$file = fopen("input.txt", "r");
fgets($file);
$currString = "";
$graph = new Graph();
while(!feof($file))
{
    $currString = fgets($file);
    if(!empty($currString))
    {
        $a = 0;
        $b = 0;
        sscanf($currString, "%d %d", $a, $b);
        $graph->getNode($a)->connect($graph->getNode($b));
        $graph->getNode($b)->connect($graph->getNode($a));
    }
}
echo $graph->radius();
