using System.Globalization;

class Day02
{
    public static void Part1()
    {
        string[] lines = GetInputFile();
        int[] safeLevels = [1, 2, 3];
        var count = 0;
        var isIncreasing = true;


        foreach (var line in lines)
        {
            var aux = 0;
            var isSafe = true;

            var nums = line.Trim().Split(" ");

            if (int.Parse(nums[0]) > int.Parse(nums[1]))
            {
                isIncreasing = false;
            }
            else
            {
                isIncreasing = true;
            }
            foreach (var num in nums)
            {
                if (aux == 0)
                {
                    aux = int.Parse(num);
                    continue;
                }
                else
                {


                    if (isIncreasing)
                    {
                        if (aux > int.Parse(num))
                        {
                            isSafe = false;
                            break;
                        }
                        else
                        {

                            var aux2 = Math.Abs(aux - int.Parse(num));
                            if (!safeLevels.Contains(aux2))
                            {
                                isSafe = false;



                            }
                        }


                    }
                    else
                    {
                        if (aux < int.Parse(num))
                        {
                            isSafe = false;
                            break;
                        }
                        else
                        {

                            var aux2 = Math.Abs(aux - int.Parse(num));
                            if (!safeLevels.Contains(aux2))
                            {
                                isSafe = false;



                            }
                        }

                    }




                }
                aux = int.Parse(num);


            }
            if (isSafe)
            {
                count++;
            }

        }
        Console.WriteLine("Part1: " + count);
    }


        public static void Part2()
    {
        string[] lines = GetInputFile();
        int[] safeLevels = { 1, 2, 3 };
        var count = 0;
    
        foreach (var line in lines)
        {
            var nums = line.Trim().Split(" ").Select(int.Parse).ToList();
            var isSafe = false;
    
            for (int i = 0; i < nums.Count; i++)
            {
                var tempNums = new List<int>(nums);
                tempNums.RemoveAt(i);
    
                if (IsSafe(tempNums, safeLevels))
                {
                    isSafe = true;
                    break;
                }
            }
    
            if (isSafe)
            {
                count++;
            }
        }
    
        Console.WriteLine("Part2: " + count);
    }
    
    private static bool IsSafe(List<int> nums, int[] safeLevels)
    {
        var isIncreasing = nums[0] < nums[1];
        var aux = nums[0];
    
        for (int i = 1; i < nums.Count; i++)
        {
            var num = nums[i];
    
            if (isIncreasing)
            {
                if (aux > num || !safeLevels.Contains(Math.Abs(aux - num)))
                {
                    return false;
                }
            }
            else
            {
                if (aux < num || !safeLevels.Contains(Math.Abs(aux - num)))
                {
                    return false;
                }
            }
    
            aux = num;
        }
    
        return true;
    }
    public static string[] GetInputFile()
    {
        var input = @"C:\Users\mario\Desktop\AdventOfCode\Day02\Day02Project\input.txt";


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