<?php
define("FILENAME", "input.txt");
$file = fopen(FILENAME, "r");
fgets($file);
$nodes = array();
while(!feof($file))
{
    $matches = array();
    $line = fgets($file);
    if(preg_match('/^(?P<one>[0-9]+) (?P<two>[0-9]+)$/', $line, $matches))
    {
        $one = $matches["one"];
        $two = $matches["two"];
        if(!isset($nodes[$one]))
            $nodes[$one] = array();
        array_push($nodes[$one], $two);
        if(!isset($nodes[$two]))
            $nodes[$two] = array();
        array_push($nodes[$two], $one);
    }
}

foreach($nodes as $key => $value)
{
    $count = count($value);
    echo "Node $key has degree of $count\n";
}
