package main

import (
	"encoding/json"
	"fmt"
	"io"
	"math"
	// "net/http"
	"os"
	"strconv"
	"strings"
)

type position struct {
	Latitude  float64
	Longitude float64
}

type airplane struct {
	Pos      position
	Callsign string
	Altitude float64
	Country  string
	ID       string
}

type response struct {
	States [][]interface{} `json:"states"`
}

func main() {
	data := getInput()
	defer data.Close()
	decoder := json.NewDecoder(data)
	pos := getLocation()
	var curr response
	decoder.Decode(&curr)
	planes := structifyArrays(curr.States[:])
	closest := 0
	closestDistance := squareDistance(pos, &planes[0].Pos)

	for i := 1; i < len(planes); i++ {
		if dist := squareDistance(pos, &planes[i].Pos); dist < closestDistance {
			closestDistance = dist
			closest = i
		}
	}
	closestPlane := planes[closest]
	fmt.Printf("%f\n%s\n%f\n%f\n%f\n%s\n%s\n", math.Pow(closestDistance, 0.5), closestPlane.Callsign, closestPlane.Pos.Latitude, closestPlane.Pos.Longitude, closestPlane.Altitude, closestPlane.Country, closestPlane.ID)
}

func getLocation() *position {
	pos := os.Args[1:]
	const EXPECTED_NUMBER_OF_ARGUMENTS = 4
	if len(pos) != EXPECTED_NUMBER_OF_ARGUMENTS {
		printUsage()
	}
	lat, err := strconv.ParseFloat(pos[0], 64)
	if err != nil {
		printUsage()
	}
	long, err := strconv.ParseFloat(pos[2], 64)
	if err != nil {
		printUsage()
	}

	if strings.ToUpper(pos[1]) == "S" {
		lat *= -1
	}

	if strings.ToUpper(pos[3]) == "W" {
		long *= -1
	}

	out := position{Latitude: float64(lat), Longitude: float64(long)}
	return &out
}

func printUsage() {
	panic(fmt.Sprintf("Usage: \n%s LAT DIR LONG DIR", os.Args[0]))
}

func structifyArrays(bigArray [][]interface{}) []*airplane {
	out := make([]*airplane, 0, len(bigArray))
	ch := make(chan *airplane)
	for i := 0; i < len(bigArray); i++ {
		curr := bigArray[i]
		go structifyArray(curr, ch)
	}

	for i := 0; i < len(bigArray); i++ {
		curr := <-ch
		if curr != nil {
			out = append(out, curr)
		}
	}

	return out
}

func structifyArray(arr []interface{}, ch chan *airplane) {
	var currPlane airplane
	if st, ok := arr[0].(string); ok {
		currPlane.ID = st
	} else {
		currPlane.ID = ""
	}

	if st, ok := arr[1].(string); ok {
		currPlane.Callsign = st
	} else {
		currPlane.Callsign = ""
	}

	if st, ok := arr[2].(string); ok {
		currPlane.Country = st
	} else {
		currPlane.Country = ""
	}

	if fLong, okLong := arr[5].(float64); okLong {
		if fLat, okLat := arr[6].(float64); okLat {
			currPlane.Pos = position{Latitude: fLat, Longitude: fLong}
		}
	} else {
		ch <- nil
	}

	if f, ok := arr[7].(float64); ok {
		currPlane.Altitude = f
	} else {
		currPlane.Altitude = 0
	}

	ch <- &currPlane
}

func squareDistance(a *position, b *position) float64 {
	return math.Pow(a.Latitude-b.Latitude, 2) + math.Pow(a.Longitude-b.Longitude, 2)
}

/*
func getInput() io.ReadCloser {
	resp, err := http.Get("https://opensky-network.org/api/states/all")
	if err != nil {
		panic("Welp")
	}
	return resp.Body
}
*/

func getInput() io.ReadCloser {
	f, err := os.Open("input.json")
	if err != nil {
		panic("Welp")
	}
	return f
}
