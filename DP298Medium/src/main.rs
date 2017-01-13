#![allow(non_snake_case)]
// https://www.reddit.com/r/dailyprogrammer/comments/5m034l/20170104_challenge_298_intermediate_too_many_or/
mod util;


use std::fs::File;
use std::io::Read;

fn main() {
    let mut f = match File::open("input.txt") {
        Ok(f) => f,
        Err(_) => {panic!("Something bad happened. There was probably no file.")}
    };
    let mut s = String::new();
    f.read_to_string(&mut s).unwrap();
    for line in s.lines() {
        match get_unmatched_paren(&String::from(line)) {
            x if x == line.len() => println!("{}", line),
            x => util::highlight_char(line, x) 
        }
    }
}

/// Returns the length of the string if there is no unmatched paren, otherwise it returns
/// the index of the first unmatched paren
fn get_unmatched_paren(s: &String) -> usize {
    let mut currIdx = 0;
    while currIdx < s.len() {
        let currChar = s.chars().nth(currIdx).unwrap();
        if currChar == ')'
        {
            return currIdx;
        }
        else if currChar == '('
        {
            match find_match(s, currIdx)
            {
                Ok(x) => currIdx = x + 1,
                Err(x) => return x
            }
        }
    }
    s.len()
}

/// NB `startingIdx` is the index of the opening paren
/// The return is either an `Ok` with the index of the thing that closes 
/// the thing at `startingIdx` or an `Err` with the index of the innermost open
/// paren that doesn't have a buddy (this can include the `startingIdx` of the topmost 
/// function call)
fn find_match(s: &String, startingIdx: usize) -> Result<usize, usize>
{
    let mut idx = startingIdx + 1;
    loop {
        match s.chars().nth(idx)
        {
            Some('(') => {
                match find_match(s, idx) {
                    Ok(x) => idx = x + 1,
                    Err(x) => return Err(x), 
                }
            }
            Some(')') => return Ok(idx), 
            Some(_) => idx += 1,
            None => return Err(startingIdx)
        }
    }
}

