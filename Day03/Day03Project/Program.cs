using System.Text.RegularExpressions;

class Day03
{

    static void Part1()
    {
        var lines = GetInputFile();
        var pattern = @"mul\((\d+),(\d+)\)";
        Regex regex = new(pattern);
        var result = 0;


        foreach (var line in lines)
        {
            Match match = regex.Match(line);

            while (match.Success)
            {
                var number = match.Groups[1].Value;
                var number2 = match.Groups[2].Value;
                var aux = int.Parse(number) * int.Parse(number2);
                result += aux;


                match = match.NextMatch();
            }
        }

        Console.WriteLine("Part1: " + result);

    }
    static void Part2()
    {
        var lines = GetInputFile();
        var pattern = @"(don't\(\)|do\(\)|mul\(\d+,\d+\))";
        Regex regex = new(pattern);
        var result = 0;
        var isEnabled = true;

        foreach (var line in lines)
        {
            Match match = regex.Match(line);

            while (match.Success)
            {
                var command = match.Value;

                if (command == "don't()")
                {
                    isEnabled = false;
                }
                else if (command == "do()")
                {
                    isEnabled = true;
                }
                else if (command.StartsWith("mul(") && isEnabled)
                {

                    var numbers = Regex.Match(command, @"mul\((\d+),(\d+)\)");
                    if (numbers.Success)
                    {
                        var number1 = int.Parse(numbers.Groups[1].Value);
                        var number2 = int.Parse(numbers.Groups[2].Value);
                        result += number1 * number2;
                    }
                }

                match = match.NextMatch();
            }
        }

        Console.WriteLine("Part2: " + result);

    }
    public static string[] GetInputFile()
    {
        var input = @"C:\Users\mario\Desktop\AdventOfCode\Day03\Day03Project\input.txt";


        if (File.Exists(input))
        {
            var lines = File.ReadAllLines(input);
            return lines;
        }
        return new string[1];
    }
    static void Main(string[] args)
    {
        Part1();
        Part2();
    }
}