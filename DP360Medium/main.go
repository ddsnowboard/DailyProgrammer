package main

import (
	"encoding/json"
	"fmt"
	"net/http"
	"os"
	"strconv"
	"strings"
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
	resp, err := http.Get("https://opensky-network.org/api/states/all")
	if err != nil {
		panic("Welp")
	}
	decoder := json.NewDecoder(resp.Body)
	defer resp.Body.Close()
	pos := getLocation()
}

func getLocation() *position {
	pos := os.Args[1:]
	const EXPECTED_NUMBER_OF_ARGUMENTS = 4
	if len(pos) != EXPECTED_NUMBER_OF_ARGUMENTS {
		printUsage()
	}
	lat, err := strconv.ParseFloat(pos[0], 32)
	if err != nil {
		printUsage()
	}
	long, err := strconv.ParseFloat(pos[2], 32)
	if err != nil {
		printUsage()
	}
	var direction dir = 0
	switch sDirection := strings.ToUpper(pos[1]); sDirection {
	case "N":
		direction |= NORTH
	case "S":
		direction |= SOUTH
	}

	switch sDirection := strings.ToUpper(pos[3]); sDirection {
	case "W":
		direction |= WEST
	case "E":
		direction |= EAST
	}
        return &position{Latitude: float32(lat), Longitude: float32(long), Direction: direction}
}

func printUsage() {
	panic(fmt.Sprintf("Usage: \n%s LAT DIR LONG DIR"))
}
