use std::io::{self, Read};
use std::iter::Iterator;
use std::ops::SubAssign;

type NumType = usize;
type Responses = Vec<NumType>;
fn main() -> io::Result<()> {
    let responses: Responses = parse_input()?;
    let possible = havel_hakimi(responses);
    if possible {
        println!("List was possible");
    } else {
        println!("List was impossible");
    }
    Ok(())
}

fn parse_input() -> io::Result<Responses> {
    let mut stdin = io::stdin();
    let mut line = String::new();
    stdin.read_to_string(&mut line)?;
    Ok(line
        .split(" ")
        .map(|s| s.trim())
        .map(|s| s.parse().unwrap())
        .collect())
}

fn havel_hakimi(mut responses: Responses) -> bool {
    let step1 = |v: &mut Responses| {
        v.sort_unstable();
        v.retain(|&x| x != 0);
    };
    step1(&mut responses);
    while let Some(n) = responses.pop() {
        println!("n is {} and the rest of the list is {:?}", n, responses);
        if responses.len() >= n {
            decrement_last_n(&mut responses, &n);
            step1(&mut responses);
        } else {
            return false;
        }
    }
    true
}

fn decrement_last_n<T: From<usize> + SubAssign + Copy>(lst: &mut Vec<T>, n: &T)
where
    usize: From<T>,
{
    for idx in (lst.len() - usize::from(*n))..lst.len() {
        lst[idx] -= (1 as usize).into();
    }
}

#[test]
fn test_given() {
    test_hh(vec![5, 3, 0, 2, 6, 2, 0, 7, 2, 5], false);
}

#[test]
fn test_easy() {
    test_hh(vec![5, 3, 0, 2, 6, 2, 0, 7, 2, 5], false);
    test_hh(vec![4, 2, 0, 1, 5, 0], false);
    test_hh(vec![3, 1, 2, 3, 1, 0], true);
    test_hh(
        vec![
            15, 18, 6, 13, 12, 4, 4, 14, 1, 6, 18, 2, 6, 16, 0, 9, 10, 7, 12, 3,
        ],
        false,
    );
    test_hh(
        vec![
            6, 0, 10, 10, 10, 5, 8, 3, 0, 14, 16, 2, 13, 1, 2, 13, 6, 15, 5, 1,
        ],
        false,
    );
    test_hh(vec![2, 2, 0], false);
    test_hh(vec![3, 2, 1], false);
    test_hh(vec![1, 1], true);
    test_hh(vec![1], false);
    test_hh(vec![], true);
}

#[test]
fn test_hard() {
    test_hh(
        vec![
            16, 9, 9, 15, 9, 7, 9, 11, 17, 11, 4, 9, 12, 14, 14, 12, 17, 0, 3, 16,
        ],
        true,
    );

    test_hh(
        vec![
            14, 10, 17, 13, 4, 8, 6, 7, 13, 13, 17, 18, 8, 17, 2, 14, 6, 4, 7, 12,
        ],
        true,
    );
}

#[allow(dead_code)]
fn test_hh(lst: Vec<usize>, expected: bool) {
    println!("Vector is {:?}; expected {}", lst, expected);
    assert_eq!(havel_hakimi(lst), expected);
}
