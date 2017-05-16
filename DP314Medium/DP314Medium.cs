using System;
using System.IO;
using System.Collections.Generic;

class DP314Medium 
{
    public static void Main(string[] args) 
    {
        string[] lines = File.ReadAllLines("input.txt");
        foreach(var line in lines) 
        {
            var strings = new List<Tuple<int, string>>();
            for(int i = 0; i < line.Length; i++) 
            {
                strings.Add(new Tuple<int, string>(i, Rotate(line, i)));
            }

            strings.Sort(delegate(Tuple<int, string> a, Tuple<int, string> b)
                    {
                    return String.Compare(a.Item2, b.Item2);;
                    });
            Console.WriteLine("{0} {1}", strings[0].Item1, strings[0].Item2);
        }
    }

    private static string Rotate(string s, int letters) 
    {
        string FirstHalf = s.Substring(0, letters);
        string SecondHalf = s.Substring(letters, s.Length - letters);
        return SecondHalf + FirstHalf;
    }
}
