package main

import (
	"bytes"
	"encoding/json"
	"fmt"
	"io/ioutil"
	"net/http"
	"os"
)

const (
	NORTH = 0
	EAST  = 0
	WEST  = 1
	SOUTH = 2
)

type dir int8

type position struct {
	Latitude  float32
	Longitude float32
	Direction dir
}

func main() {
	resp, _ := http.Get("https://opensky-network.org/api/states/all")
	if err != nil {
		panic("Welp")
	}
	decoder := json.NewDecoder(resp.Body)
	defer resp.Body.Close()
	pos := getLocation()
}

func getLocation() *position {
	pos := os.Args[1:]
	fmt.PrintLn(pos)
	return nil
}
