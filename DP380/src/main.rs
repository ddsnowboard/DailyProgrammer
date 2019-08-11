#[macro_use]
extern crate lazy_static;

extern crate counter;
extern crate rayon;

use counter::Counter;
use rayon::prelude::*;
use std::collections::HashMap;
use std::fs::File;
use std::io::BufRead;
use std::io::BufReader;

lazy_static! {
    static ref MORSE_MAP: HashMap<char, &'static str> = "abcdefghijklmnopqrstuvwxyz"
        .chars()
        .zip(vec![
            ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..",
            "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-",
            "-.--", "--..",
        ])
        .collect();
    static ref WORD_LIST: Vec<String> = BufReader::new(File::open("wordlist").unwrap())
        .lines()
        .map(|s| s.unwrap())
        .collect();
    static ref WORD_MORSE: HashMap<String, String> =
        WORD_LIST.iter().map(|s| (s.clone(), smorse(s))).collect();
}

fn main() {
    println!("Optional 1: {}", optional1());
    println!("Optional 2: {}", optional2());
    println!("Optional 3: {}", optional3());
    println!("Optional 4: {}", optional4());
    println!("Optional 5: {}", optional5());
}

fn optional1() -> String {
    let counter: Counter<_> = WORD_MORSE.iter().map(|(_, v)| v).collect();
    counter
        .iter()
        .filter(|(_, ct)| **ct == 13)
        .map(|(k, _)| k)
        .next()
        .unwrap()
        .to_string()
}

fn optional2() -> &'static str {
    const FIFTEEN_DASHES: &'static str = "---------------";
    if let Some((word, _)) = WORD_MORSE
        .iter()
        .filter(|(_, v)| v.contains(FIFTEEN_DASHES))
        .next()
    {
        word
    } else {
        panic!("Couldn't find one");
    }
}
fn optional3() -> String {
    let is_balanced = |s: &str| -> bool { count_letters(s, &'.') == count_letters(s, &'-') };
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

fn increment_morse(mut s: String) -> Option<String> {
    for idx in (0..s.len()).rev() {
        match &s[idx..idx + 1] {
            "." => {
                s.replace_range(idx..idx + 1, &"-");
                return Some(s);
            }
            "-" => {
                s.replace_range(idx..idx + 1, &".");
            }
            _ => {
                panic!(
                    "There was something there shouldn't have been in the string; {}",
                    s
                );
            }
        }
    }
    None
}

fn get_morse(c: char) -> &'static str {
    if !c.is_ascii_alphabetic() {
        panic!("{} is not a letter of the alphabet", c);
    }

    let lowered: char = if c.is_uppercase() {
        c.to_lowercase().to_string().chars().next().unwrap()
    } else {
        c
    };

    assert!(MORSE_MAP.contains_key(&lowered));
    MORSE_MAP[&lowered]
}

fn smorse(s: &str) -> String {
    let mut out = String::new();
    const LONGEST_MORSE_STRING: usize = 4;
    out.reserve(s.len() * LONGEST_MORSE_STRING);
    for c in s.chars() {
        out.push_str(get_morse(c));
    }
    out
}

fn count_letters(s: &str, c: &char) -> usize {
    s.chars().filter(|ch| ch == c).count()
}

#[test]
fn test_get_morse() {
    assert_eq!(get_morse('s'), "...");
}

#[test]
fn test_simple_smorse() {
    assert_eq!(smorse("SOS"), "...---...");
    assert_eq!(smorse("sos"), "...---...");
    assert_eq!(smorse("sOs"), "...---...");
}

#[test]
#[ignore]
fn test_enable1() {
    const NUM_DOTS: usize = 2_499_157;
    const NUM_DASHES: usize = 1_565_081;
    let mut counted_dots = 0;
    let mut counted_dashes = 0;
    WORD_LIST.iter().map(|s| smorse(&s)).for_each(|s| {
        counted_dots += count_letters(&s, &'.');
        counted_dashes += count_letters(&s, &'-');
    });
    assert_eq!(counted_dots, NUM_DOTS);
    assert_eq!(counted_dashes, NUM_DASHES);
}

#[test]
fn test_increment_morse() {
    assert_eq!(
        Some(".....-".to_string()),
        increment_morse("......".to_string())
    );
    assert_eq!(
        Some("-....-".to_string()),
        increment_morse("-.....".to_string())
    );
    assert_eq!(
        Some("-.....".to_string()),
        increment_morse(".-----".to_string())
    );
    assert_eq!(None, increment_morse("------".to_string()));
}
