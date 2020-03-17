package main

import "fmt"
import "os"
import "bufio"
import "strconv"

const MaxNumber = 100000000

type Counter struct {
	counts map[int]int
}

func newCounter() Counter {
	return Counter{counts: make(map[int]int)}
}

func newCounterWithCounts(values []int) Counter {
	out := Counter{counts: make(map[int]int)}
	out.update(values)
	return out
}

func (this *Counter) update(values []int) {
	for _, val := range values {
		this.counts[val] += 1
	}
}

func (this *Counter) getCount(value int) int {
	return this.counts[value]
}

func yahtzee_upper(rolls []int) int {
	c := newCounterWithCounts(rolls)
	max := 0

	for _, val := range rolls {
		score := c.getCount(val) * val
		if score > max {
			max = score
		}
	}
	return max
}

func parseInput(filename string) []int {
	file, err := os.Open(filename)
	if err != nil {
		panic(fmt.Sprintf("%s", err))
	}
	defer file.Close()
	scanner := bufio.NewScanner(file)
	out := make([]int, 0, 1000)
	for scanner.Scan() {
		s := scanner.Text()
		num, _ := strconv.Atoi(s)
		out = append(out, num)
	}
	return out
}

func main() {
	input := parseInput("input.txt")
	fmt.Println(len(input))
	fmt.Println(yahtzee_upper(input))
	// fmt.Println(10 == yahtzee_upper([]int{2, 3, 5, 5, 6}))
	// fmt.Println(4 == yahtzee_upper([]int{1, 1, 1, 1, 3}))
	// fmt.Println(6 == yahtzee_upper([]int{1, 1, 1, 3, 3}))
	// fmt.Println(5 == yahtzee_upper([]int{1, 2, 3, 4, 5}))
	// fmt.Println(30 == yahtzee_upper([]int{6, 6, 6, 6, 6}))
}
