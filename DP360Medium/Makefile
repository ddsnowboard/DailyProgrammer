.PHONY: format clean

main : main.go
	go build main.go

format : 
	gofmt -s -w main.go

clean :
	go clean

run : main
	./main 40.6413 N 73.7781 W
