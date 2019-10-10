use std::iter::once;
use std::io::stdin;
use std::io::prelude::*;


#[derive(Copy, Clone, Debug)]
enum Card {
    Up,
    Down,
}

fn main() {
    let line = stdin().lock().lines().next().unwrap().unwrap();
    let board = make_board(&line);
    if let Some(solution) = solve(board) {
        for i in solution {
            print!("{} ", i);
        }
    } else {
        println!("No solution for {}", line);
    }
}

fn solve(board: Vec<Card>) -> Option<Vec<usize>> {
    if board.is_empty() {
        return Some(vec![]);
    }

    for (c, idx) in board.iter().zip(0..) {
        if let Card::Up = c {
            let (left_board, right_board) = remove_card(&board, idx);
            if let (Some(left_vec), Some(right_vec)) = (solve(left_board), solve(right_board)) {
                let right_vec = right_vec.into_iter().map(|x| x + idx + 1);
                return Some(once(idx).chain(left_vec.into_iter()).chain(right_vec).collect());
            }
        }
    }
    None
}

fn remove_card(board: &Vec<Card>, idx: usize) -> (Vec<Card>, Vec<Card>) {
    let mut left: Vec<Card> = board[0..idx].iter().map(|&t| t).collect();
    let mut right: Vec<Card> = board[idx + 1..].iter().map(|&t| t).collect();
    let try_change = |c: Option<&mut Card>| {
        if let Some(card) = c {
            *card = match *card {
                Card::Up => Card::Down,
                Card::Down => Card::Up,
            }
        }
    };

    if idx > 0 {
        try_change(left.get_mut(idx - 1));
    }
    try_change(right.get_mut(0));
    (left, right)
}

fn make_board(s: &str) -> Vec<Card> {
    s.chars()
        .map(|c| match c {
            '0' => Card::Down,
            '1' => Card::Up,
            c => panic!("We got an unexpected character, {}", c),
        })
        .collect()
}

#[cfg(test)]
fn run_test(input: &str, output: &str) {
    let board = make_board(input);
    let expected: Option<Vec<usize>> = match output {
        "no solution" => None,
        s => Some(s.split_whitespace().map(|s| s.parse().unwrap()).collect())
    };

    let response = solve(board);
    assert_eq!(response, expected);
}

#[test]
fn test_1() {
    run_test("0100110", "1 0 2 3 5 4 6");
}

#[test]
fn test_2() {
    run_test("01001100111", "no solution");
}

#[test]
fn test_3() {
    run_test("100001100101000", "0 1 2 3 4 6 5 7 8 11 10 9 12 13 14");
}
