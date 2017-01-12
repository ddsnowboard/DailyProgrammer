#![allow(non_snake_case)]
// https://www.reddit.com/r/dailyprogrammer/comments/5m034l/20170104_challenge_298_intermediate_too_many_or/
use std::fs::File;
use std::io::Read;

fn main() {
    let mut f = File::open("input.txt").unwrap();
    let mut s = String::new();
    f.read_to_string(&mut s).unwrap();



    for line in s.lines() {
        let mut line = String::from(line);
        let mut currIdx = 0;
        while currIdx < line.len() {
            let currChar = line.chars().nth(currIdx).unwrap();
            if currChar == ')'
            {
                surroundIndex(&mut line, currIdx, "**");
                break;
            }
            else if currChar == '('
            {
                match find_match(&mut line, currIdx)
                {
                    Ok(x) => currIdx = x + 1,
                    Err(x) => {
                        surroundIndex(&mut line, x, "**");
                        break;
                    }
                }
            }
        }
        println!("{}", line);
    }
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

fn surroundIndex(s: &mut String, currIdx: usize, toInsert: &str)
{
    s.insert_string(currIdx, toInsert);
    s.insert_string(currIdx + 1 + toInsert.len(), toInsert);
}

trait InsertsString {
    fn insert_string(&mut self, usize, &str);
}

impl InsertsString for String {
    fn insert_string(&mut self, idx: usize, toInsert: &str)
    {
        for currIndex in (0..toInsert.len()).rev() {
            self.insert(idx, toInsert.chars().nth(currIndex).unwrap()); 
        }
    }
}

#[test]
fn test_insertString() 
{
    let mut s = String::from("apples");
    s.insert_string(0, "bad");
    assert_eq!(s, "badapples");
    let mut s = String::from("apples");
    s.insert_string(3, "red");
    assert_eq!(s, "appredles");
}

fn _highlight_char(s: &str, idx: usize)
{
    println!("{}", s);
    for _ in 0..idx {
        print!(" ");
    }
    println!("^");
}
