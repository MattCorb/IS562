package main

import (
	"bufio"
	"fmt"
	"math/rand"
	"os"
	"strconv"
	"strings"
	"time"
)

func main() {
	//open class list
	class_path := "class_list.txt"
	class_list, err := os.Open(class_path)
	if err != nil {
		fmt.Println("Sorry there is a problem with the class list. Is the class_list.txt file in the same directory")
	}

	//intialize the master list of all students
	class_slice := []string{}

	//scan file
	scanner := bufio.NewScanner(class_list)

	//load the class_slice with all students
	for scanner.Scan() {
		class_slice = append(class_slice, scanner.Text())
	}

	//close the class_list file
	defer class_list.Close()

	//intialize hashmap
	groups_map := make(map[int][]string, 0)

	//intialize reader and get prefered group size
	reader := bufio.NewReader(os.Stdin)
	fmt.Println("How many per group?")
	input_gz, _ := reader.ReadString('\n')
	int_group_size, err := strconv.Atoi(strings.TrimSpace(input_gz))
	if err != nil {
		panic("You didn't enter an integer")
	}

	//calculate the number of teams
	quotient, remainder := len(class_slice)/int_group_size, len(class_slice)%int_group_size
	if remainder > 0 {
		quotient++
	}

	//load hashmap with empty slices
	for team_no := 1; team_no <= quotient; team_no++ {
		groups_map[team_no] = []string{}
	}

	//randomize the class_slice
	for i := range class_slice {
		rand.Seed(time.Now().UnixNano())
		j := rand.Intn(i + 1)
		class_slice[i], class_slice[j] = class_slice[j], class_slice[i]
	}

	team_idx := 1
	//load members into their teams
	for student := range class_slice {

		groups_map[team_idx] = append(groups_map[team_idx], class_slice[student])
		team_idx++

		if team_idx > quotient {
			team_idx = 1
		}
	}

	//display the groups

	for i := 1; i <= len(groups_map); i++ {

		fmt.Println("Group:", i, groups_map[i])
	}

}
