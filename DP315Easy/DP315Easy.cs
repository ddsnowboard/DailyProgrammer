using System;
using System.IO;
using System.Text.RegularExpressions;
using System.Linq;

class DP315Easy {
    public static void Main(string[] args) {
        string[] lines = File.ReadAllLines("input.txt");
        var re = new Regex("^([0-9]+) ([0-9]+)$");
        foreach(var line in lines) 
        {
            if(re.IsMatch(line))
            {
                var match = re.Match(line);
                int a = Int32.Parse(match.Groups[1].Value);
                int b = Int32.Parse(match.Groups[2].Value);
                Console.WriteLine("{0}@{1}={2}", a, b, XorMultiply(a, b));
            }
            else
            {
                Console.WriteLine("Broke on {0}", line);
            }
        }
    }

    private static int XorMultiply(int a, int b) {
        int Retval = 0;
        do
        {
            if((b & 1) == 1) 
            {
                Retval ^= a;
            }
            a = a << 1;
        }
        while((b = b >> 1) != 0);
        return Retval;
    }
}
