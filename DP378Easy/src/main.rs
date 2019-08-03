use std::io::{self, Read};
use std::iter::Iterator;

type NumType = u32;
type Responses = Vec<NumType>;
fn main() -> io::Result<()> {
    let responses = parse_input();

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
