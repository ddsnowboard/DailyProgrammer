#[macro_use]
extern crate lazy_static;
extern crate counter;
extern crate rayon;

mod morse;

use crate::morse::*;
use counter::Counter;
use rayon::prelude::*;
use std::collections::HashSet;
use std::fs::File;
use std::io::BufRead;
use std::io::BufReader;

fn main() {
    let input_lines: Vec<_> = BufReader::new(File::open("bigInput").unwrap())
        .lines()
        .map(|r| r.unwrap())
        .collect();

    let responses: Vec<_> = input_lines
        .par_iter()
        .map(|s| {
            let out = smalpha(s);
            println!("finished {}", s);
            out
        })
        .collect();
    for r in responses.into_iter() {
        let r = r.unwrap();
        assert_eq!(*ALPHABET_SET, r.chars().collect::<HashSet<_>>());
    }
}

#[allow(dead_code)]
fn optional1() -> String {
    let counter: Counter<_> = WORD_MORSE.iter().map(|(_, v)| v).collect();
    (*counter
        .iter()
        .filter(|(_, ct)| **ct == 13)
        .map(|(k, _)| k)
        .next()
        .unwrap())
    .to_string()
}

#[allow(dead_code)]
fn optional2() -> &'static str {
    const FIFTEEN_DASHES: &str = "---------------";
    if let Some((word, _)) = WORD_MORSE.iter().find(|(_, v)| v.contains(FIFTEEN_DASHES)) {
        word
    } else {
        panic!("Couldn't find one");
    }
}

#[allow(dead_code)]
fn optional3() -> String {
    let is_balanced = |s: &str| -> bool { count_letters(s, '.') == count_letters(s, '-') };
    WORD_MORSE
        .iter()
        .filter(|(w, _)| w.len() == 21)
        .filter(|(w, _)| w.as_str() != "counterdemonstrations")
        .filter(|(_, m)| is_balanced(m))
        .map(|(w, _)| w)
        .next()
        .unwrap()
        .to_string()
}

#[allow(dead_code)]
fn optional4() -> String {
    let is_palindrome = |s: &str| -> bool {
        s.chars()
            .zip(s.chars().rev())
            .take(s.len() / 2)
            .all(|(a, b)| a == b)
    };

    WORD_MORSE
        .iter()
        .filter(|(w, _)| w.len() == 13)
        .filter(|(_, m)| is_palindrome(m))
        .map(|(w, _)| w)
        .next()
        .unwrap()
        .to_string()
}

#[allow(dead_code)]
fn optional5() -> String {
    let mut out = Vec::new();
    let mut morse: String = ".............".to_string();
    let long_enough_morses: Vec<_> = WORD_MORSE
        .iter()
        .filter_map(|(_, m)| if m.len() > morse.len() { Some(m) } else { None })
        .collect();

    let is_in_codes = |s: &str| -> bool { long_enough_morses.par_iter().any(|m| m.contains(s)) };

    loop {
        if !is_in_codes(&morse) {
            out.push(morse.clone());
        }
        if let Some(new_morse) = increment_morse(morse) {
            morse = new_morse;
        } else {
            break;
        }
    }
    out.join("\n")
}
