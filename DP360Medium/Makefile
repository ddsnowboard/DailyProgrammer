.PHONY: format clean

main : main.go
	go build main.go

format : 
	gofmt -s -w main.go

clean :
	go clean

run : main
	./main
